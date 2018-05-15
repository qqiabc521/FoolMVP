package com.ljj.foolmvp.user.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.ljj.foolmvp.appcomm.bean.UserBrief;
import com.ljj.foolmvp.appcomm.entity.UserEntity;

/**
 * Created by lijunjie on 2017/12/28.
 */

public class UserBean extends UserBrief implements Parcelable {

    private int age;

    private String email;

    private String phoneNumber;

    private String description;

    public UserBean() {
        super();
    }

    public UserBean(UserEntity userEntity) {
        super(userEntity);
        this.age = userEntity.getAge();
        this.email = userEntity.getEmail();
        this.phoneNumber = userEntity.getPhoneNumber();
        this.description = userEntity.getDescription();
    }

    protected UserBean(Parcel in) {
        super(in);
        age = in.readInt();
        email = in.readString();
        phoneNumber = in.readString();
        description = in.readString();
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel in) {
            return new UserBean(in);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static UserEntity createUserEntity(UserBean userBean) {
        if (userBean == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userBean.getId() == 0 ? null : userBean.getId());
        userEntity.setAge(userBean.getAge());
        userEntity.setName(userBean.getName());
        userEntity.setEmail(userBean.getEmail());
        userEntity.setPhoneNumber(userBean.getPhoneNumber());
        userEntity.setDescription(userBean.getDescription());
        userEntity.setRelationShip(userBean.getRelationship());

        return userEntity;
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
        dest.writeInt(age);
        dest.writeString(email);
        dest.writeString(phoneNumber);
        dest.writeString(description);
    }
}
