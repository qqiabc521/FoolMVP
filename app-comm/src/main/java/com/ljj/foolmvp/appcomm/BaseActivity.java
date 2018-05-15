package com.ljj.foolmvp.appcomm;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.ljj.foolmvp.appcomm.widget.LoadingDialog;
import com.ljj.foolmvp.core.BaseMVPActivity;
import com.ljj.foolmvp.util.BusinessBaseUtil;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by lijunjie on 2017/12/28.
 */

public class BaseActivity extends BaseMVPActivity {

    private boolean isDestroyed = false;

    private LoadingDialog loadingDialog = null;
    private CompositeDisposable mDisposables = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isDestroyed = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelLoadingDialog();
        isDestroyed = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public boolean isPageDestroyed() {
        return isDestroyed;
    }

    protected void showLoadingDialog() {
        showLoadingDialog(null, null, true, false);
    }

    protected void showLoadingDialog(String message) {
        showLoadingDialog(message, null, true, false);
    }

    protected void showLoadingDialog(String message, boolean progressFlag) {
        showLoadingDialog(message, null, true, progressFlag);
    }

    protected void showLoadingDialog(DialogInterface.OnCancelListener onCancelListener) {
        showLoadingDialog(null, onCancelListener, true, false);
    }

    protected void showLoadingDialog(DialogInterface.OnCancelListener onCancelListener, boolean cancelable) {
        showLoadingDialog(null, onCancelListener, cancelable, false);
    }

    protected void showLoadingDialog(String message, boolean cancelable, boolean progressFlag) {
        showLoadingDialog(message, null, cancelable, progressFlag);
    }

    protected void showLoadingDialog(String message, DialogInterface.OnCancelListener onCancelListener, boolean cancelable) {
        showLoadingDialog(message, onCancelListener, cancelable, false);
    }

    protected void showLoadingDialog(String message, DialogInterface.OnCancelListener onCancelListener, boolean cancelable, boolean progressFlag) {
        if (isPageDestroyed()) {
            return;
        }
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.cancel();
        }
        loadingDialog = null;

        loadingDialog = new LoadingDialog(this, R.style.LoadingDialog, progressFlag);
        loadingDialog.setCancelable(cancelable);
        if (onCancelListener != null) {
            loadingDialog.setOnCancelListener(onCancelListener);
        }
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
        if (!TextUtils.isEmpty(message)) {
            loadingDialog.setLoadingText(message);
        }
    }

    protected void cancelLoadingDialog() {
        if (isPageDestroyed() || isFinishing()) {
            return;
        }
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.cancel();
        }
        loadingDialog = null;
    }

    protected void updateProgressLoading(int progress) {
        if (loadingDialog == null || !loadingDialog.isShowing()) {
            return;
        }
        loadingDialog.updateProgress(progress);
    }

    public void showNofityMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    /**
     * 添加一个订阅关系
     *
     * @param disposable
     */
    protected void register(Disposable disposable) {
        mDisposables = BusinessBaseUtil.registerDisposable(disposable, mDisposables);
    }

    /**
     * 手动移除并取消订阅一个订阅关系
     *
     * @param disposable
     */
    protected void unRegister(Disposable disposable) {
        BusinessBaseUtil.releaseDisposable(disposable, mDisposables);
    }

    /**
     * 显示加载view
     *
     * @param presenterId
     * @param disposable
     */
    @Override
    protected void showLoadingView(int presenterId, Disposable disposable) {
        showLoadingDialog();
    }

    /**
     * 隐藏加载View
     *
     * @param presenterId
     */
    @Override
    protected void hidelLoadingView(int presenterId) {
        cancelLoadingDialog();
    }

    /**
     * 显示消息提示
     *
     * @param presenterId
     * @param msg
     */
    @Override
    protected void showNofityMessage(int presenterId, String msg) {
        showNofityMessage(msg);
    }

    /**
     * 设置ActionBar title
     *
     * @param title
     */
    protected void setActionBarTitle(String title) {
        setTitle(title);
    }
}
