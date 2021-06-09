package com.rafiki.flashes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.rafiki.flashes.R;
import com.rafiki.flashes.model.Film;
import com.rafiki.flashes.model.FilmComment;

public class CommentDetailActivity extends AppCompatActivity {

    TextView title;
    TextView user;
    TextView commentString;
    TextView filmTitle;
    RatingBar note;
    FilmComment comment;
    Film film;
    Button btListComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_detail);
        setUpView();
    }

    private void setUpView() {
        filmTitle = findViewById(R.id.topText);
        title = findViewById(R.id.tvCommentTitle);
        user = findViewById(R.id.tvUserName);
        note = findViewById(R.id.ratingStars);
        commentString = findViewById(R.id.tvComment);
        btListComments = findViewById(R.id.btnListComment);

        Intent intent = getIntent();
        if(intent.getExtras() !=null){
            comment = (FilmComment) intent.getSerializableExtra("comment");
            film = (Film) intent.getSerializableExtra("film");
            String filmTitleData = film.getTitle();
            String titleData = comment.getTitle();
            Integer noteData = comment.getNote();
            String userData = comment.getUserName();
            String commentData = comment.getComment();

            title.setText(filmTitleData);
            note.setRating(noteData);
            title.setText(titleData);
            user.setText(userData);
            commentString.setText(commentData);

            btListComments.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), CommentsActivity.class)));
        }

    }
}