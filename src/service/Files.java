package service;

import domain.SalesData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Files {

    public static List<SalesData> readModel3CSV(String filename) throws IOException {
        List<SalesData> model3Data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("model3.csv"))) {
            String line;
            // Skip the header
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String date = parts[0];
                int sales = Integer.parseInt(parts[1]);
                model3Data.add(new SalesData(date, sales));
            }
        }
        return model3Data;
    }

    // Method to read modelS.csv
    public static List<SalesData> readModelSCSV(String filename) throws IOException {
        List<SalesData> modelSData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("modelS"))) {
            String line;
            // Skip the header
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String date = parts[0];
                int sales = Integer.parseInt(parts[1]);
                modelSData.add(new SalesData(date, sales));
            }
        }
        return modelSData;
    }

    public static List<SalesData> readModelXCSV(String filename) throws IOException {
        List<SalesData> modelXData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("modelX"))) {
            String line;
            // Skip the header
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String date = parts[0];
                int sales = Integer.parseInt(parts[1]);
                modelXData.add(new SalesData(date, sales));
            }
        }
        return modelXData;


    }
}
