package com.tyndallm.producthunt.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProductPost implements Parcelable {

    private String id;
    private String name;
    private String tagline;
    private String day;

    @SerializedName("created_at")
    private String createdAt;

    private Boolean featured;

    @SerializedName("comments_count")
    private int commentsCount;

    @SerializedName("votes_count")
    private int votesCount;

    @SerializedName("discussion_url")
    private String discussionUrl;

    @SerializedName("redirect_url")
    private String redirectUrl;

    @SerializedName("screenshot_url")
    private ScreenshotUrl screenshotUrl;

    private User user;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.tagline);
        dest.writeString(this.day);
        dest.writeString(this.createdAt);
        dest.writeValue(this.featured);
        dest.writeInt(this.commentsCount);
        dest.writeInt(this.votesCount);
        dest.writeString(this.discussionUrl);
        dest.writeString(this.redirectUrl);
        dest.writeParcelable(this.screenshotUrl, 0);
        dest.writeParcelable(this.user, 0);
    }

    public ProductPost() {
    }

    protected ProductPost(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.tagline = in.readString();
        this.day = in.readString();
        this.createdAt = in.readString();
        this.featured = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.commentsCount = in.readInt();
        this.votesCount = in.readInt();
        this.discussionUrl = in.readString();
        this.redirectUrl = in.readString();
        this.screenshotUrl = in.readParcelable(ScreenshotUrl.class.getClassLoader());
        this.user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Parcelable.Creator<ProductPost> CREATOR = new Parcelable.Creator<ProductPost>() {
        public ProductPost createFromParcel(Parcel source) {
            return new ProductPost(source);
        }

        public ProductPost[] newArray(int size) {
            return new ProductPost[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTagline() {
        return tagline;
    }

    public String getDay() {
        return day;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public int getVotesCount() {
        return votesCount;
    }

    public String getDiscussionUrl() {
        return discussionUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public ScreenshotUrl getScreenshotUrl() {
        return screenshotUrl;
    }

    public User getUser() {
        return user;
    }
}
