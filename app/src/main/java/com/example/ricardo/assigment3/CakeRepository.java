package com.example.ricardo.assigment3;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CakeRepository {

        private CakeAPI cakeAPI;
        private static class SingletonHelper
        {
            private static final CakeRepository INSTANCE = new CakeRepository();
        }
        public static CakeRepository getInstance()
        {
            return SingletonHelper.INSTANCE;
        }
        public CakeRepository()
        {
            
            cakeAPI = new Retrofit.Builder()
                    .baseUrl(CakeAPI.BASE_URL)
                    //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(CakeAPI.class);
        }
        public LiveData<List<Cake>> getCakes()
        {
            final MutableLiveData<List<Cake>> data = new MutableLiveData<>();

            cakeAPI.getCakes()
                    .enqueue(new Callback<List<Cake>>()
                    {
                        @Override
                        public void onResponse(Call<List<Cake>> call, Response<List<Cake>> response)
                        {


                                data.setValue(response.body());

                        }

                        @Override
                        public void onFailure(Call<List<Cake>> call, Throwable t) {

                            data.setValue(null);
                        }
                    });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return data;
        }

}
