package com.haseebahmed.covid19tracker.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class GlobalHistoricalData {


    @SerializedName("cases")
    Map<String,Long> caseMap;

    @SerializedName("deaths")
    Map<String,Long> deathMap;

    @SerializedName("recovered")
    Map<String,Long> recoveredMap;

    public GlobalHistoricalData(Map<String, Long> caseMap, Map<String, Long> deathMap, Map<String, Long> recoveredMap) {
        this.caseMap = caseMap;
        this.deathMap = deathMap;
        this.recoveredMap = recoveredMap;
    }

    public Map<String, Long> getCaseMap() {
        return caseMap;
    }

    public void setCaseMap(Map<String, Long> caseMap) {
        this.caseMap = caseMap;
    }

    public Map<String, Long> getDeathMap() {
        return deathMap;
    }

    public void setDeathMap(Map<String, Long> deathMap) {
        this.deathMap = deathMap;
    }

    public Map<String, Long> getRecoveredMap() {
        return recoveredMap;
    }

    public void setRecoveredMap(Map<String, Long> recoveredMap) {
        this.recoveredMap = recoveredMap;
    }
}
