package com.example.joblistingapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.joblistingapplication.R;
import com.example.joblistingapplication.adapters.BookmarkAdapter;
import com.example.joblistingapplication.database.AppDatabase;
import com.example.joblistingapplication.database.Bookmark;

import java.util.ArrayList;
import java.util.List;

public class BookmarksFragment extends Fragment {
    RecyclerView recyclerView;
    BookmarkAdapter adapter;
    List<Bookmark> bookmarkList = new ArrayList<>();

    public BookmarksFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books, container, false);

        recyclerView = view.findViewById(R.id.bookmarkRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        bookmarkList = AppDatabase.getInstance(getContext()).bookmarkDao().getAll();
        adapter = new BookmarkAdapter(getContext(), bookmarkList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
