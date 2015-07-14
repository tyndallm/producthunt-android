package com.tyndallm.producthunt.data;

import android.os.Parcel;
import android.os.Parcelable;

public class UserImage implements Parcelable {

    private String original;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.original);
    }

    public UserImage() {
    }

    public String getOriginal() {
        return original;
    }

    protected UserImage(Parcel in) {
        this.original = in.readString();
    }

    public static final Parcelable.Creator<UserImage> CREATOR = new Parcelable.Creator<UserImage>() {
        public UserImage createFromParcel(Parcel source) {
            return new UserImage(source);
        }

        public UserImage[] newArray(int size) {
            return new UserImage[size];
        }
    };
}
