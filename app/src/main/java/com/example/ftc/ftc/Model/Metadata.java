package com.example.ftc.ftc.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Nawif on 4/6/18.
 */

public class Metadata {
    private String[] restaurantTypes={"Burger","Barbecue","Mexican","Chinese","Italian","Middle Eastern","Fast Food","Sandwich","Fried Chicken","Other"};

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getType() {


        return restaurantTypes[type];
    }

    public void setType(String type) {
        for (int i=0;i<restaurantTypes.length;i++){
            if(type.equalsIgnoreCase(restaurantTypes[i])) {
                this.type = i;
                return;
            }
        }
        this.type = restaurantTypes.length-1;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

}
