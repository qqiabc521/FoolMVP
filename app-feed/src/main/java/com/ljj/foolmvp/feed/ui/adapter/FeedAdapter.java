package com.ljj.foolmvp.feed.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljj.foolmvp.appcomm.adapter.BaseRecyclerViewAdapter;
import com.ljj.foolmvp.appcomm.adapter.BaseRecyclerViewHolder;
import com.ljj.foolmvp.appcomm.bean.FeedBrief;
import com.ljj.foolmvp.feed.R;

import java.lang.ref.WeakReference;

/**
 * Created by lijunjie on 2017/12/21.
 */

public class FeedAdapter extends BaseRecyclerViewAdapter<FeedBrief> {
    private WeakReference<Activity> mActivity;

    public FeedAdapter(Activity activity){
        mActivity = new WeakReference<>(activity);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FeedViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false));
    }

    public class FeedViewHolder extends BaseRecyclerViewHolder<FeedBrief>{
        private TextView titleTV;

        public FeedViewHolder(View itemView){
            super(itemView);
            titleTV = itemView.findViewById(R.id.feed_item_title);
        }

        /**
         * @param feedBrief   the data of bind
         * @param position the item position of recyclerView
         */
        @Override
        public void onBindViewHolder(FeedBrief feedBrief, int position) {
            if(feedBrief == null){
                return;
            }
            Activity activity = mActivity.get();
            if(activity == null){
                return;
            }
            titleTV.setText(feedBrief.getTitle());
        }

    }
}
