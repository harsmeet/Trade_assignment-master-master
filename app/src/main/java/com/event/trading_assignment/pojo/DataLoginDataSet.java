package com.event.trading_assignment.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataLoginDataSet {




    @SerializedName("questions")
    @Expose
    private List<String> questions = null;
    @SerializedName("question_ids")
    @Expose
    private List<Integer> questionIds = null;
    @SerializedName("first_time_login")
    @Expose
    private Boolean firstTimeLogin;

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public List<Integer> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Integer> questionIds) {
        this.questionIds = questionIds;
    }

    public Boolean getFirstTimeLogin() {
        return firstTimeLogin;
    }

    public void setFirstTimeLogin(Boolean firstTimeLogin) {
        this.firstTimeLogin = firstTimeLogin;
    }




}
