package com.example.ricardo.assigment3;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    CompositeDisposable mCom;
    ArrayList<Cake> datos;
    CakeViewModel mCakeViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (RecyclerView) findViewById(R.id.recycler_cake);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mCakeViewModel = ViewModelProviders.of(this).get(CakeViewModel.class);//new CakeViewModel(this.getApplication());
        if(mCakeViewModel.getNewsResponseObservable().getValue() == null) System.out.println("well fuck");
        AdapterCake adapter = new AdapterCake(mCakeViewModel.getNewsResponseObservable().getValue());
        recycler.setAdapter(adapter);
        mCakeViewModel.getNewsResponseObservable().observe(this, new Observer<List<Cake>>() {
            @Override
            public void onChanged(final List<Cake> cakes) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(cakes);
            }
        });
        /*
        mCom = new CompositeDisposable();
        recycler = (RecyclerView) findViewById(R.id.recycler_cake);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        datos = new ArrayList<Cake>();
        CakeAPI retrofit = new Retrofit.Builder()
                .baseUrl(CakeAPI.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(CakeAPI.class);
        mCom.add(retrofit.getCakes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));


    }
    private void handleResponse(List<Cake> cake){

        datos = new ArrayList<>(cake);
        AdapterCake adapter = new AdapterCake(datos);
        recycler.setAdapter(adapter);


    }

    private void handleError(Throwable error) {

        Toast.makeText(this, "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }*/
    }
}
