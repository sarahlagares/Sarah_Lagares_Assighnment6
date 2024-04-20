package service;

import domain.TeslaSale;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SaleDataService {

    public static void creatYearlySalesReport(List<TeslaSale> amount, String model){
        System.out.println(model + " Yearly Sales Report");
        System.out.println("---------------------------");

        Map<Integer, Integer> yearlySales = amount.stream()
                .collect(Collectors.groupingBy
                        (teslaSale -> teslaSale.getYearMonth().getYear(),
                                Collectors.summingInt(TeslaSale::getSalesAmount)));
        yearlySales.forEach((a,z)-> System.out.println(a+" -> "+z));
        System.out.println(" ");

    }

    public static void bestAndWorstMonth(List<TeslaSale> amount, String model){
        amount.stream()
                .max(Comparator
                        .comparingInt(TeslaSale::getSalesAmount))
                .ifPresent(bestMonth ->
                        System.out.println("The best month for " + model + " was: " + bestMonth.getYearMonth()));

        amount.stream()
                .min(Comparator
                        .comparingInt(TeslaSale::getSalesAmount))
                .ifPresent(worstMonth ->
                        System.out.println("The worst month for " + model + " was: " + worstMonth.getYearMonth()));
        System.out.println(" ");

    }
    public static void generateTeslaSaleReport(FileService read) throws IOException {

        List<TeslaSale> model3 = read.readFile("model3.csv");
        creatYearlySalesReport(model3, "Model 3");
        bestAndWorstMonth(model3, "Model 3");

        List<TeslaSale> modelS = read.readFile("modelS.csv");
        creatYearlySalesReport(modelS, "Model S");
        bestAndWorstMonth(modelS, "Model S");

        List<TeslaSale> modelX = read.readFile("modelX.csv");
        creatYearlySalesReport(modelX, "Model X");
        bestAndWorstMonth(modelX, "Model X");
    }

}
