package com.rafiki.flashes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.rafiki.flashes.R;
import com.rafiki.flashes.model.Film;

public class FilmDetailActivity extends AppCompatActivity {

    TextView title;
    TextView director;
    TextView synopsis;
    TextView genre;
    TextView runningTime;
    RatingBar rating;
    Film film;
    Button btComment;
    Button btListComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        setUpView();
    }

    private void setUpView(){
        rating = findViewById(R.id.ratingStars);
        title = findViewById(R.id.topText);
        director = findViewById(R.id.tvDirector);
        synopsis = findViewById(R.id.tvSynopsis);
        genre = findViewById(R.id.tvGenre);
        runningTime = findViewById(R.id.tvRunningTime);
        btComment = findViewById(R.id.btnComment);
        btListComment = findViewById(R.id.btnListComment);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            film = (Film) intent.getSerializableExtra("film");

            String titleData = film.getTitle();
            String directorText = film.getDirector();
            String synopsisText = film.getSynopsis();
            String genreText = film.getGenre();
            String runningTimeText = film.getRunningTime().toString();

            rating.setRating(film.getNote().floatValue());
            title.setText(titleData);
            director.setText(directorText);
            synopsis.setText(synopsisText);
            genre.setText(genreText);
            runningTime.setText(runningTimeText);

            btComment.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), CreateCommentActivity.class).putExtra("film", film)));
            btListComment.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), CommentsActivity.class).putExtra("film", film)));
        }
    }
}