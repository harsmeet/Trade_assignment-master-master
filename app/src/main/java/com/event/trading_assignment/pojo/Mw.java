package com.event.trading_assignment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Mw {

    @SerializedName("mw1")
    @Expose
    private List<String> mw1 = null;
    @SerializedName("mw2")
    @Expose
    private List<String> mw2 = null;
    @SerializedName("mw3")
    @Expose
    private List<String> mw3 = null;
    @SerializedName("mw4")
    @Expose
    private List<String> mw4 = null;
    @SerializedName("mw5")
    @Expose
    private List<Object> mw5 = null;

    public List<String> getMw1() {
        return mw1;
    }

    public void setMw1(List<String> mw1) {
        this.mw1 = mw1;
    }

    public List<String> getMw2() {
        return mw2;
    }

    public void setMw2(List<String> mw2) {
        this.mw2 = mw2;
    }

    public List<String> getMw3() {
        return mw3;
    }

    public void setMw3(List<String> mw3) {
        this.mw3 = mw3;
    }

    public List<String> getMw4() {
        return mw4;
    }

    public void setMw4(List<String> mw4) {
        this.mw4 = mw4;
    }

    public List<Object> getMw5() {
        return mw5;
    }

    public void setMw5(List<Object> mw5) {
        this.mw5 = mw5;
    }


}
