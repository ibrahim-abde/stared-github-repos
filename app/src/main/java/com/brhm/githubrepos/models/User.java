package com.brhm.githubrepos.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("login")
    private String name;

    @SerializedName("avatar_url")
    private String avatarURL;

    public String getName() {
        return name;
    }

    public String getAvatarURL() {
        return avatarURL;
    }
}
