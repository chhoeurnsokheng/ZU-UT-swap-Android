package com.zillennium.utswap.screens.setting.MyWallet;

import java.util.ArrayList;

public class MyWalletGroup {
    String selectDayGroup;
    ArrayList<MyWalletItem> selectItemGroup;

    public MyWalletGroup(String selectDayGroup, ArrayList<MyWalletItem> selectItemGroup) {
        this.selectDayGroup = selectDayGroup;
        this.selectItemGroup = selectItemGroup;
    }

    public String getSelectDayGroup() {
        return selectDayGroup;
    }

    public void setSelectDayGroup(String selectDayGroup) {
        this.selectDayGroup = selectDayGroup;
    }

    public ArrayList<MyWalletItem> getSelectItemGroup() {
        return selectItemGroup;
    }

    public void setSelectItemGroup(ArrayList<MyWalletItem> selectItemGroup) {
        this.selectItemGroup = selectItemGroup;
    }
}
