package com.rafiki.flashes.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rafiki.flashes.R;
import com.rafiki.flashes.model.Film;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmAdapterVH> {

    private List<Film> filmList;
    private final ClickedItem clickedItem;

    public FilmAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Film> filmList) {
        this.filmList = filmList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilmAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FilmAdapter.FilmAdapterVH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_film,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FilmAdapterVH holder, int position) {
        Film film = filmList.get(position);
        String title = film.getTitle();
        String genre = film.getGenre();
        float note = film.getNote().floatValue();
        holder.genre.setText(genre);
        holder.title.setText(title);
        holder.ratingBar.setRating(note);
        holder.title.setOnClickListener(view -> clickedItem.ClickedFilm(film));
    }

    public interface ClickedItem{
        public void ClickedFilm(Film film);
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public class FilmAdapterVH extends RecyclerView.ViewHolder {

        TextView title;
        TextView genre;
        RatingBar ratingBar;

        public FilmAdapterVH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            genre = itemView.findViewById(R.id.tvGenre);
            ratingBar = itemView.findViewById(R.id.stNote);


        }
    }
}
