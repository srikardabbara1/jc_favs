package edu.uchicago.gerber.favs.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class YelpResponse {

    @SerializedName("total")
    @Expose
    private int total;

    @SerializedName("businesses")
    @Expose
    private List<Business> businesses = null;

    @SerializedName("region")
    @Expose
    private Region region;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public static class Business {

        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("rating")
        @Expose
        private Double rating;

        @SerializedName("location")
        @Expose
        private Location location;

        @SerializedName("image_url")
        @Expose
        private String imageUrl;

        @SerializedName("url")
        @Expose
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getRating() {
            return rating;
        }

        public void setRating(Double rating) {
            this.rating = rating;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class Location {

        @SerializedName("address1")
        @Expose
        private String address1;

        @SerializedName("city")
        @Expose
        private String city;

        @SerializedName("state")
        @Expose
        private String state;

        @SerializedName("zip_code")
        @Expose
        private String zipCode;

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }

    public static class Region {

        @SerializedName("center")
        @Expose
        private Center center;

        public Center getCenter() {
            return center;
        }

        public void setCenter(Center center) {
            this.center = center;
        }
    }

    public static class Center {

        @SerializedName("latitude")
        @Expose
        private Double latitude;

        @SerializedName("longitude")
        @Expose
        private Double longitude;

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }
    }
}
