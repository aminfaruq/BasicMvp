package id.co.maminfaruq.basicmvp.detail;

import android.os.Bundle;

import id.co.maminfaruq.basicmvp.model.UserData;

public interface DetailContract {
    interface View{
        void showProgress();
        void hideProgress();
        void showDataSingleUser(UserData userData);
        void ShowFailureMessage(String msg);
    }

    interface Presenter{
        void getDataSingleUser(Bundle bundle);
    }
}
