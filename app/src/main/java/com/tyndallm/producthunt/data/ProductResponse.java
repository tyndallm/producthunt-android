package com.tyndallm.producthunt.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductResponse implements Parcelable {

    @SerializedName("posts")
    private List<ProductPost> productList = new ArrayList<>();

    public List<ProductPost> getProductList() {
        return productList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.productList);
    }

    public ProductResponse() {
    }

    protected ProductResponse(Parcel in) {
        this.productList = new ArrayList<ProductPost>();
        in.readList(this.productList, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<ProductResponse> CREATOR = new Parcelable.Creator<ProductResponse>() {
        public ProductResponse createFromParcel(Parcel source) {
            return new ProductResponse(source);
        }

        public ProductResponse[] newArray(int size) {
            return new ProductResponse[size];
        }
    };
}
