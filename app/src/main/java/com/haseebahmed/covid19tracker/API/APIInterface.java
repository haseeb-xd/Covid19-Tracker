package com.haseebahmed.covid19tracker.API;

import com.haseebahmed.covid19tracker.Models.CountryData;
import com.haseebahmed.covid19tracker.Models.GlobalData;
import com.haseebahmed.covid19tracker.Models.GlobalHistoricalData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    static final String BASE_URL="https://disease.sh/";

    @GET("/v2/all")
     Call<GlobalData> getGlobalData();

    @GET("/v2/historical/all?lastdays=0")
    Call<GlobalHistoricalData> getGlobalHistoricalData();


    @GET("/v2/Countries")
    Call<List<CountryData>> geCountriesData();





}
