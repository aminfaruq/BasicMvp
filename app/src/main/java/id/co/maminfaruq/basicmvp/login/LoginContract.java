package id.co.maminfaruq.basicmvp.login;

public interface LoginContract {
    //TODO Membuat interface untuk method yang di butuhkan pada view / Interaksi dengan user
    interface View {
        // menampilkan dan menutup progress loading dialog
        void showProgress();

        void hideProgress();

        // menampilkan dan melakukan sesuatu pada saat server merespon berhasil ataupun gagal
        void loginFailure(String msg);

        void loginSuccess(String token);
    }

    /*TODO|| membuat interface untuk method yang di butuhkan pada Presenter / Mediator dengan
     * TODO|| model (Bisnis Logic)*/
    interface Presenter {
        /* method untuk mengeksekusi bisnis logic untuk login contoh pengecekan
         * data dan pengiriman ke internet*/

        void doLogin(String email, String password);

    }
}
