package com.example.ricardo.assigment3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    ArrayList<Cake> datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = (RecyclerView) findViewById(R.id.recycler_cake);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        datos = new ArrayList<Cake>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CakeAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CakeAPI api = retrofit.create(CakeAPI.class);
        Call<List<Cake>> call = api.getCakes();
        call.enqueue(new Callback<List<Cake>>() {

            @Override
            public void onResponse(Call<List<Cake>> call, Response<List<Cake>> response) {
                List<Cake> cakes = response.body();
                System.out.println("hello");
                /*for(int i=0;i<=cakes.size();i++){
                    datos.add(new Cake(cakes.get(i).getTitle());
                }*/
                AdapterCake adapter = new AdapterCake(cakes);
                recycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Cake>> call, Throwable t) {
                System.out.println("error ------> "+t.getMessage());
            }
        });

    }
}
