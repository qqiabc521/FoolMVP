package com.ljj.foolmvp.appcomm.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.ljj.foolmvp.appcomm.entity.FeedEntity;
import com.ljj.foolmvp.appcomm.entity.Relationship;

/**
 * Created by lijunjie on 2017/12/28.
 */

public class FeedBrief extends BaseBean implements Parcelable{

    private long id;

    private String title;

    private UserBrief owner;

    public FeedBrief(){}

    public FeedBrief(FeedEntity feedEntity){
        this.id = feedEntity.getId();
        this.title = feedEntity.getTitle();

        if(feedEntity.getOwner() != null){
            this.owner = new UserBrief(feedEntity.getOwner());
        }
    }

    protected FeedBrief(Parcel in) {
        id = in.readLong();
        title = in.readString();
        owner = in.readParcelable(UserBrief.class.getClassLoader());
    }

    public static final Creator<FeedBrief> CREATOR = new Creator<FeedBrief>() {
        @Override
        public FeedBrief createFromParcel(Parcel in) {
            return new FeedBrief(in);
        }

        @Override
        public FeedBrief[] newArray(int size) {
            return new FeedBrief[size];
        }
    };

    public UserBrief getOwner() {
        return owner;
    }

    public void setOwner(UserBrief owner) {
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void updateRelationship(Relationship relationship){
        if(owner != null){
            owner.setRelationship(relationship);
        }
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
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeParcelable(owner, flags);
    }
}
