package com.ljj.foolmvp.config;

import com.ljj.foolmvp.appcomm.entity.Relationship;
import com.ljj.foolmvp.feed.bean.FeedBean;
import com.ljj.foolmvp.user.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijunjie on 2017/12/28.
 */

public class AppDataUtil {

    public static List<UserBean> createUsers(int count) {
        List<UserBean> userBeans = new ArrayList<>(count);

        for (int i = 1; i <= count; i++) {
            UserBean userBean = new UserBean();
            userBean.setAge((int) (Math.random() * 100));
            userBean.setName("name" + i);
            userBean.setEmail("email" + i);
            userBean.setPhoneNumber("phoneNumber" + i);
            userBean.setDescription("我是傻瓜" + i);
            if (i == 1) {
                userBean.setRelationship(Relationship.OWNER);
            } else if (i % 3 == 0) {
                userBean.setRelationship(Relationship.FOLLOWED);
            } else {
                userBean.setRelationship(Relationship.DEFAULT);
            }

            userBeans.add(userBean);
        }

        return userBeans;
    }

    public static List<FeedBean> createFeeds(int count) {
        List<FeedBean> feedBeans = new ArrayList<>(count);

        for (int i = 1; i <= count; i++) {
            FeedBean feedBean = new FeedBean();
            feedBean.setTitle("FoolMVP的第" + i + "条feed");
            feedBean.setContent("开发高质量应用，离不开良好的框架设计和团队所有成员的共同努力，我是" + i + "条feed");

            feedBeans.add(feedBean);
        }

        return feedBeans;
    }
}
