package com.ljj.foolmvp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.ljj.foolmvp.di.component.AppActivityComponent;
import com.ljj.foolmvp.presenter.impl.LoadDataPresenterImpl;
import com.ljj.foolmvp.ui.AppInjectBaseActivity;
import com.ljj.foolmvp.ui.view.ILoadDataView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class LoadDataActivity extends AppInjectBaseActivity implements ILoadDataView {
    private TextView hitTV;

    @Inject
    LoadDataPresenterImpl loadDataPresenter;

    @Override
    protected void initInjector(AppActivityComponent appActivityComponent) {
        appActivityComponent.inject(this);
        loadDataPresenter.attachView(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);
        hitTV = findViewById(R.id.load_data_hit);

        loadDataPresenter.loadData();
    }

    @Override
    public void onStartTask(int presenterId, Disposable disposable) {
        if (presenterId == loadDataPresenter.getPresenterId()) {
            hitTV.setText("正在载入数据...");
            return;
        }
        super.onStartTask(presenterId, disposable);
    }

    @Override
    public void onErrorMessage(int presenterId, String msg) {
        if (presenterId == loadDataPresenter.getPresenterId()) {
            hitTV.setText(Html.fromHtml("数据载入失败：\r" + msg));
            return;
        }
        super.onErrorMessage(presenterId, msg);
    }

    /**
     * 完成初始化数据后回调
     */
    @Override
    public void doLoadDataResult() {
        hitTV.setText("数据已准备完成");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * 载入进度更新
     *
     * @param index
     * @param count
     */
    @Override
    public void doLoadingData(int index, int count) {
        hitTV.setText("数据已载入第 " + index + " 条，共计 " + count + " 条");
    }

}
