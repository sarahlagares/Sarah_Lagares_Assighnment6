package service;

import domain.TeslaSale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    public List<TeslaSale> readFile(String filename) throws IOException {
        List<TeslaSale> teslaSales = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        reader.readLine();
        String line;

        while ((line = reader.readLine()) != null) {
            String[] columns = line.split(",");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy");
            TeslaSale teslaSaleTemp = new TeslaSale(YearMonth.parse(columns[0], formatter),
                    Integer.parseInt(columns[1]));
            teslaSales.add(teslaSaleTemp);
        }
        return teslaSales;
    }
}
