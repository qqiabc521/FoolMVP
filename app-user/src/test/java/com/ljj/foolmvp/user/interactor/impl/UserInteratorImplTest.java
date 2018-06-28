package com.ljj.foolmvp.user.interactor.impl;

import com.ljj.foolmvp.appcomm.entity.Relationship;
import com.ljj.foolmvp.appcomm.entity.UserEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserInteratorImplTest {

    private UserInteratorImpl userInterator = null;

    @Before
    public void setUp() throws Exception {
        userInterator = new UserInteratorImpl();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setAge(10);
        userEntity.setDescription(null);
        userEntity.setEmail("abc@163.com");
        userEntity.setId(1L);
        userEntity.setName("张三");
        userEntity.setPhoneNumber("12345678901");
        userEntity.setRelationShip(Relationship.DEFAULT);
        Observable<Long> result =  userInterator.saveUser(userEntity);
        result.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                assert true;
            }

            @Override
            public void onError(Throwable e) {
                assert false;
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Test
    public void saveUser1() {
    }
}