package com.tyndallm.producthunt.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tyndallm.producthunt.R;
import com.tyndallm.producthunt.activities.ProductActivity;
import com.tyndallm.producthunt.data.ProductPost;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<ProductPost> items = new ArrayList<ProductPost>();

    public ProductListAdapter(Context context) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_product_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(row);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final ProductPost currentProduct = items.get(position);
        viewHolder.nameTextView.setText(currentProduct.getName());
        viewHolder.headlineTextView.setText(currentProduct.getTagline());
        viewHolder.voteTextView.setText(String.valueOf(currentProduct.getVotesCount()));
        Picasso.with(viewHolder.userImageView.getContext()).load(currentProduct.getUser().getImage().getOriginal().toString()).into(viewHolder.userImageView);
//        Glide.with(viewHolder.userImageView.getContext())
//                .load(currentProduct.getUser().getImage().getOriginal().toString())
//                .fitCenter()
//                .into(viewHolder.userImageView);

        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra(ProductActivity.ARG_PRODUCT, currentProduct);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateProducts(List<ProductPost> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public String mBoundString;

        public final View rootView;
        public final TextView nameTextView;
        public final TextView headlineTextView;
        public final TextView voteTextView;
        public final ImageView userImageView;

        public ViewHolder(View view) {
            super(view);
            rootView = view;
            nameTextView = (TextView) rootView.findViewById(R.id.name);
            headlineTextView = (TextView) rootView.findViewById(R.id.headline);
            voteTextView = (TextView) rootView.findViewById(R.id.votes);
            userImageView = (ImageView) rootView.findViewById(R.id.avatar);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + nameTextView.getText();
        }
    }

}
