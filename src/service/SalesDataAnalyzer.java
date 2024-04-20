package service;

import domain.Sales;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SalesDataAnalyzer {

    public static void creatYearlySalesReport(List<Sales> amount, String model){
        System.out.println(model + " Yearly Sales Report");
        System.out.println("---------------------------");

        Map<Integer, Integer> yearlySales = amount.stream()
                .collect(Collectors.groupingBy
                        (sales -> sales.getYearMonth().getYear(),
                                Collectors.summingInt(Sales::getSalesAmount)));
        yearlySales.forEach((a,z)-> System.out.println(a+" -> "+z));
        System.out.println(" ");

    }

    public static void bestAndWorstMonth(List<Sales> amount, String model){
        amount.stream()
                .max(Comparator
                        .comparingInt(Sales::getSalesAmount))
                .ifPresent(bestMonth ->
                        System.out.println("The best month for " + model + " was: " + bestMonth.getYearMonth()));

        amount.stream()
                .min(Comparator
                        .comparingInt(Sales::getSalesAmount))
                .ifPresent(worstMonth ->
                        System.out.println("The worst month for " + model + " was: " + worstMonth.getYearMonth()));
        System.out.println(" ");

    }
    public static void generateTeslaSalesReport(FileReader read) throws IOException {

        List<Sales> model3 = read.readFile("model3.csv");
        creatYearlySalesReport(model3, "Model 3");
        bestAndWorstMonth(model3, "Model 3");

        List<Sales> modelS = read.readFile("modelS.csv");
        creatYearlySalesReport(modelS, "Model S");
        bestAndWorstMonth(modelS, "Model S");

        List<Sales> modelX = read.readFile("modelX.csv");
        creatYearlySalesReport(modelX, "Model X");
        bestAndWorstMonth(modelX, "Model X");
    }

}
