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
import com.rafiki.flashes.model.FilmComment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentAdapterVH> {

    private List<FilmComment> commentList;
    private final ClickedItem clickedItem;

    public CommentAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<FilmComment> commentList) {
        this.commentList = commentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentAdapterVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_comment,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapterVH holder, int position) {

        FilmComment comment = commentList.get(position);
        String title = comment.getTitle();
        String userName = comment.getUserName();
        float note = comment.getNote();
        holder.useNameText.setText(userName);
        holder.title.setText(title);
        holder.ratingBar.setRating(note);
        holder.title.setOnClickListener(view -> clickedItem.ClickedComment(comment));

    }

    public interface ClickedItem{
        public void ClickedComment(FilmComment comment);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class CommentAdapterVH extends RecyclerView.ViewHolder {

        TextView title;
        TextView useNameText;
        RatingBar ratingBar;

        public CommentAdapterVH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitleComment);
            useNameText = itemView.findViewById(R.id.tvUser);
            ratingBar = itemView.findViewById(R.id.stCommentNote);
        }
    }
}
