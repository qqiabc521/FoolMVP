package com.ljj.foolmvp.user.interactor.impl;

import com.ljj.foolmvp.appcomm.entity.Relationship;
import com.ljj.foolmvp.appcomm.entity.UserEntity;
import com.ljj.foolmvp.appcomm.util.RxUtils;
import com.ljj.foolmvp.callback.RequestCallBack;
import com.ljj.foolmvp.greendao.UserEntityDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class UserInteratorImplTest {

    @Mock
    private UserEntityDao userEntityDao;

    private UserInteratorImpl userInterator = null;

    private UserEntity tempUserEntity;

    @Before
    public void setUp() throws Exception {
        System.out.println("setUp");

        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
        RxUtils.setCallbackScheduler(Schedulers.single());
        MockitoAnnotations.initMocks(this);
        userInterator = new UserInteratorImpl(userEntityDao);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setAge(10);
        userEntity.setDescription(null);
        userEntity.setEmail("abc@163.com");
        userEntity.setName("张三");
        userEntity.setPhoneNumber("12345678901");
        userEntity.setRelationShip(Relationship.DEFAULT);

        tempUserEntity = userEntity;
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown");
    }


    @Test
    public void saveUser() {
        System.out.println("================= saveUser ===================");
        Observable<Long> result =  userInterator.saveUser(tempUserEntity);
        if(result == null){
            assert false;
        }
        result.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe :"+d);
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("onNext :"+aLong);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError :"+e.getMessage());
                assert false;
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

//    @Test
//    public void saveAndGetUser() {
//        System.out.println("================= saveUser ===================");
//        userInterator.saveUser(tempUserEntity).flatMap(new Function<Long, Observable<UserEntity>>() {
//            @Override
//            public Observable<UserEntity> apply(Long aLong) throws Exception {
//                System.out.println("flatMap apply :"+aLong);
//                return userInterator.getUser(aLong);
//            }
//        }).subscribe(new Observer<UserEntity>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                System.out.println("onSubscribe :"+d);
//            }
//
//            @Override
//            public void onNext(UserEntity user) {
//                System.out.println("onNext :"+user);
//                assertThat(user.getName(),is(tempUserEntity.getName()));
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("onError :"+e.getMessage());
//                assert false;
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("onComplete");
//            }
//        });
//    }

    @Test
    public void saveUserSync(){
        System.out.println("================= saveUserSync ===================");
        userInterator.saveUser(tempUserEntity, new RequestCallBack<Long>() {
            @Override
            public void onRequestStart(Disposable disposable) {
                System.out.println("onRequestStart :"+disposable);
            }

            @Override
            public void onFinish() {
                System.out.println("onFinish");
            }

            @Override
            public void onResponse(Long data) {
                System.out.println("onResponse :"+data);
                assert true;
            }

            @Override
            public void onRequestError(String error) {
                System.out.println("onRequestError :"+error);
                assert false;
            }
        });
    }

}