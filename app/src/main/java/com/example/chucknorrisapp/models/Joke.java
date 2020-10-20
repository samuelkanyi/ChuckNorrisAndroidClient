package com.example.chucknorrisapp.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Joke {
    @SerializedName("id")
    private String id;
    @SerializedName("url")
    private String url;
    @SerializedName("icon_url")
    private String iconUrl;
    @SerializedName("value")
    private String value;

    public Joke(String id, String url, String iconUrl, String value) {
        this.id = id;
        this.url = url;
        this.iconUrl = iconUrl;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
