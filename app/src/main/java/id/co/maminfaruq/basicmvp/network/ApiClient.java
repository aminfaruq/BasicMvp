package id.co.maminfaruq.basicmvp.network;

import id.co.maminfaruq.basicmvp.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    //Membuat method return untuk mendapatkan retrofit yang sudah berisi base url
    public static Retrofit getClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
