package com.example.ricardo.assigment3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CakeAPI {

    String BASE_URL = "https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/";
    @GET("8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json")
    Call<List<Cake>> getCakes();

}
