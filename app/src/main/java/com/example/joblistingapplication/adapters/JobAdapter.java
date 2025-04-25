package com.example.joblistingapplication.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.joblistingapplication.R;
import com.example.joblistingapplication.activities.JobDetailActivity;
import com.example.joblistingapplication.models.Job;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {
    private Context context;
    private List<Job> jobs;

    public JobAdapter(Context context, List<Job> jobs) {
        this.context = context;
        this.jobs = jobs;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_job, parent, false);
        return new JobViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job job = jobs.get(position);
        holder.title.setText(job.getTitle());
        holder.location.setText(job.getLocation());
        holder.salary.setText(job.getSalary());
        holder.phone.setText(job.getPhone());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, JobDetailActivity.class);
            intent.putExtra("title", job.getTitle());
            intent.putExtra("location", job.getLocation());
            intent.putExtra("salary", job.getSalary());
            intent.putExtra("phone", job.getPhone());
            intent.putExtra("description", job.getDescription());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {
        TextView title, location, salary, phone;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.jobTitle);
            location = itemView.findViewById(R.id.jobLocation);
            salary = itemView.findViewById(R.id.jobSalary);
            phone = itemView.findViewById(R.id.jobPhone);
        }
    }
}
