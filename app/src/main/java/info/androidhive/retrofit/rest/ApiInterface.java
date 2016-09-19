package info.androidhive.retrofit.rest;

import info.androidhive.retrofit.model.Bank;
import info.androidhive.retrofit.model.BanksResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("/api/Getbank/bank")
    Call<Bank> getBankDetails();

}
