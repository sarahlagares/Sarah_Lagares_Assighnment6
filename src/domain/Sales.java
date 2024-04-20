package domain;

import java.time.YearMonth;

public class TeslaSales {

    private final YearMonth yearMonth;

    private final Integer salesAmount;

    //all Sales reports' data are final, can not be changed, don't need setter.

    public TeslaSales(YearMonth yearMonth, Integer salesAmount) {
        this.yearMonth = yearMonth;
        this.salesAmount = salesAmount;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }


    public Integer getSalesAmount() {
        return salesAmount;
    }


    @Override
    public String toString() {
        return yearMonth + "->" + salesAmount;
    }

}
