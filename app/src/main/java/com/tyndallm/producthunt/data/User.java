package com.tyndallm.producthunt.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

    private String id;
    private String name;
    private String headline;

    @SerializedName("created_at")
    private String createdAt;

    private String username;

    @SerializedName("website_url")
    private String websiteUrl;

    @SerializedName("image_url")
    private UserImage image;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.headline);
        dest.writeString(this.createdAt);
        dest.writeString(this.username);
        dest.writeString(this.websiteUrl);
        dest.writeParcelable(this.image, 0);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.headline = in.readString();
        this.createdAt = in.readString();
        this.username = in.readString();
        this.websiteUrl = in.readString();
        this.image = in.readParcelable(UserImage.class.getClassLoader());;
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
