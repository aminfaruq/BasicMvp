package id.co.maminfaruq.basicmvp.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.maminfaruq.basicmvp.R;
import id.co.maminfaruq.basicmvp.detail.DetailContract;
import id.co.maminfaruq.basicmvp.detail.DetailPresenter;
import id.co.maminfaruq.basicmvp.model.UserData;

public class DetailActivity extends AppCompatActivity implements DetailContract.View {

    @BindView(R.id.imgAvatar)
    ImageView imgAvatar;
    @BindView(R.id.txtFirst)
    TextView txtFirst;
    @BindView(R.id.txtLast)
    TextView txtLast;

    //TODO menyiapkan variable yang di butuhkan
    private ProgressDialog progressDialog;
    private final DetailPresenter detailPresenter = new DetailPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        //TODO mengirimkan bundle ke presenter untuk di cek dan merequest data single user ke API
        Bundle bundle = getIntent().getExtras();
        detailPresenter.getDataSingleUser(bundle);
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showDataSingleUser(UserData userData) {
        txtFirst.setText(userData.getFirst_name());
        txtLast.setText(userData.getLast_name());

        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image_black_24dp);

        Glide.with(this).load(userData.getAvatar()).apply(options).into(imgAvatar);
    }

    @Override
    public void ShowFailureMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
