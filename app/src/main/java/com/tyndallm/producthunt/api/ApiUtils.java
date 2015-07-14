package com.tyndallm.producthunt.api;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tyndallm.producthunt.BuildConfig;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public abstract class ApiUtils {

    private static ProductHuntService productHuntService;

    protected ApiUtils() {

    }

    public static ProductHuntService getProductHuntService() {
        if (productHuntService == null) {
            productHuntService = createProductHuntService();
        }

        return productHuntService;
    }

    private static ProductHuntService createProductHuntService() {
        return new RestAdapter.Builder()
                .setEndpoint(ApiConstants.PRODUCT_HUNT_API_BASE_URL)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {

                        // Add API key header, if available
                        if (!TextUtils.isEmpty(BuildConfig.PRODUCT_HUNT_API_KEY)) {
                            request.addHeader("Authorization", "Bearer " + BuildConfig.PRODUCT_HUNT_API_KEY);
                        }
                    }
                })
                .setConverter(new GsonConverter(createGson()))
                .build().create(ProductHuntService.class);
    }

    private static Gson createGson() {
        return new GsonBuilder().create();
    }
}
