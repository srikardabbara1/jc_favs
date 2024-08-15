package edu.uchicago.gerber.favs.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class FavItem extends Item {
    @SerializedName("sessionEmail")
    @Expose
    private String sessionEmail;

    public FavItem(Item item, String sessionEmail) {
        // Initialize the inherited fields using the Item's getter methods
        this.setKind(item.getKind());
        this.setId(item.getId());
        this.setEtag(item.getEtag());
        this.setSelfLink(item.getSelfLink());
        this.setVolumeInfo(item.getVolumeInfo());
        this.setSaleInfo(item.getSaleInfo());
        this.setAccessInfo(item.getAccessInfo());
        this.setSearchInfo(item.getSearchInfo());

        // Initialize the new field
        this.sessionEmail = sessionEmail;
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