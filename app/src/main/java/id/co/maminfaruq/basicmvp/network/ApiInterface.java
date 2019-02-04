package id.co.maminfaruq.basicmvp.network;

import id.co.maminfaruq.basicmvp.model.LoginBody;
import id.co.maminfaruq.basicmvp.model.LoginResponse;
import id.co.maminfaruq.basicmvp.model.SingleUserResponse;
import id.co.maminfaruq.basicmvp.model.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    //TODO membuat endpoint login
    @POST("api/login")
    Call<LoginResponse> postLogin(
            @Body LoginBody loginBody
    );

    @GET("api/users")
    Call<UserResponse>getDataUser(
            @Query("per_page")int perPage
    );

    @GET("api/users/{id}")
    Call<SingleUserResponse> getSingleUser(
            @Path("id") int id
    );
}
