package com.example.lloyd.Ukite;

/**
 * Created by lloyd on 01-May-18.
 */

public class LocationItem {
    private String mImageUrl;
    private String mWindUrl;
    private String mSwellUrl;
    private String mLocation;
    private int mSpeed;
    private String mDirection;
    private int mTemp;

    public LocationItem(String imageUrl, String location, int speed, String direction, int temperature, String windURL, String swellURL) {
        mImageUrl = imageUrl;
        mLocation = location;
        mSpeed = speed;
        mTemp = temperature;
        mDirection = direction;
        mSwellUrl = swellURL;
        mWindUrl = windURL;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getLocation() {
        return mLocation;
    }

    public int getSpeed() {
        return mSpeed;
    }

    public String getDirection() {
        return mDirection;
    }

    public int getTemp() {
        return mTemp;
    }

    public String getWindUrl() {
        return mWindUrl;
    }

    public String getSwellUrl() {
        return mSwellUrl;
    }

}
