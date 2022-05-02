package com.zillennium.utswap.models.LockUpHistory;

import java.io.Serializable;

public class LockUpHistory implements Serializable {
    String amount, date, project, period, maturity;


    public LockUpHistory(String amount, String date, String project, String period, String maturity) {
        this.amount = amount;
        this.date = date;
        this.project = project;
        this.period = period;
        this.maturity = maturity;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getProject() {
        return project;
    }

    public String getPeriod() {
        return period;
    }

    public String getMaturity() {
        return maturity;
    }
}
