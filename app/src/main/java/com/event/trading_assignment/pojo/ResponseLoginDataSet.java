package com.event.trading_assignment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseLoginDataSet {




    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;

    public DataLoginDataSet getData() {
        return data;
    }

    public void setData(DataLoginDataSet data) {
        this.data = data;
    }

    @SerializedName("data")
    @Expose
    private DataLoginDataSet data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
