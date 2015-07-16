package com.tyndallm.producthunt.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tyndallm.producthunt.R;
import com.tyndallm.producthunt.Utils.Utils;
import com.tyndallm.producthunt.adapters.ProductListAdapter;
import com.tyndallm.producthunt.api.ApiUtils;
import com.tyndallm.producthunt.data.ProductResponse;

import org.joda.time.DateTime;

import java.util.Collections;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class DailyFragment extends Fragment implements Callback<ProductResponse>, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = DailyFragment.class.getSimpleName();
    private static final String ARG_DATE = "arg_date";

    public static DailyFragment newInstance(String date) {
        Bundle args = new Bundle();
        args.putString(ARG_DATE, date);

        DailyFragment dailyFragment = new DailyFragment();
        dailyFragment.setArguments(args);

        return dailyFragment;
    }

    private RecyclerView productList;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily, container, false);
        productList = (RecyclerView) view.findViewById(R.id.productList);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        if (Utils.isAndroid5()) {
            swipeRefreshLayout.setProgressViewOffset(false, 0,100);
        }
        TextView todaysDate = (TextView) view.findViewById(R.id.today);

        setupTodaysDate(todaysDate);
        setupRecyclerView(productList);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onRefresh();
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void success(ProductResponse productResponse, Response response) {
        Log.d(TAG, "products retrieved: " + productResponse.getProductList().size());
        if (isAdded()) {
            swipeRefreshLayout.setRefreshing(false);
            productList.setVisibility(View.VISIBLE);
            Collections.sort(productResponse.getProductList());
            ProductListAdapter productListAdapter = (ProductListAdapter) productList.getAdapter();
            productListAdapter.updateProducts(productResponse.getProductList());
        }

    }

    @Override
    public void failure(RetrofitError error) {
        Log.e(TAG, "Error:" + error.getLocalizedMessage());
    }


    private void setupTodaysDate(TextView todaysDate) {
        DateTime now = DateTime.now();
        todaysDate.setText("Today, " + now.toString("MMMM") + " " + now.toString("d"));
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new ProductListAdapter(getActivity()));
    }

    @Override
    public void onRefresh() {
        ApiUtils.getProductHuntService().getDailyProductHunts(this);
        productList.setVisibility(View.GONE);
    }
}
