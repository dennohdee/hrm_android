package com.example.hrm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.hrm.R;
import com.example.hrm.UserAdapter;
import com.example.hrm.User;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<User> userList;
    private RecyclerView rvUsers;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        generateData();
        setData();
    }
    
    private void init() {
        userList = new ArrayList<>();
        rvUsers = findViewById(R.id.rv_users);
    }

    private void setRecyclerView() {
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        rvUsers.setAdapter(new UserAdapter(this, userList));
    }

    private void generateData() {
        WebService webService = WebServiceClient.getClient().create(WebService.class);
        Call<UserData> call = webService.getUserData();
        call.enqueue(new Callback<UserData>() {
            
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
                progressBar.setVisibility(View.GONE);
                Log.d(TAG, "onResponse: " + response.code());
                assert response.body() != null : "Response Body Empty";
                userList = response.body().getUserData();
                UserAdapter userAdapter = new UserAdapter(MainActivity.this, userList);
                setRecyclerView();
            }

            public void onFailure(Call<UserData> call, Throwable t) {
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
                progressBar.setVisibility(View.GONE);
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
//        userList.add(new User("John Doe", "john@doe.com", "2010"));
//        userList.add(new User("Jane Doe", "jane@doe.com", "2010"));
//        userList.add(new User("June Doe", "june@doe.com", "2015"));
//        userList.add(new User("Jin Doe", "Steve McQueen", "2013"));
//        userList.add(new User("July Doe", "july@doe.com", "2014"));
//        userList.add(new User("Jusper Doe", "jusper@doe.com", "2013"));
//        userList.add(new User("GIna Doe", "gina@doe.com", "2016"));
//        userList.add(new User("Bill", "bill@doe.com", "2012"));
    }

    private void setData() {
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        rvUsers.setAdapter(new UserAdapter(this, userList));
    }
}