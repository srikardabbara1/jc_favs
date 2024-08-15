package edu.uchicago.gerber.favs.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class FavBusiness {

    @SerializedName("sessionEmail")
    @Expose
    private String sessionEmail;

    @SerializedName("businessId")
    @Expose
    private String businessId;

    @SerializedName("businessName")
    @Expose
    private String businessName;

    public FavBusiness(YelpResponse.Business business, String sessionEmail) {
        this.sessionEmail = sessionEmail;
        this.businessId = business.getId();
        this.businessName = business.getName();
    }

    public String getSessionEmail() {
        return sessionEmail;
    }

    public void setSessionEmail(String sessionEmail) {
        this.sessionEmail = sessionEmail;
    }

    public JsonObject toJson() {
        Gson gson = new Gson();
        return gson.toJsonTree(this).getAsJsonObject();
    }
}
