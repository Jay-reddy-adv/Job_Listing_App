package com.example.joblistingapplication.api;


import com.example.joblistingapplication.models.Job;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JobApi {
    @GET("common/jobs")
    Call<List<Job>> getJobs(@Query("page") int page);
}
