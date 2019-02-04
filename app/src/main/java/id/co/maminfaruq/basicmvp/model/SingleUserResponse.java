package id.co.maminfaruq.basicmvp.model;

import com.google.gson.annotations.SerializedName;

public class SingleUserResponse {

    @SerializedName("data")
    private UserData data;

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }
}
