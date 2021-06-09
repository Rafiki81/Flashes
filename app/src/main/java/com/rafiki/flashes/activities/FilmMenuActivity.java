package com.rafiki.flashes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rafiki.flashes.R;

public class FilmMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_menu);
        setUpView();
    }

    private void setUpView(){
        Button btnCreateFilm = findViewById(R.id.btnCreateFilm);
        Button btnListFilms = findViewById(R.id.btnListFilm);
        btnCreateFilm.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), CreateFilmActivity.class)));
        btnListFilms.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), FilmsActivity.class)));
    }
}