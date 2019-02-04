package id.co.maminfaruq.basicmvp.detail;

import android.os.Bundle;

import id.co.maminfaruq.basicmvp.model.SingleUserResponse;
import id.co.maminfaruq.basicmvp.network.ApiClient;
import id.co.maminfaruq.basicmvp.network.ApiInterface;
import id.co.maminfaruq.basicmvp.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter implements DetailContract.Presenter{

    private final DetailContract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private int id;

    public DetailPresenter(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataSingleUser(Bundle bundle) {
        //TODO cek bundle
        if (bundle != null){
            id = bundle.getInt(Constants.KEY_ID);
        }

        //TODO menampilkan data dengan id
        view.showProgress();
        Call<SingleUserResponse>call = apiInterface.getSingleUser(id);
        call.enqueue(new Callback<SingleUserResponse>() {
            @Override
            public void onResponse(Call<SingleUserResponse> call, Response<SingleUserResponse> response) {
                //Menutup progress dialog
                view.hideProgress();

                //cek response body
                if (response.body() != null){
                    //Memasukkan response body ke dalam Single user response
                    SingleUserResponse singleUserResponse = response.body();
                    //cek apakah single user response ada isinya?
                    if (singleUserResponse.getData() != null){
                        //Mengirim data single user ke view untuk di tampilkan
                        view.showDataSingleUser(singleUserResponse.getData());
                    }
                }

            }

            @Override
            public void onFailure(Call<SingleUserResponse> call, Throwable t) {
                view.hideProgress();
                view.ShowFailureMessage(t.getMessage());
            }
        });
    }
}
