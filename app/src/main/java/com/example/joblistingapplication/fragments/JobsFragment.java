package com.example.joblistingapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.joblistingapplication.R;
import com.example.joblistingapplication.adapters.JobAdapter;
import com.example.joblistingapplication.api.JobApi;
import com.example.joblistingapplication.api.RetrofitClient;
import com.example.joblistingapplication.models.Job;
import com.example.joblistingapplication.utils.PaginationScrollListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobsFragment extends Fragment {
    private RecyclerView recyclerView;
    private JobAdapter adapter;
    private ProgressBar progressBar;
    private List<Job> jobList = new ArrayList<>();
    private int currentPage = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;

    public JobsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jobs, container, false);

        recyclerView = view.findViewById(R.id.jobsRecyclerView);
        progressBar = view.findViewById(R.id.jobsProgressBar);

        adapter = new JobAdapter(getContext(), jobList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        jobList.add(new Job("Software Developer", "Bangalore", "₹50,000", "1234567890", "Software developer job"));
        jobList.add(new Job("Software Testing", "Hyderabad", "₹40,000", "9876543210", "QA testing job"));
        jobList.add(new Job("SDE 1", "Bangalore", "₹80,000", "1234567890", "Software developer job"));
        jobList.add(new Job("Database Engineer", "Bangalore", "₹1,10,000", "1234567890", "Software developer job"));
        jobList.add(new Job("UI /UX Developer", "Bangalore", "₹80,000", "1234567890", "Software developer job"));
        jobList.add(new Job("Flutter Developer", "Pune", "₹80,000", "1234567890", "Software developer job"));
        jobList.add(new Job("Cloud Architect", "Mumbai", "₹80,000", "1234567890", "Software Engineer"));
        jobList.add(new Job("Cloud Practitioner", "Noida", "₹80,000", "1234567890", "Software Engineer"));

        adapter.notifyDataSetChanged();

        recyclerView.addOnScrollListener(new PaginationScrollListener((LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void loadMoreItems() {
                isLoading = true;
                currentPage++;
                fetchJobs(currentPage);
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });



        fetchJobs(currentPage);

        return view;
    }

    private void fetchJobs(int page) {
        progressBar.setVisibility(View.VISIBLE);
        JobApi api = RetrofitClient.getInstance().create(JobApi.class);
        api.getJobs(page).enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                progressBar.setVisibility(View.GONE);
                isLoading = false;
                if (response.isSuccessful() && response.body() != null) {
                    List<Job> jobs = response.body();
                    jobList.addAll(jobs); // Add the new jobs to the list
                    adapter.notifyDataSetChanged();
                    isLastPage = jobs.size() < 10; // Check if this is the last page
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                isLoading = false;

                // Check if the fragment is added to an activity before showing the Toast
                if (getActivity() != null) {
                    Toast.makeText(getActivity(), "Error loading jobs", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    }


