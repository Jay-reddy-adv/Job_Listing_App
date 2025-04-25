package com.example.joblistingapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.joblistingapplication.R;
import com.example.joblistingapplication.database.Bookmark;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder> {
    private Context context;
    private List<Bookmark> bookmarks;

    public BookmarkAdapter(Context context, List<Bookmark> bookmarks) {
        this.context = context;
        this.bookmarks = bookmarks;
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_job, parent, false);
        return new BookmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        Bookmark job = bookmarks.get(position);
        holder.title.setText(job.title);
        holder.location.setText(job.location);
        holder.salary.setText(job.salary);
        holder.phone.setText(job.phone);
    }

    @Override
    public int getItemCount() {
        return bookmarks.size();
    }

    public static class BookmarkViewHolder extends RecyclerView.ViewHolder {
        TextView title, location, salary, phone;

        public BookmarkViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.jobTitle);
            location = itemView.findViewById(R.id.jobLocation);
            salary = itemView.findViewById(R.id.jobSalary);
            phone = itemView.findViewById(R.id.jobPhone);
        }
    }
}
