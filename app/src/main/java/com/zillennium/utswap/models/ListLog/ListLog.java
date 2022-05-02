package com.zillennium.utswap.models.ListLog;

public class ListLog {
    int id;
    String operatingtime, actionRemark, status;

    public ListLog(int id, String operatingtime, String actionRemark, String status) {
        this.id = id;
        this.operatingtime = operatingtime;
        this.actionRemark = actionRemark;
        this.status = status;
    }
}
