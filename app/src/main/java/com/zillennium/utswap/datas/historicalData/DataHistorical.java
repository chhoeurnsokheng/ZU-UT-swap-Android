package com.zillennium.utswap.datas.historicalData;

import com.zillennium.utswap.R;
import com.zillennium.utswap.models.Historical.Historical;

import java.util.ArrayList;

public class DataHistorical {
    public static ArrayList<Historical> LIST_OF_HISTORICAL_HISTORY() {
        ArrayList<Historical> listHistorical = new ArrayList<>();

        listHistorical.add(new Historical(R.drawable.ic_arrow_down_right,"Sold 10UT New Airport 38Ha @ 18.71","28-Feb-2022","$ 187.10","10","$ 18.71","$ 187.10","6 822"));
        listHistorical.add(new Historical(R.drawable.ic_arrow_down_right,"Sold 25UT New Airport 38Ha @ 18.75","28-Feb-2022","$ 468.75","25","$ 18.75","$ 468.75","6 842"));

        return listHistorical;
    }
}
