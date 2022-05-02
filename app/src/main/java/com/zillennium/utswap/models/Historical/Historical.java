package com.zillennium.utswap.models.Historical;

public class Historical {
    int logo;
    String nameSub, date, value,volume,price,settlement,utBalance;

    public Historical(int logo, String nameSub, String date, String value, String volume, String price, String settlement, String utBalance) {
        this.logo = logo;
        this.nameSub = nameSub;
        this.date = date;
        this.value = value;
        this.volume = volume;
        this.price = price;
        this.settlement = settlement;
        this.utBalance = utBalance;
    }

    public int getLogo() {
        return logo;
    }

    public String getNameSub() {
        return nameSub;
    }

    public String getDate() {
        return date;
    }

    public String getValue() {
        return value;
    }

    public String getVolume() {
        return volume;
    }

    public String getPrice() {
        return price;
    }

    public String getSettlement() {
        return settlement;
    }

    public String getUtBalance() {
        return utBalance;
    }
}
