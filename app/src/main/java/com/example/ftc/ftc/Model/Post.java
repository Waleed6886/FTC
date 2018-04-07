package com.example.ftc.ftc.Model;
import android.location.Address;
import android.location.Geocoder;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.ftc.ftc.Model.Login.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmObject;

public class Post extends RealmObject implements Parcelable {

    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("metadata_key")
    @Expose
    private String metadataKey;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("post_id")
    @Expose
    private String postId;
    @SerializedName("status_id")
    @Expose
    private int statusId;
    @SerializedName("post_image")
    @Expose
    private String postImage;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("user")
    @Expose
    private User user;

    public Post() {
    }

    protected Post(Parcel in) {
        if (in.readByte() == 0) {
            latitude = null;
        } else {
            latitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            longitude = null;
        } else {
            longitude = in.readDouble();
        }
        description = in.readString();
        metadataKey = in.readString();
        metadata = in.readParcelable(Metadata.class.getClassLoader());
        postId = in.readString();
        statusId = in.readInt();
        postImage = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getDescription() {
        return description;
    }

    public String getMetadataKey() {
        return metadataKey;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public String getPostId() {
        return postId;
    }

    public int getStatusId() {
        return statusId;
    }

    public String getPostImage() {
        return postImage;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMetadataKey(String metadataKey) {
        this.metadataKey = metadataKey;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (latitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(latitude);
        }
        if (longitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(longitude);
        }
        dest.writeString(description);
        dest.writeString(metadataKey);
        dest.writeParcelable(metadata, flags);
        dest.writeString(postId);
        dest.writeInt(statusId);
        dest.writeString(postImage);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
    }
}