package com.example.joblistingapplication.activities;

import android.os.Bundle;
import android.util.Log;

import com.example.joblistingapplication.R;
import com.example.joblistingapplication.fragments.BookmarksFragment;
import com.example.joblistingapplication.fragments.JobsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "API_RESPONSE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new JobsFragment()).commit();

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selected = null;

            // Use if-else instead of switch statement
            if (item.getItemId() == R.id.nav_jobs) {
                selected = new JobsFragment();
            } else if (item.getItemId() == R.id.nav_bookmarks) {
                selected = new BookmarksFragment();
            }

            // Replace the fragment dynamically
            getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            return true;
        });
        fetchData();

    }

    private void fetchData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://testapi.getlokalapp.com/common/jobs?page=1");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    int responseCode = connection.getResponseCode();
                    Log.d(TAG, "Response Code: " + responseCode);

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(connection.getInputStream()));
                        String inputLine;
                        StringBuilder response = new StringBuilder();

                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();

                        Log.d(TAG, "Response: " + response.toString());
                    } else {
                        Log.e(TAG, "GET request failed. Code: " + responseCode);
                    }

                } catch (Exception e) {
                    Log.e(TAG, "Error: " + e.getMessage(), e);
                }
            }
        }).start();
    }
}
