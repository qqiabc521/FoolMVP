package com.ljj.foolmvp.core;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ljj.foolmvp.di.component.ApplicationComponent;
import com.ljj.foolmvp.di.module.FragmentModule;
import com.ljj.foolmvp.util.BusinessBaseUtil;

import java.util.Set;

import io.reactivex.disposables.Disposable;

/**
 * Created by lijunjie on 2017/12/27.
 */

public abstract class BaseMVPFragment extends Fragment implements CompositePresenter {

    private Set<BasePresenter> mPresenters = null;

    protected abstract void initViews(View view);

    protected AppCompatActivity getAppCompatActivity() {
        return (AppCompatActivity) getActivity();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((BaseMVPApplication) getActivity().getApplication()).getApplicationComponent();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    /**
     * 任务开始触发
     * @param presenterId
     * @param disposable
     */
    public void onStartTask(int presenterId, Disposable disposable) {
        showLoadingView(presenterId, disposable);
    }

    /**
     * 任务完成触发
     * @param presenterId
     */
    public void onFinishTask(int presenterId) {
        hidelLoadingView(presenterId);
    }

    /**
     * 异常消息处理
     * @param presenterId
     * @param msg
     */
    public void onErrorMessage(int presenterId, String msg) {
        onFinishTask(presenterId);
        showNofityMessage(presenterId, msg);
    }

    /**
     * 显示加载view
     * @param presenterId
     * @param disposable
     */
    protected abstract void showLoadingView(int presenterId, Disposable disposable);

    /**
     * 隐藏加载View
     * @param presenterId
     */
    protected abstract void hidelLoadingView(int presenterId);

    /**
     * 显示消息提示
     * @param presenterId
     * @param msg
     */
    protected abstract void showNofityMessage(int presenterId, String msg);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BusinessBaseUtil.releasePresenters(mPresenters);
    }

    /**
     * 聚合Presenter，方便对Prestener集中处理
     *
     * @param basePresenter
     */
    @Override
    public void compositePresenter(BasePresenter basePresenter) {
        mPresenters = BusinessBaseUtil.compositePresenter(basePresenter, mPresenters);
    }

}

