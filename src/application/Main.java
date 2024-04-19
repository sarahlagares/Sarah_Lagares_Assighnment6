package application;

import domain.SalesData;
import service.Files;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        try {
            List<SalesData> model3Data = Files.readModel3CSV("model3.csv");
            List<SalesData> modelSData = Files.readModelSCSV("modelS.csv");
            List<SalesData> modelXData = Files.readModelXCSV("modelX.csv");

            // Group data by year and month for each vehicle model
            Map<Integer, Map<Integer, List<SalesData>>> model3SalesByYearAndMonth = groupByYearAndMonth(model3Data);
            Map<Integer, Map<Integer, List<SalesData>>> modelSSalesByYearAndMonth = groupByYearAndMonth(modelSData);
            Map<Integer, Map<Integer, List<SalesData>>> modelXSalesByYearAndMonth = groupByYearAndMonth(modelXData);

            // Calculate yearly sales for each vehicle model
            Map<Integer, Integer> model3YearlySales = calculateYearlySales(model3SalesByYearAndMonth);
            Map<Integer, Integer> modelSYearlySales = calculateYearlySales(modelSSalesByYearAndMonth);
            Map<Integer, Integer> modelXYearlySales = calculateYearlySales(modelXSalesByYearAndMonth);

            // Identify best and worst months for each vehicle model
            Map<Integer, String> model3BestAndWorstMonths = identifyBestAndWorstMonths(model3SalesByYearAndMonth);
            Map<Integer, String> modelSBestAndWorstMonths = identifyBestAndWorstMonths(modelSSalesByYearAndMonth);
            Map<Integer, String> modelXBestAndWorstMonths = identifyBestAndWorstMonths(modelXSalesByYearAndMonth);

            // Output the results
            outputResults("Model 3", model3YearlySales, model3BestAndWorstMonths);
            outputResults("Model S", modelSYearlySales, modelSBestAndWorstMonths);
            outputResults("Model X", modelXYearlySales, modelXBestAndWorstMonths);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<Integer, Map<Integer, List<SalesData>>> groupByYearAndMonth(List<SalesData> salesData) {
        return salesData.stream()
                .collect(Collectors.groupingBy(sales -> Integer.parseInt(sales.getDate().substring(3, 5)),
                        Collectors.groupingBy(sales -> Integer.parseInt(sales.getDate().substring(6, 8)))));
    }

    private static Map<Integer, Integer> calculateYearlySales(Map<Integer, Map<Integer, List<SalesData>>> salesByYearAndMonth) {
        Map<Integer, Integer> yearlySales = new HashMap<>();
        for (Map.Entry<Integer, Map<Integer, List<SalesData>>> entry : salesByYearAndMonth.entrySet()) {
            int year = entry.getKey();
            int totalSales = entry.getValue().values().stream()
                    .flatMap(List::stream)
                    .mapToInt(SalesData::getSales)
                    .sum();
            yearlySales.put(year, totalSales);
        }
        return yearlySales;
    }

    private static Map<Integer, String> identifyBestAndWorstMonths(Map<Integer, Map<Integer, List<SalesData>>> salesByYearAndMonth) {
        Map<Integer, String> bestAndWorstMonths = new HashMap<>();
        for (Map.Entry<Integer, Map<Integer, List<SalesData>>> entry : salesByYearAndMonth.entrySet()) {
            int year = entry.getKey();
            Map<Integer, List<SalesData>> salesByMonth = entry.getValue();
            int maxSales = 0, minSales = Integer.MAX_VALUE;
            int bestMonth = 0, worstMonth = 0;
            for (Map.Entry<Integer, List<SalesData>> monthEntry : salesByMonth.entrySet()) {
                int month = monthEntry.getKey();
                int totalSales = monthEntry.getValue().stream()
                        .mapToInt(SalesData::getSales)
                        .sum();
                if (totalSales > maxSales) {
                    maxSales = totalSales;
                    bestMonth = month;
                }
                if (totalSales < minSales) {
                    minSales = totalSales;
                    worstMonth = month;
                }
            }
            String bestAndWorst = String.format("%04d-%02d", year, bestMonth);
            bestAndWorst += " -> " + maxSales + "\n" + String.format("%04d-%02d", year, worstMonth) + " -> " + minSales;
            bestAndWorstMonths.put(year, bestAndWorst);
        }
        return bestAndWorstMonths;
    }

    private static void outputResults(String model, Map<Integer, Integer> yearlySales, Map<Integer, String> bestAndWorstMonths) {
        System.out.println(model + " Yearly Sales Report\n---------------------------");
        for (Map.Entry<Integer, Integer> entry : yearlySales.entrySet()) {
            int year = entry.getKey();
            int sales = entry.getValue();
            System.out.println(year + " -> " + sales);
        }
        System.out.println("The best month for " + model + " was: ");
        for (Map.Entry<Integer, String> entry : bestAndWorstMonths.entrySet()) {
            int year = entry.getKey();
            String bestAndWorst = entry.getValue();
            String[] parts = bestAndWorst.split("\n");
            String bestMonth = parts[0];
            System.out.println(bestMonth);
        }
        System.out.println("The worst month for " + model + " was: ");
        for (Map.Entry<Integer, String> entry : bestAndWorstMonths.entrySet()) {
            String bestAndWorst = entry.getValue();
            String[] parts = bestAndWorst.split("\n");
            String worstMonth = parts[1];
            System.out.println(worstMonth);
        }
        System.out.println();
    }
}