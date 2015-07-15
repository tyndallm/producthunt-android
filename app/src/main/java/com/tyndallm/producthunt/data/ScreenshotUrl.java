package com.tyndallm.producthunt.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ScreenshotUrl implements Parcelable {

    @SerializedName("300px")
    private String smallUrl;

    @SerializedName("850px")
    private String largeUrl;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.smallUrl);
        dest.writeString(this.largeUrl);
    }

    public ScreenshotUrl() {
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    protected ScreenshotUrl(Parcel in) {
        this.smallUrl = in.readString();
        this.largeUrl = in.readString();
    }

    public static final Parcelable.Creator<ScreenshotUrl> CREATOR = new Parcelable.Creator<ScreenshotUrl>() {
        public ScreenshotUrl createFromParcel(Parcel source) {
            return new ScreenshotUrl(source);
        }

        public ScreenshotUrl[] newArray(int size) {
            return new ScreenshotUrl[size];
        }
    };
}
