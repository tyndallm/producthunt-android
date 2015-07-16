package com.tyndallm.producthunt.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tyndallm.producthunt.R;
import com.tyndallm.producthunt.data.ProductPost;
import com.tyndallm.producthunt.interfaces.FragmentController;

public class ProductActivity extends AppCompatActivity implements FragmentController{
    public static final String ARG_PRODUCT = "arg_product";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent intent = getIntent();
        ProductPost productPost = intent.getParcelableExtra(ARG_PRODUCT);

        String title = "Detail";
        if (productPost != null) {
            title = productPost.getName();
        }

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);


//        CollapsingToolbarLayout collapsingToolbar =
//                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle(title);
//
//        loadBackdrop(productPost);
    }

    @Override
    public void changeFragment(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        if (addToBackstack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

//    private void loadBackdrop(ProductPost product) {
//        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
//        Glide.with(this).load(product.getScreenshotUrl().getLargeUrl().toString()).centerCrop().into(imageView);
//    }

}
