package com.example.hrm;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hrm.R;
import com.example.hrm.UserAdapter;
import com.example.hrm.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private ArrayList<User> userList;
    private RecyclerView rvUsers;
    private static final String TAG = "MainActivity";
    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView mResponseTv;
    private StoreUser webService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configure all views

//        configureToolBar();

        configureDrawerLayout();

        configureNavigationView();

        init();
        generateData();
        setData();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        //(renders) the menu in the main activity
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_user:
                setContentView(R.layout.add_user);
                //create user process
                final EditText nameEt = (EditText) findViewById(R.id.et_name);
                final EditText emailEt = (EditText) findViewById(R.id.et_email);
                Button submitBtn = (Button) findViewById(R.id.btn_submit);
                mResponseTv = (TextView) findViewById(R.id.tv_response);

                webService = WebServiceClient.getClient().create(StoreUser.class);

                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = nameEt.getText().toString().trim();
                        String email = emailEt.getText().toString().trim();
                        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email)) {
                            sendPost(name, email);
                            mResponseTv.setVisibility(View.VISIBLE);
                        }
                    }
                });

                // Configure all views

                //  configureToolBar();

                configureDrawerLayout();

                configureNavigationView();

            case R.id.action_show_menu:
                // Handle back click to close menu
                if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    this.drawerLayout.closeDrawer(GravityCompat.START);
                } else  {
                this.drawerLayout.openDrawer(GravityCompat.START);
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
//
//
    public void sendPost(String name, String email) {
        Call<Post> call = webService.savePost(name, email);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(response.isSuccessful()) {
                    setContentView(R.layout.activity_main);
                    // Configure all views

                    //  configureTool//  Bar();
                    configureDrawerLayout();

                    configureNavigationView();
                    init();
                    generateData();
                    setData();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                showResponse("Error thrown"+t);
            }
        });
    }

    public void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }

    @Override
    public void onBackPressed() {
        // Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle Navigation Item Click
        int id = item.getItemId();

        switch (id){
            case R.id.activity_main_drawer_news :
                setContentView(R.layout.activity_main);
                // Configure all views

                //  configureTool//  Bar();
                configureDrawerLayout();

                configureNavigationView();
                init();
                generateData();
                setData();

                break;
            case R.id.activity_main_drawer_profile:
                setContentView(R.layout.room_user);
                // Configure all views

                //  configureTool//  Bar();
                configureDrawerLayout();

                configureNavigationView();

                //create user process
                final EditText nameEt = (EditText) findViewById(R.id.user_room_txt_name);
                Button submitBtn = (Button) findViewById(R.id.user_room_submit);
                mResponseTv = (TextView) findViewById(R.id.user_room_response);



                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = nameEt.getText().toString().trim();
                        if(!TextUtils.isEmpty(name)) {
                            mResponseTv.setVisibility(View.VISIBLE);
                            mResponseTv.setText("Added "+name);
//                            roomSend(name);
                        }
                    }
                });

                break;
            case R.id.activity_main_drawer_settings:
                setContentView(R.layout.activity_about);
                // Configure all views

                //  configureToolBar();

                configureDrawerLayout();

                configureNavigationView();
                break;
            default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
    //room send
    public void roomSend(String name){
        UserRoomDatabase appDb = UserRoomDatabase.getInstance(getApplicationContext());
        UserRoom user = new UserRoom(name);
        appDb.userRoomDao.insertUserRoom(user);
    }
    // Configure Toolbar
    private void configureToolBar(){
        this.toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    // Configure Drawer Layout
    private void configureDrawerLayout(){
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // Configure NavigationView
    private void configureNavigationView(){
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
    }

    private void setData() {
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        rvUsers.setAdapter(new UserAdapter(this, userList));
    }
}