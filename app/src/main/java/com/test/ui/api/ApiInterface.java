package com.test.ui.api;

import com.test.ui.model.ImageItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/v2/list?page=2&limit=20")
Call<ArrayList<ImageItem>>getAllImageList();

}
