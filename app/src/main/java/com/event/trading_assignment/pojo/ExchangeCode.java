package com.event.trading_assignment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExchangeCode {



    @SerializedName("NSE")
    @Expose
    private Integer nSE;
    @SerializedName("NFO")
    @Expose
    private Integer nFO;
    @SerializedName("MCX")
    @Expose
    private Integer mCX;
    @SerializedName("CDS")
    @Expose
    private Integer cDS;
    @SerializedName("BSE")
    @Expose
    private Integer bSE;

    public Integer getNSE() {
        return nSE;
    }

    public void setNSE(Integer nSE) {
        this.nSE = nSE;
    }

    public Integer getNFO() {
        return nFO;
    }

    public void setNFO(Integer nFO) {
        this.nFO = nFO;
    }

    public Integer getMCX() {
        return mCX;
    }

    public void setMCX(Integer mCX) {
        this.mCX = mCX;
    }

    public Integer getCDS() {
        return cDS;
    }

    public void setCDS(Integer cDS) {
        this.cDS = cDS;
    }

    public Integer getBSE() {
        return bSE;
    }

    public void setBSE(Integer bSE) {
        this.bSE = bSE;
    }


}
