package id.co.maminfaruq.basicmvp.main;

import id.co.maminfaruq.basicmvp.model.UserResponse;
import id.co.maminfaruq.basicmvp.network.ApiClient;
import id.co.maminfaruq.basicmvp.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {

    //TODO membuat variable dan constructor untuk menerima context dari mainActivity
    //agar dapat mengakses method yang ada di mainActivity

    private final MainContract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataListUser() {
        //menampilkan progress dialog
        view.ShowProgress();

        //merequest object call untuk menseting endpoint dan parameter yang di butuhkan
        Call<UserResponse> call = apiInterface.getDataUser(12);
        //Menjalan request api
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                //Menutup progress
                view.HideProgress();

                //Mencek data response body
                if (response.body() != null) {
                    UserResponse userResponse = response.body();

                    //Cek data list user
                    if (userResponse.getUserDataList() != null) {
                        //mengirimkan data list user ke view untuk di tampilkan
                        view.showDataListUser(userResponse.getUserDataList());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

                //menutup progress
                view.HideProgress();

                //menampilkan pesan penolakan server
                view.showFailureMessage(t.getMessage());
            }
        });
    }
}
