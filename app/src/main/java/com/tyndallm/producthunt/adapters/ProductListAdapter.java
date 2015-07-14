package com.tyndallm.producthunt.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tyndallm.producthunt.R;
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
        ProductPost currentProduct = items.get(position);
        viewHolder.nameTextView.setText(currentProduct.getName());
        viewHolder.headlineTextView.setText(currentProduct.getTagline());

//        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Context context = v.getContext();
//                Intent intent = new Intent(context, CheeseDetailActivity.class);
//                intent.putExtra(CheeseDetailActivity.EXTRA_NAME, holder.mBoundString);
//
//                context.startActivity(intent);
//            }
//        });
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

        public ViewHolder(View view) {
            super(view);
            rootView = view;
            nameTextView = (TextView) rootView.findViewById(R.id.name);
            headlineTextView = (TextView) rootView.findViewById(R.id.headline);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + nameTextView.getText();
        }
    }

}
