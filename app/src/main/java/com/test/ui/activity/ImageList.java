package com.test.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.test.R;
import com.test.ui.adapter.ImageListAdapter;
import com.test.ui.api.RetrofitClient;
import com.test.ui.model.ImageItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageList extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recyclerView;
    ImageListAdapter imageListAdapter;
    ArrayList<ImageItem> list;
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(this);
        list = new ArrayList<>();
        getImageList();
    }
    private void getImageList() {
        Call<ArrayList<ImageItem>> call = RetrofitClient.getInstance().getApi().getAllImageList();
        call.enqueue(new Callback<ArrayList<ImageItem>>() {
            @Override
            public void onResponse(Call<ArrayList<ImageItem>> call, Response<ArrayList<ImageItem>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    for (int i = 0; i < list.size(); i++) {
                        LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layout);
                        imageListAdapter = new ImageListAdapter(getApplicationContext(), list);
                        recyclerView.setAdapter(imageListAdapter);
                        recyclerView.setNestedScrollingEnabled(false);
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<ImageItem>> call, Throwable t) {
                Toast.makeText(ImageList.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onRefresh() {
        getImageList();
        imageListAdapter.notifyDataSetChanged();
        refreshLayout.setRefreshing(false);
    }
}