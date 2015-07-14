package com.tyndallm.producthunt.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tyndallm.producthunt.R;
import com.tyndallm.producthunt.data.ProductPost;

public class ProductFragment extends Fragment {

    private static final String TAG = ProductFragment.class.getSimpleName();
    private static final String ARG_PRODUCT = "arg_product";

    public static ProductFragment newInstance(ProductPost productPost) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_PRODUCT, productPost);

        ProductFragment productFragment = new ProductFragment();
        productFragment.setArguments(args);

        return productFragment;
    }

    private TextView name, tagline;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        name = (TextView) view.findViewById(R.id.name);
        tagline = (TextView) view.findViewById(R.id.tagline);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProductPost productPost = getArguments().getParcelable(ARG_PRODUCT);

        if (productPost != null) {
            name.setText(productPost.getName());
            tagline.setText(productPost.getTagline());
        }

        // TODO: make network request
        // ApiUtils.getProductHuntService().getDailyProductHunts(this);
    }


}
