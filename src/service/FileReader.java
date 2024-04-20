package service;

import domain.Sales;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public List<Sales> readFile(String filename) throws IOException {
        List<Sales> sales = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new java.io.FileReader(filename));

        reader.readLine();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] columns = line.split(",");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy");
            Sales salesTemp = new Sales(YearMonth.parse(columns[0], formatter),
                    Integer.parseInt(columns[1]));
            sales.add(salesTemp);
        }
        return sales;
    }
}
