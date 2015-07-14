package com.tyndallm.producthunt.api;

import com.tyndallm.producthunt.data.ProductResponse;

import retrofit.Callback;
import retrofit.http.GET;

public interface ProductHuntService {

    @GET(ApiConstants.URL_PATH_DAILY_HUNTS)
    void getDailyProductHunts(Callback<ProductResponse> callback);
}
