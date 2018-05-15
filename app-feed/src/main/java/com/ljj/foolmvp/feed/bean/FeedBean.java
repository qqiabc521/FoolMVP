package com.ljj.foolmvp.feed.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.ljj.foolmvp.appcomm.bean.FeedBrief;
import com.ljj.foolmvp.appcomm.entity.FeedEntity;

/**
 * Created by lijunjie on 2017/12/28.
 */

public class FeedBean extends FeedBrief implements Parcelable {

    private String content;

    private int likeCount;

    public FeedBean() {
        super();
    }

    public FeedBean(FeedEntity feedEntity) {
        super(feedEntity);
        this.content = feedEntity.getContent();
        this.likeCount = feedEntity.getLikeCount();
    }

    protected FeedBean(Parcel in) {
        super(in);
        content = in.readString();
        likeCount = in.readInt();
    }

    public static final Creator<FeedBean> CREATOR = new Creator<FeedBean>() {
        @Override
        public FeedBean createFromParcel(Parcel in) {
            return new FeedBean(in);
        }

        @Override
        public FeedBean[] newArray(int size) {
            return new FeedBean[size];
        }
    };

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public static FeedEntity createFeedEntity(FeedBean feedBean) {
        if (feedBean == null) {
            return null;
        }
        FeedEntity feedEntity = new FeedEntity();
        feedEntity.setId(feedBean.getId() == 0 ? null : feedBean.getId());
        feedEntity.setContent(feedBean.getContent());
        feedEntity.setTitle(feedBean.getTitle());
        if (feedBean.getOwner() != null) {
            feedEntity.setOwnerId(feedBean.getOwner().getId());
        }
        feedEntity.setLikeCount(feedBean.getLikeCount());

        return feedEntity;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(content);
        dest.writeInt(likeCount);
    }
}
