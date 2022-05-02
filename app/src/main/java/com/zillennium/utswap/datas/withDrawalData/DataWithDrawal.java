package com.zillennium.utswap.datas.withDrawalData;

import com.zillennium.utswap.models.WithDrawalHistory.WithDrawalHistory;

import java.util.ArrayList;

public class DataWithDrawal {
    public static ArrayList<WithDrawalHistory> LIST_OF_WITHDRAWAL_HISTORY() {
        ArrayList<WithDrawalHistory> listWithdraw = new ArrayList<>();

        listWithdraw.add(new WithDrawalHistory("TR202202-1646038558699791","2022-02-28 15:55:58","$ 10 000.00","$ 10 000.00","Bunpor Kang"));
        listWithdraw.add(new WithDrawalHistory("TR202202-1645806494386738","2022-02-25 23:28:14","$ 10 000.00","$ 10 000.00","Bunpor Kang"));

        return listWithdraw;
    }
}
