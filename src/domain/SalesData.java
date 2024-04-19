package domain;

public class SalesData {
    private String date;
    private int sales;

    public SalesData(String date, int sales) {
        this.date = date;
        this.sales = sales;
    }

    public String getDate() {
        return date;
    }

    public int getSales() {
        return sales;
    }
}
