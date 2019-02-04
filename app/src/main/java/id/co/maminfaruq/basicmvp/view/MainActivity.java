package id.co.maminfaruq.basicmvp.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.maminfaruq.basicmvp.R;
import id.co.maminfaruq.basicmvp.adapter.MainAdapter;
import id.co.maminfaruq.basicmvp.main.MainContract;
import id.co.maminfaruq.basicmvp.main.MainPresenter;
import id.co.maminfaruq.basicmvp.model.UserData;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.rvUser)
    RecyclerView rvUser;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    //TODO membuat variable yang di butuhkan
    private ProgressDialog progressDialog;
    private final MainPresenter mainPresenter = new MainPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter.getDataListUser();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainPresenter.getDataListUser();
            }
        });
    }

    @Override
    public void ShowProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void HideProgress() {
        progressDialog.dismiss();
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showDataListUser(List<UserData> userDataList) {
        //TODO mensetting adapter
        rvUser.setLayoutManager(new LinearLayoutManager(this));
        rvUser.setAdapter(new MainAdapter(this, userDataList));
    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
