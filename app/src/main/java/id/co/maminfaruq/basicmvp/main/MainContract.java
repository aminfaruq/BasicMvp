package id.co.maminfaruq.basicmvp.main;

import java.util.List;

import id.co.maminfaruq.basicmvp.model.UserData;

public interface MainContract {
    interface View{
        //Menampilkan method progress dialog
        void ShowProgress();
        void HideProgress();

        //menampilkan data list user ke view recyclerview
        void showDataListUser(List<UserData>userDataList);

        //menampilkan pesan gagal
        void showFailureMessage(String msg);

    }
    interface Presenter{
        //membuat method interface untuk mengambil data dari API
        void getDataListUser();
    }
}
