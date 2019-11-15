package com.event.trading_assignment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {


    @SerializedName("reset_password")
    @Expose
    private Boolean resetPassword;
    @SerializedName("products")
    @Expose
    private List<String> products = null;
    @SerializedName("order_types")
    @Expose
    private List<String> orderTypes = null;
    @SerializedName("mws")
    @Expose
    private List<Mw> mws = null;
    @SerializedName("mw_names")
    @Expose
    private List<String> mwNames = null;
    @SerializedName("login_id")
    @Expose
    private String loginId;
    @SerializedName("last_login")
    @Expose
    private String lastLogin;

    public ExchangeCode getExchangeCodes() {
        return exchangeCodes;
    }

    public void setExchangeCodes(ExchangeCode exchangeCodes) {
        this.exchangeCodes = exchangeCodes;
    }

    @SerializedName("exchange_codes")
    @Expose
    private ExchangeCode exchangeCodes;
    @SerializedName("auth_token")
    @Expose
    private String authToken;
    @SerializedName("answer_twofa")
    @Expose
    private Boolean answerTwofa;

    public Boolean getResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(Boolean resetPassword) {
        this.resetPassword = resetPassword;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public List<String> getOrderTypes() {
        return orderTypes;
    }

    public void setOrderTypes(List<String> orderTypes) {
        this.orderTypes = orderTypes;
    }

    public List<Mw> getMws() {
        return mws;
    }

    public void setMws(List<Mw> mws) {
        this.mws = mws;
    }

    public List<String> getMwNames() {
        return mwNames;
    }

    public void setMwNames(List<String> mwNames) {
        this.mwNames = mwNames;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Boolean getAnswerTwofa() {
        return answerTwofa;
    }

    public void setAnswerTwofa(Boolean answerTwofa) {
        this.answerTwofa = answerTwofa;
    }



}
