package com.wallet.country;

import java.io.Serializable;

import com.wallet.application.Risk;

public class Country implements Serializable {

    Risk riskCat;
    private String id;
    private String language;
    private boolean acceptable;
    private String riskCategory;

    public Country() {
    }

    public Country(String id, String language, boolean acceptable, String category) {
        super();
        this.id = id;
        this.language = language;
        this.acceptable = acceptable;
        this.riskCategory = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isAcceptable() {
        return acceptable;
    }

    public void setAcceptable(boolean acceptable) {
        this.acceptable = acceptable;
    }

    public String getRiskCategory() {
        return riskCategory;
    }

    public void setRiskCategory(String category) {
        this.riskCategory = category;
    }

}
