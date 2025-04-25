package com.example.joblistingapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.joblistingapplication.R;
import com.example.joblistingapplication.database.AppDatabase;
import com.example.joblistingapplication.database.Bookmark;

public class JobDetailActivity extends AppCompatActivity {

    TextView title, location, salary, phone, description;
    Button bookmarkBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_job_detail);

        title = findViewById(R.id.detailTitle);
        location = findViewById(R.id.detailLocation);
        salary = findViewById(R.id.detailSalary);
        phone = findViewById(R.id.detailPhone);
        description = findViewById(R.id.detailDescription);
        bookmarkBtn = findViewById(R.id.bookmarkButton);

        Intent intent = getIntent();
        String titleText = intent.getStringExtra("title");
        String locationText = intent.getStringExtra("location");
        String salaryText = intent.getStringExtra("salary");
        String phoneText = intent.getStringExtra("phone");
        String descText = intent.getStringExtra("description");


        if (title != null && location != null && salary != null && phone != null && description != null) {


            TextView jobTitleTextView = findViewById(R.id.detailTitle);
            TextView jobLocationTextView = findViewById(R.id.detailLocation);
            TextView jobSalaryTextView = findViewById(R.id.detailSalary);
            TextView jobPhoneTextView = findViewById(R.id.detailPhone);
            TextView jobDescriptionTextView = findViewById(R.id.detailDescription);

            jobTitleTextView.setText(titleText);
            jobLocationTextView.setText(locationText);
            jobSalaryTextView.setText("₹" + salaryText);
            jobPhoneTextView.setText("+91" + phoneText);
            jobDescriptionTextView.setText(descText);
        }else {
            Toast.makeText(this, "Error loading job details", Toast.LENGTH_SHORT).show();

        }




        bookmarkBtn.setOnClickListener(v -> {
            Bookmark bookmark = new Bookmark(titleText, locationText, salaryText, phoneText, descText);
            AppDatabase.getInstance(this).bookmarkDao().insert(bookmark);
            Toast.makeText(this, "Bookmarked!", Toast.LENGTH_SHORT).show();
        });




    }
}