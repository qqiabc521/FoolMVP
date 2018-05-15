package com.ljj.foolmvp.user.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ljj.foolmvp.appcomm.bean.UserBrief;
import com.ljj.foolmvp.appcomm.config.PageActionConfig;
import com.ljj.foolmvp.appcomm.entity.Relationship;
import com.ljj.foolmvp.user.R;
import com.ljj.foolmvp.user.bean.UserBean;
import com.ljj.foolmvp.user.di.component.UserActivityComponent;
import com.ljj.foolmvp.user.presenter.impl.UserSubPresenterImpl;
import com.ljj.foolmvp.user.ui.view.IUserSubView;

import javax.inject.Inject;

/**
 * Created by lijunjie on 2017/12/21.
 */

public class UserDetailActivity extends BaseUserActivity implements IUserSubView, View.OnClickListener {

    private TextView nickNameTV, ageTV, emailTV, phoneTV, signTV;
    private Button followBtn;

    private static final String USER_ID = PageActionConfig.ACTION_USER_DETAIL_EXTRA_USERID;
    private static final String USER = PageActionConfig.ACTION_USER_DETAIL_EXTRA_USER;

    @Inject
    UserSubPresenterImpl userSubPresenter;

    @Override
    protected void initInjector(UserActivityComponent userActivityComponent) {
        userActivityComponent.inject(this);
        userSubPresenter.attachView(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        long userId;
        UserBrief userBrief = getIntent().getParcelableExtra(USER);
        if (userBrief != null) {
            userId = userBrief.getId();
        } else {
            userId = getIntent().getLongExtra(USER_ID, -1);
        }

        nickNameTV = findViewById(R.id.user_detail_name);
        ageTV = findViewById(R.id.user_detail_age);
        emailTV = findViewById(R.id.user_detail_email);
        phoneTV = findViewById(R.id.user_detail_phone);
        signTV = findViewById(R.id.user_detail_sign);
        followBtn = findViewById(R.id.user_detail_follow_btn);
        followBtn.setOnClickListener(this);

        userSubPresenter.requestUserDetail(userId);
    }

    /**
     * 获得用户详情信息回调
     *
     * @param userBean
     */
    @Override
    public void doUserDetail(UserBean userBean) {
        if (userBean == null) {
            return;
        }
        nickNameTV.setText(userBean.getName());
        ageTV.setText(userBean.getAge() + "岁");
        emailTV.setText(userBean.getEmail());
        phoneTV.setText(userBean.getPhoneNumber());
        signTV.setText(userBean.getDescription());

        showRelationship(userBean.getRelationship());
    }

    private void showRelationship(Relationship relationship) {
        if (Relationship.FOLLOWED.equals(relationship)) {
            doFollowedResult();
        } else if (Relationship.DEFAULT.equals(relationship)) {
            doUnFollowResult();
        }
    }

    /**
     * 关注用户成功回调
     */
    @Override
    public void doFollowedResult() {
        followBtn.setText("取消关注");
    }

    /**
     * 取消关注用户回调
     */
    @Override
    public void doUnFollowResult() {
        followBtn.setText("关注");
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.user_detail_follow_btn) {
            userSubPresenter.updateUserRelationship();
        }
    }
}
