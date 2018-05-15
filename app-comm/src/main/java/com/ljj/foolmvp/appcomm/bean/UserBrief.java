package com.ljj.foolmvp.appcomm.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.ljj.foolmvp.appcomm.entity.Relationship;
import com.ljj.foolmvp.appcomm.entity.UserEntity;

/**
 * Created by lijunjie on 2017/12/28.
 */

public class UserBrief extends BaseBean implements Parcelable{

    private long id;

    private String name;

    private Relationship relationship = Relationship.DEFAULT;

    public UserBrief(){}

    public UserBrief(UserEntity userEntity){
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.relationship = userEntity.getRelationShip();
    }

    protected UserBrief(Parcel in) {
        id = in.readLong();
        name = in.readString();
    }

    public static final Creator<UserBrief> CREATOR = new Creator<UserBrief>() {
        @Override
        public UserBrief createFromParcel(Parcel in) {
            return new UserBrief(in);
        }

        @Override
        public UserBrief[] newArray(int size) {
            return new UserBrief[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
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
        dest.writeString(name);
    }
}
