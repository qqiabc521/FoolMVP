package com.ljj.foolmvp.feed.ui;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.ljj.foolmvp.appcomm.adapter.OnItemClickListener;
import com.ljj.foolmvp.appcomm.bean.FeedBrief;
import com.ljj.foolmvp.feed.R;
import com.ljj.foolmvp.feed.di.component.FeedActivityComponent;
import com.ljj.foolmvp.feed.presenter.impl.FeedsPresenterImpl;
import com.ljj.foolmvp.feed.ui.adapter.FeedAdapter;
import com.ljj.foolmvp.feed.ui.view.IFeedsView;

import java.util.List;

import javax.inject.Inject;

public class FeedsActivity extends BaseFeedActivity implements IFeedsView{
    private RecyclerView mRecyclerView;
    private FeedAdapter mFeedAdapter;

    @Inject
    FeedsPresenterImpl feedsPresenter;

    @Override
    protected void initInjector(FeedActivityComponent feedActivityComponent) {
        feedActivityComponent.inject(this);
        feedsPresenter.attachView(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeds);

        mRecyclerView = findViewById(R.id.feeds_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(1));

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        mRecyclerView.setHasFixedSize(true);

        mFeedAdapter = new FeedAdapter(this);
        mFeedAdapter.setOnItemClickListener(new OnItemClickListener<FeedBrief>(){

            @Override
            public void onClick(FeedBrief feedBrief, int position) {
                startActivity(FeedDetailActivity.createIntent(FeedsActivity.this,feedBrief.getId()));
            }
        });
        mRecyclerView.setAdapter(mFeedAdapter);

        feedsPresenter.requestFeeds();
    }

    @Override
    public void doFeedsResult(List<FeedBrief> feedBriefs) {
        if(feedBriefs == null){
            return;
        }

        mFeedAdapter.addAll(feedBriefs);
        mFeedAdapter.notifyDataSetChanged();
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(space, space, space, space);
        }
    }
}
