package id.co.maminfaruq.basicmvp.login;

import android.util.Log;

import id.co.maminfaruq.basicmvp.model.LoginBody;
import id.co.maminfaruq.basicmvp.model.LoginResponse;
import id.co.maminfaruq.basicmvp.network.ApiClient;
import id.co.maminfaruq.basicmvp.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {
    //TODO menyiapkan variable yang di butuhkan
    //membuat object apiInterface untuk mensetting baseUrl retrofit
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    //TODO membuat object loginContract View
    private final LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void doLogin(String email, String password) {
        if (email == null || email.isEmpty()) {
            view.loginFailure("Email isEmpty");
            return;
        }

        if (password == null || email.isEmpty()) {
            view.loginFailure("Password isEmpty");
            return;
        }

        //menampilkan progress dialog agar memberitahu ada proses yang sedang berjalan
        view.showProgress();

        //Memasukkan data email dan password ke salam login body
        LoginBody loginBody = new LoginBody();
        loginBody.setEmail(email);
        loginBody.setPassword(password);

        /*mengeksekusi data ke server
         * membuat object call untuk mengirim login body*/
        Call<LoginResponse> call = apiInterface.postLogin(loginBody);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                //view menutup progress dialog
                view.hideProgress();

                //mengecek resonse apakah ada isinya?
                if (response.body() != null) {
                    //Mengambil data response body dan memasukan ke dalam class model LoginResponse
                    LoginResponse loginResponse = response.body();

                    //mencek isi token apakah ada isinya? agar tidak NPE
                    if (loginResponse.getToken() != null) {
                        view.loginSuccess(loginResponse.getToken());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("Debug gagal", t.getMessage());

                //Menutup progress dialog
                view.hideProgress();
                //menampilkan toast gagal
                view.loginFailure(t.getMessage());
            }
        });
    }
}
