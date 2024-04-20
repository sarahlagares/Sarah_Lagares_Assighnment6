package application;

import service.FileReader;
import service.SalesDataAnalyzer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        FileReader fileReader = new FileReader();
        SalesDataAnalyzer.generateTeslaSalesReport(fileReader);
    }
}