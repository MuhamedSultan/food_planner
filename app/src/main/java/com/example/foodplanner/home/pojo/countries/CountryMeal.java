package com.example.foodplanner.home.pojo.countries;

import java.util.HashMap;
import java.util.Map;

public class CountryMeal {
    private String strArea;
    private String thumbArea;
    private static final Map<String, String> COUNTRY_CODE_MAP = new HashMap<>();

    static {
        COUNTRY_CODE_MAP.put("American", "us");
        COUNTRY_CODE_MAP.put("British", "gb");
        COUNTRY_CODE_MAP.put("Canadian", "ca");
        COUNTRY_CODE_MAP.put("Chinese", "cn");
        COUNTRY_CODE_MAP.put("Croatian", "hr");
        COUNTRY_CODE_MAP.put("Dutch", "nl");
        COUNTRY_CODE_MAP.put("Egyptian", "eg");
        COUNTRY_CODE_MAP.put("Filipino", "ph");
        COUNTRY_CODE_MAP.put("French", "fr");
        COUNTRY_CODE_MAP.put("Greek", "gr");
        COUNTRY_CODE_MAP.put("Indian", "in");
        COUNTRY_CODE_MAP.put("Irish", "ie");
        COUNTRY_CODE_MAP.put("Italian", "it");
        COUNTRY_CODE_MAP.put("Jamaican", "jm");
        COUNTRY_CODE_MAP.put("Japanese", "jp");
        COUNTRY_CODE_MAP.put("Kenyan", "ke");
        COUNTRY_CODE_MAP.put("Malaysian", "my");
        COUNTRY_CODE_MAP.put("Mexican", "mx");
        COUNTRY_CODE_MAP.put("Moroccan", "ma");
        COUNTRY_CODE_MAP.put("Polish", "pl");
        COUNTRY_CODE_MAP.put("Portuguese", "pt");
        COUNTRY_CODE_MAP.put("Russian", "ru");
        COUNTRY_CODE_MAP.put("Spanish", "es");
        COUNTRY_CODE_MAP.put("Thai", "th");
        COUNTRY_CODE_MAP.put("Tunisian", "tn");
        COUNTRY_CODE_MAP.put("Turkish", "tr");
        COUNTRY_CODE_MAP.put("Ukrainian", "ua");
        COUNTRY_CODE_MAP.put("Vietnamese", "vn");
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String value) {
        this.strArea = value;
    }

    public String getThumbArea() {
        String countryCode = COUNTRY_CODE_MAP.getOrDefault(strArea, "unknown");
        thumbArea = "https://www.themealdb.com/images/icons/flags/big/64/" + countryCode + ".png";
        return thumbArea;
    }

    public void setThumbArea(String value) {
        this.thumbArea = value;
    }
}