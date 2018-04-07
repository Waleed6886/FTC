package com.example.ftc.ftc.Model;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Nawif on 4/6/18.
 */

public class Metadata extends RealmObject implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("imgPath")
    @Expose
    private String imgPath;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("working_hours")
    @Expose
    private String workingHours;

    public Metadata() {
    }

    protected Metadata(Parcel in) {
        name = in.readString();
        imgPath = in.readString();
        if (in.readByte() == 0) {
            type = null;
        } else {
            type = in.readInt();
        }
        workingHours = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imgPath);
        if (type == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(type);
        }
        dest.writeString(workingHours);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Metadata> CREATOR = new Creator<Metadata>() {
        @Override
        public Metadata createFromParcel(Parcel in) {
            return new Metadata(in);
        }

        @Override
        public Metadata[] newArray(int size) {
            return new Metadata[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public Integer getType() {
        return type;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }
}
