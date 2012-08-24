package com.wallet.country;

import java.util.HashMap;

import com.wallet.application.Risk;

public class CountriesService implements CountriesAPI {

    /**
     * we will save in Hash Map the countries by their name as key of the map and their risk level will be the value
     */
    public static HashMap<String, Risk> countries = new HashMap<String, Risk>();

    /**
     * initialize CountriesService as put in the HashMap hey and value
     */
    
    public static void init() {
        countries.put("Bulgaria", Risk.MediumRisk);
        countries.put("Turkey", Risk.LowRisk);
        countries.put("USA", Risk.HighRisk);
        countries.put("Germany", Risk.MediumRisk);
        countries.put("Italy", Risk.MediumRisk);
        countries.put("Russia", Risk.LowRisk);
    }

    public Risk getCountriesRisk(String name) {
        if (countries.containsKey(name)) {
            return countries.get(name);
        } else {
        }
        return null;
    }

    public String changeRiskLevel(String name, String riskLevel) {
        if (countries.containsKey(name)) {
            countries.put(name, Risk.valueOf(riskLevel));
            return name + "'s risk level was changed";
        } else {
            return "This risk level was not changed.";
        }
    }
}
