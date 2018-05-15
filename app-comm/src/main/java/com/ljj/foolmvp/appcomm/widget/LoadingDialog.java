package com.ljj.foolmvp.appcomm.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.ljj.foolmvp.appcomm.R;


/**
 * Created by Lijj on 17/2/20.
 */

public class LoadingDialog extends ProgressDialog {
    private RingProgressBar mProgressBar;
    private TextView mTextView;
    private boolean progressFlag = false;

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme, boolean progressFlag) {
        super(context, theme);
        this.progressFlag = progressFlag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(getContext());
    }

    private void init(Context context) {
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
        setCanceledOnTouchOutside(false);

        if(progressFlag){
            setContentView(R.layout.loading_progress);
        }else{
            setContentView(R.layout.loading_dialog);
        }

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);


        mTextView = (TextView) findViewById(R.id.tv_load_dialog);

        if(progressFlag){
            mProgressBar = (RingProgressBar) findViewById(R.id.pb_load);
        }
    }

    public void setLoadingText(String text){
        mTextView.setText(text);
    }

    public void updateProgress(int progress){
        if(mProgressBar != null){
            mProgressBar.setProgress(progress);
        }
    }
}
