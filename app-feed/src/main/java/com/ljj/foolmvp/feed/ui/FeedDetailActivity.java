package com.ljj.foolmvp.feed.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ljj.foolmvp.appcomm.bean.UserBrief;
import com.ljj.foolmvp.appcomm.config.PageActionConfig;
import com.ljj.foolmvp.appcomm.entity.Relationship;
import com.ljj.foolmvp.appcomm.presenter.impl.FollowPresenterImpl;
import com.ljj.foolmvp.appcomm.ui.view.IFollowView;
import com.ljj.foolmvp.feed.R;
import com.ljj.foolmvp.feed.bean.FeedBean;
import com.ljj.foolmvp.feed.di.component.FeedActivityComponent;
import com.ljj.foolmvp.feed.presenter.impl.FeedDetailPresenterImpl;
import com.ljj.foolmvp.feed.ui.view.IFeedDetailView;

import javax.inject.Inject;

/**
 * Created by lijunjie on 2018/1/2.
 */

public class FeedDetailActivity extends BaseFeedActivity implements IFeedDetailView, IFollowView, View.OnClickListener {
    private static final String FEED_ID = "feed_id";

    private TextView contentTV, onwerTV;
    private Button toUserBtn, followBtn;

    private FeedBean feedBean;

    @Inject
    FeedDetailPresenterImpl feedDetailPresenter;

    @Inject
    FollowPresenterImpl followPresenter;

    public static Intent createIntent(Context context, long feedId) {
        Intent intent = new Intent(context, FeedDetailActivity.class);
        intent.putExtra(FEED_ID, feedId);
        if (!(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        return intent;
    }

    @Override
    protected void initInjector(FeedActivityComponent feedActivityComponent) {
        feedActivityComponent.inject(this);
        feedDetailPresenter.attachView(this);
        followPresenter.attachView(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_detail);

        long feedId = getIntent().getLongExtra(FEED_ID, -1);

        contentTV = findViewById(R.id.feed_detail_content);
        onwerTV = findViewById(R.id.feed_detail_owner_name);
        toUserBtn = findViewById(R.id.feed_detail_to_user);
        followBtn = findViewById(R.id.feed_detail_follow_user);

        toUserBtn.setOnClickListener(this);
        followBtn.setOnClickListener(this);

        feedDetailPresenter.requestFeed(feedId);
    }

    /**
     * 获得feed详情
     *
     * @param feedBean
     */
    @Override
    public void doFeedResult(FeedBean feedBean) {
        if (feedBean == null) {
            return;
        }
        this.feedBean = feedBean;
        setActionBarTitle(feedBean.getTitle());

        contentTV.setText(feedBean.getContent());
        UserBrief userBrief = feedBean.getOwner();
        if (userBrief != null) {
            if (Relationship.OWNER.equals(userBrief.getRelationship())) {
                onwerTV.setText("来自：自己");
                toUserBtn.setVisibility(View.GONE);
                followBtn.setVisibility(View.GONE);
            } else {
                onwerTV.setText("来自：" + userBrief.getName() + " 用户");
                toUserBtn.setVisibility(View.VISIBLE);
                followBtn.setVisibility(View.VISIBLE);
                showRelationship(userBrief.getRelationship());
            }
        }
    }

    private void showRelationship(Relationship relationship) {
        if (Relationship.FOLLOWED.equals(relationship)) {
            followBtn.setText("取消关注");
        } else if (Relationship.DEFAULT.equals(relationship)) {
            followBtn.setText("关注");
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.feed_detail_to_user) {
            Intent intent = new Intent(PageActionConfig.ACTION_USER_DETAIL);
            intent.putExtra(PageActionConfig.ACTION_USER_DETAIL_EXTRA_USER, feedBean.getOwner());
            startActivity(intent);
        } else if (v.getId() == R.id.feed_detail_follow_user) {
            UserBrief userBrief = feedBean.getOwner();
            if (userBrief != null) {
                if (Relationship.DEFAULT.equals(userBrief.getRelationship())) {
                    followPresenter.toFollowUser(userBrief.getId());
                } else if (Relationship.FOLLOWED.equals(userBrief.getRelationship())) {
                    followPresenter.toUnFollowUser(userBrief.getId());
                }
            }
        }
    }

    /**
     * 关注用户成功回调
     *
     * @param userId
     * @param relationship
     */
    @Override
    public void doFollowedResult(long userId, Relationship relationship) {
        updateRelationship(userId, relationship);
    }

    /**
     * 取消关注用户回调
     *
     * @param userId
     * @param relationship
     */
    @Override
    public void doUnFollowResult(long userId, Relationship relationship) {
        updateRelationship(userId, relationship);
    }

    private void updateRelationship(long userId, Relationship relationship) {
        if (feedBean == null || feedBean.getOwner() == null) {
            return;
        }
        UserBrief userBrief = feedBean.getOwner();
        if (userId != userBrief.getId()) {
            return;
        }
        feedBean.updateRelationship(relationship);
        showRelationship(relationship);
    }
}
