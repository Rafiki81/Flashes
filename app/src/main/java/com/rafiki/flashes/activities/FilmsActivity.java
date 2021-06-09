package com.rafiki.flashes.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.rafiki.flashes.R;
import com.rafiki.flashes.adapters.FilmAdapter;
import com.rafiki.flashes.model.Film;
import com.rafiki.flashes.service.Service;
import com.rafiki.flashes.service.ServiceApi;
import com.rafiki.flashes.sharedResources.Shared;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsActivity extends AppCompatActivity implements FilmAdapter.ClickedItem {

    Toolbar toolbar;
    RecyclerView recyclerView;
    FilmAdapter filmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films);
        setUpView();
    }

    public void setUpView() {
        getAllFilms();
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        filmAdapter = new FilmAdapter(this::ClickedFilm);
    }

    public void getAllFilms(){

        String token = Shared.getInstance(getApplicationContext()).getUser().getAccessToken();
        String authHeader = "Bearer " + token;

        Call<List<Film>> filmList = Service
                .getInstance()
                .createService(ServiceApi.class)
                .listFilms(authHeader);

        filmList.enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if(response.code() == 200){
                    List<Film> bookResponse = response.body();
                    Log.d("FLASHES", "Film list " + response.body().toString());
                    filmAdapter.setData(bookResponse);
                    recyclerView.setAdapter(filmAdapter);
                }else{
                    Log.d("FLASHES", "Unknown error, please try again");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Film>> call, @NonNull Throwable t) {
                Log.e("failure",t.getLocalizedMessage());

            }
        });
    }

    @Override
    public void ClickedFilm(Film film) {
        startActivity(new Intent(this,FilmsActivity.class).putExtra("film", film));
    }
}