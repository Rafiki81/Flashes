package com.rafiki.flashes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.rafiki.flashes.R;
import com.rafiki.flashes.adapters.CommentAdapter;
import com.rafiki.flashes.model.Film;
import com.rafiki.flashes.model.FilmComment;
import com.rafiki.flashes.service.Service;
import com.rafiki.flashes.service.ServiceApi;
import com.rafiki.flashes.sharedResources.Shared;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsActivity extends AppCompatActivity implements CommentAdapter.ClickedItem {

    Film film;
    Toolbar toolbar;
    RecyclerView recyclerView;
    CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);
        Intent intent = getIntent();
        if(intent.getExtras() !=null){
            film = (Film) intent.getSerializableExtra("film");
            getAllCommentsById(film.getId());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
            recyclerView.setItemViewCacheSize(5);
            commentAdapter = new CommentAdapter(this::ClickedComment);
        }
    }

    public void getAllCommentsById(Integer id){

        String token = Shared.getInstance(getApplicationContext()).getUser().getAccessToken();
        String authHeader = "Bearer " + token;

        Call<List<FilmComment>> commentList = Service
                .getInstance()
                .createService(ServiceApi.class)
                .getAllCommentsByFilmId(authHeader, id);

        commentList.enqueue(new Callback<List<FilmComment>>() {
            @Override
            public void onResponse(Call<List<FilmComment>> call, Response<List<FilmComment>> response) {
                if(response.code() == 200){
                    List<FilmComment> commentsResponse = response.body();
                    Log.d("FLASHES", "Lista de Comentarios " + response.body().toString());
                    commentAdapter.setData(commentsResponse);
                    recyclerView.setAdapter(commentAdapter);
                }else{
                    Log.d("FLASHES", "Unknown error, please try again");
                }
            }

            @Override
            public void onFailure(Call<List<FilmComment>> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());

            }
        });
    }

    @Override
    public void ClickedComment(FilmComment comment) {
        startActivity(new Intent(this, CommentDetailActivity.class)
                .putExtra("comment", comment)
                .putExtra("film", film)
        );
    }
}