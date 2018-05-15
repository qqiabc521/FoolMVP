# FoolMVP

一种MVP的实现方式，目标：业务代码高度复用、良好的组件颗粒度、方便进行单元测试，结构尽量清晰简单的高内聚低耦合的分层结构。

这是我团队高效开发三部曲中的实战篇，另两篇分别是愿景篇和方法论篇：

[开发团队多人协作的思考(愿景)](https://www.jianshu.com/p/d8cef3725175)

[日常开发中，如何给团队留下财富？（方法论）](https://www.jianshu.com/p/67d8789864b2)



FoolMVP_Lib的核心基础结构：

<img src="https://github.com/qqiabc521/FoolMVP/blob/master/screenshot/foolmvp_structure.jpg" width="300" height="600" alt="项目结构图"/>

注：项目需要结合Rxjava和dagger2一起使用，FoolMVP充分利用了响应式编程和依赖注入强大功能基础之上很方便、高效的满足我们的开发需求。
大家如果对Rxjava或dagger2不熟悉，可以参考如下几篇文章。

[给初学者的 RxJava2.0 系列教程](https://weibo.com/ttarticle/p/show?id=2309404060189467756302)

[Android常用开源工具（1）-Dagger2入门](http://blog.csdn.net/duo2005duo/article/details/50618171)

[Android常用开源工具（2）-Dagger2进阶](http://blog.csdn.net/duo2005duo/article/details/50696166)


## 前言
在项目架构中，分层架构是非常通用且常见的一种架构方式。其中MVP结构是分层架构的一种方式，最近几年在客户端开发中比较流行。
网上也有不少资料对MVP结构做了介绍和开发demo，但大部分都是单一应用在表象，真正的从如何解耦功能、业务代码隔离、多人团队开发等综合方向去提出解决方案的却少之又少。
最近开发的一个项目已初步稳定，也是使用MVP的方式做分层架构，打算把在结构设计的思路分享给大家。为了能让大家容易记住，我把这种架构取名为FoolMVP，意为“傻瓜MVP”.

这个架构思路的目标即不需要资深开发经验人员也能开发出结构清晰、代码高度复用、有良好的组件颗粒度、组件间可插拔、可进行单元测试的结构。

## 正文
MVP，全称 Model-View-Presenter，分别代表界面层（V）、业务逻辑层（P）、数据访问层（M），即三层架构。


<img src="https://github.com/qqiabc521/FoolMVP/blob/master/screenshot/image_thumb_1.png" width="500" height="300" alt="项目结构图"/>


MVP有好多种实现方式，核心的不同点大都集中在P层上。所以P层设计好坏直接影响整体结构。
我们在设计结构之前，都需要基于一个目标或者叫方法论，一切都围绕着这个方法论去搭建结构，方向就不会偏。

### FoolMVP设计的方法论:
    
    业务代码高度复用、良好的组件颗粒度、方便进行单元测试，结构尽量清晰简单的高内聚低耦合的分层结构。
    

### P层的设计要求：
* **1.UI的无关性：**
       尽可能提高可复用行，相同业务不同的UI能够共用P层逻辑。
* **2.可测试性：**
       理想情况下，任何分层都需要测试，都可以被测试。所以可测试性，也是我们框架设计的基本要求。
* **3.不依赖数据模型：**
       为了能更好的解耦，P层尽可能地不依赖数据模型。可P层与M层，需要通过数据转换的方式进行交互。但数据转换为项目带来更多的类，更多的工程文件，给我们带来额外的开发量。所以我们在项目中是否需要数据转换，需要根据根据项目的需要来决定。
* **4.不依赖图形化元素：**
       即UI的改变，不会影响我们的P层变化。

V与P实现分离，通常情况下相同业务在不同页面可能有不同的表现，所以我们可以对P层进行高度复用，能被高度复用的代码通常是业务功能尽量单一简单，即一个Presenter尽量只做一件事情。

    例如Feed列表对应一个Presenter、Feed删除对应一个Presenter、关注用户和取消关注对应一个Presenter（成对的业务通常在一起使用，尽量归为一个Presenter）。
    一个通用的Presenter完成了从UI层开始触发业务逻辑、开始加载提示、处理业务逻辑、异常提示、业务结果返回等，形成一个完整的闭环。
    对于界面层是否需要显示加载提示、异常提示及结果显示，完全由界面层决定。即UI的显示逻辑完全由界面层决定，P层只做自己本分的事情。

通常一个页面需要处理多件事情，就需要一个页面对应多个Presenter，这样页面不仅仅需要处理UI逻辑，也需要负责对多个Presenter做集合处理。
但这与MVP结构的初衷（V层只处理UI逻辑，P层处理业务逻辑）是相违背的。所有的架构设计的出发点都是为了提高开发效率、促进项目运行稳定、维护性更高，所以在V层做一点集合Presenter的简单逻辑功能也是合理的。
而且如果Presenter之间的业务关联不高的话，也是没有必要强制将它们揉合在一起的。
但对于Presenter之间有强依赖、关联性很高的话，把这部分关联逻辑放在V层处理，就不太合适了，所以这时候就出现了SubPresenter。
SubPresenter是对Presenter的拓展，是其子类，有普通Presenter的功能，但它也能集成多个Presenter，将关联性高的Presenter的关联逻辑放在SubPresenter中处理，V层直接面向SubPresentrer即可。

    例如：通常在用户页面，有用户详情（UserDetailPresenter）、关注or取消关注用户(UserFollowPresenter)、
    举报用户(UserReportPresenter)等功能，这几个功能就具有强关联性，都是围绕User进行处理，对User的所有改动都要实时的更新User。
    这个时候UserSubPresenter就能很好的派上用场了，User的变化逻辑都在UserSubPresenter中处理，UserDetailPresenter、UserFollowPresenter、UserReportPresenter不需要做任何改动，
    继续保持原有的独立性，在其他页面功能中提供服务。这也是FoolMVP能提供代码高度复用的一个体现点。

## 核心类说明：
* **BaseView：**
V层Push事件回调接口，由界面层实现。主要接收Presenter在处理业务逻辑过程中的结果反馈，onStartTask()，onFinishTask()，onErrorMessage()，showNofityMessage()。BaseView的实现者主要是Activity、Fragment、Service等UI载体。

* **ViewState：**
标记View类型，可以在Presenter中获得绑定View的载体类型，实现类为Presenter。

* **BasePresenter：**
Presenter的接口类，主要有三个生命周期和presenter唯一标识。onCreate()、attachView()、onDestory()、getPresenterId()。

* **BasePresenterImpl：**
Presenter的基础类，BasePresenter的子类，并实现了ViewState、PresenterDelegate接口。主要封装了Presenter的公共逻辑。

* **BaseSubPresenterImpl：**
Presenter的超级类或拓展类，继承BasePresenterImpl，并实现了BaseView接口。实现BaseView接口并不代表SubPresenter是V层，SubPresenter仅仅是起到UI事件中转的作用，真实的push UI事件处理交由SubPresenter所绑定的View处理。

* **CompositePresenter：**
该接口有View载体实现，主要是对界面层集合的Presenter做复合管理，比如统一销毁Presenter。

* **RequestCallBack：**
P层与M层的数据接口回调，onRequestStart()、onFinish()、onResponse、onRequestError。

* **AbstractRequestCallBack：**
RequestCallBack接口的抽象类，使RequestCallBack的实现者只关注onResponse回调，其他三个功能回调在公共基类统一处理。

* **PresenterDelegate：**
Presenter的一个委派类，为了简化RequestCallBack的实现类，使RequestCallBack的实现类只关注onResponse结果，将通用的操作在基类中统一处理。



## Demo说明：

<img src="https://github.com/qqiabc521/FoolMVP/blob/master/screenshot/FoolMVP模块依赖关系.png" width="600" height="400" alt="项目结构图"/>

为了能体现出如何进行模块解耦和代码的物理隔离，特将demo的业务分为了两个模块，feed模块和user模块。comm模块提供业务的公共服务，foolmvp-lib作底层服务。

### 业务功能包括：

* 1.准备基础数据user列表数据与feed列表数据，每一条feed所属一个用户，用户之间可以相互关注，为了快速方便实现demo，所有的数据都存在本地数据库。

* 2.feed模块有feed列表case、feed详情case、关注用户case；user模块有用户详情case、关注用户case。
其中，关注用户case同时被feed模块与user模块引用。

* 3.为了让大家看到请求加载的效果，每一个数据api都做了延时加载。

### 特色关注点：
* 1.Presenter只关注业务结果，请求开始、请求结束、请求异常等操作均有公共基类完成，UI的显示由具体UI决定（基于presenterId作区别）。例如：获得feed详情Presenter


            public class FeedDetailPresenterImpl extends BasePresenterImpl<IFeedDetailView> implements FeedDetailPresenter {
                private FeedInteractor feedInteractor;
            
                @Inject
                public FeedDetailPresenterImpl(FeedInteractor feedInteractor){
                    this.feedInteractor = feedInteractor;
                }
            
                /**
                 * Presenter的入口，可做初始化操作
                 */
                @Override
                public void onCreate() {
            
                }
            
                /**
                 * 获得Feed详情
                 *
                 * @param feedId
                 */
                @Override
                public void requestFeed(long feedId) {
                    register(RxUtils.defaultCallback(feedInteractor.getFeed(feedId).map(new Function<FeedEntity, FeedBean>() {
                        @Override
                        public FeedBean apply(FeedEntity feedEntity) throws Exception {
                            return new FeedBean(feedEntity);
                        }
                    }), new AbstractRequestCallBack<FeedBean>(this) {
                        /**
                         * 请求结果回调
                         *
                         * @param data
                         */
                        @Override
                        public void onResponse(FeedBean data) {
                            if(mView != null){
                                mView.doFeedResult(data);
                            }
                        }
                    }));
                }
            }

    
* 2.业务数据改变实时同步。例如：关注用户或取消关注用户，实时同步Feed详情与User详情实时更新。


            public class FollowPresenterImpl extends BasePresenterImpl<IFollowView> implements FollowPresenter {
        
                private UserAssistInteractor userAssistInteractor;
            
                @Inject
                public FollowPresenterImpl(UserAssistInteractor userAssistInteractor) {
                    this.userAssistInteractor = userAssistInteractor;
                }
            
                /**
                 * Presenter的入口，可做初始化操作
                 */
                @Override
                public void onCreate() {
                    register(RxBus.getDefault().register(UpdateRelationship.class, new Consumer<UpdateRelationship>() {
                        @Override
                        public void accept(UpdateRelationship updateRelationship) throws Exception {
                            if (updateRelationship == null) {
                                return;
                            }
                            if (mView != null) {
                                long userId = updateRelationship.getUserId();
                                Relationship relationship = updateRelationship.getRelationship();
                                if (Relationship.DEFAULT.equals(relationship)) {
                                    mView.doUnFollowResult(userId, relationship);
                                } else if (Relationship.FOLLOWED.equals(relationship)) {
                                    mView.doFollowedResult(userId, relationship);
                                }
                            }
                        }
                    }, AndroidSchedulers.mainThread()));
                }
            
                /**
                 * 关注用户
                 *
                 * @param userId
                 */
                @Override
                public void toFollowUser(long userId) {
                    updateUserRelationship(userId, Relationship.FOLLOWED);
                }
            
                /**
                 * 取消关注
                 *
                 * @param userId
                 */
                @Override
                public void toUnFollowUser(long userId) {
                    updateUserRelationship(userId, Relationship.DEFAULT);
                }
            
                private void updateUserRelationship(final long userId, final Relationship relationship) {
                    userAssistInteractor.updateUserRelation(userId, relationship, new AbstractRequestCallBack<Boolean>(this) {
                        @Override
                        public void onResponse(Boolean data) {
                            RxBus.getDefault().post(new UpdateRelationship(userId, relationship));
                        }
                    });
                }
        
            }


* 3.Presenter可拓展、可嵌套使用，即BasePresenterImpl与BaseSubPresenterImpl的继承关系。


            public class UserSubPresenterImpl extends BaseSubPresenterImpl<IUserSubView> implements UserSubPresenter,IUserDetailView,IFollowView {
            
                private UserBean userBean;
            
                @Inject
                UserDetailPresenterImpl userDetailPresenter;
            
                @Inject
                FollowPresenterImpl followPresenter;
            
                @Inject
                public UserSubPresenterImpl(){}
            
                /**
                 * Presenter的入口，可做初始化操作
                 */
                @Override
                public void onCreate() {
                    userDetailPresenter.attachView(this);
                    followPresenter.attachView(this);
                }
            
                /**
                 * 获得用户详情信息
                 *
                 * @param userId
                 */
                @Override
                public void requestUserDetail(long userId) {
                    userDetailPresenter.requestUserDetail(userId);
                }
            
                /**
                 * 切换用户关系
                 */
                @Override
                public void updateUserRelationship() {
                    if(userBean == null){
                        return;
                    }
            
                    if(Relationship.DEFAULT.equals(userBean.getRelationship())){
                        followPresenter.toFollowUser(userBean.getId());
                    }else if(Relationship.FOLLOWED.equals(userBean.getRelationship())){
                        followPresenter.toUnFollowUser(userBean.getId());
                    }
                }
            
                /**
                 * 获得用户详情信息回调
                 *
                 * @param userBean
                 */
                @Override
                public void doUserDetail(UserBean userBean) {
                    this.userBean = userBean;
                    if(mView != null){
                        mView.doUserDetail(userBean);
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
                    if(userBean == null || userId != userBean.getId()){
                        return;
                    }
                    userBean.setRelationship(relationship);
                    if(mView != null){
                        mView.doFollowedResult();
                    }
                }
            
                /**
                 * 取消关注用户回调
                 *
                 * @param userId
                 * @param relationship
                 */
                @Override
                public void doUnFollowResult(long userId, Relationship relationship) {
                    if(userBean == null || userId != userBean.getId()){
                        return;
                    }
                    userBean.setRelationship(relationship);
                    if(mView != null){
                        mView.doUnFollowResult();
                    }
                }
            }

* 4.在M层中，定义数据接口时，尽量同时定义同步接口与异步接口，异步接口依赖同步接口使用，使M层最大化满足P层的随意调用。


            public class FeedInteratorImpl implements FeedInteractor {
            
                private FeedEntityDao feedEntityDao;
            
                @Inject
                public FeedInteratorImpl(FeedEntityDao feedEntityDao) {
                    this.feedEntityDao = feedEntityDao;
                }
            
                /**
                 * 添加一条Feed
                 *
                 * @param feedEntity
                 * @param callBack
                 * @return
                 */
                @Override
                public Disposable saveFeed(FeedEntity feedEntity, RequestCallBack<Long> callBack) {
                    return RxUtils.defaultCallback(saveFeed(feedEntity), callBack);
                }
            
                /**
                 * 添加一条Feed
                 *
                 * @param feedEntity
                 * @return
                 */
                @Override
                public Observable<Long> saveFeed(FeedEntity feedEntity) {
                    return Observable.just(feedEntity).map(new Function<FeedEntity, Long>() {
                        @Override
                        public Long apply(FeedEntity feedEntity) throws Exception {
                            return feedEntityDao.insert(feedEntity);
                        }
                    }).delay(Constants.DELAY_TIME, Constants.TIME_TYPE);
                }
                
                ......
            }

* 5.面向接口编程，进行业务逻辑的物理隔离。比如FollowPresenter，依赖UserAssistInteractor，但其实现类UserAssistInteractorImpl在user模块中实现。
依赖注入贯穿各个业务层中，这时就需要我们在app中对接口与实现类进行注入绑定，UserAssistInteractor接口与UserAssistInteractorImpl实现类通过代理+预埋点的方式将两者关联起来。


        @Module
        public class ApiModule {
            private DaoMaster daoMaster;
            private UserAssistInteractorPlaceholder userAssistInteractorPlaceholder;
        
            public ApiModule(DaoMaster daoMaster, UserAssistInteractorPlaceholder userAssistInteractorPlaceholder) {
                this.daoMaster = daoMaster;
                this.userAssistInteractorPlaceholder = userAssistInteractorPlaceholder;
            }
        
            @Provides
            @Singleton
            public DaoSession provideDaoSession() {
                return daoMaster.newSession();
            }
        
            @Provides
            @Singleton
            public UserAssistInteractor provideUserAssistInteractor() {
                return userAssistInteractorPlaceholder.getUserAssistInteractorProxy();
            }
        
        }

        public class UserAssistInteractorProxy implements UserAssistInteractor {
    
            private UserAssistInteractor userAssistInteractor;
        
            public UserAssistInteractorProxy(UserAssistInteractor userAssistInteractor) {
                this.userAssistInteractor = userAssistInteractor;
            }
        
            /**
             * 更新用户关系
             *
             * @param userId
             * @param relationship
             * @param callBack
             * @return
             */
            @Override
            public Disposable updateUserRelation(long userId, Relationship relationship, RequestCallBack<Boolean> callBack) {
                return userAssistInteractor.updateUserRelation(userId, relationship, callBack);
            }
        
            /**
             * 更新用户关系
             *
             * @param userId
             * @param relationship
             * @return
             */
            @Override
            public Observable<Boolean> updateUserRelation(long userId, Relationship relationship) {
                return userAssistInteractor.updateUserRelation(userId, relationship);
            }
        }
        
        public class UserAssistInteractorPlaceholder {
        
            private UserAssistInteractorProxy userAssistInteractorProxy;
        
            public UserAssistInteractorPlaceholder() {}
        
            public UserAssistInteractorProxy getUserAssistInteractorProxy() {
                return userAssistInteractorProxy;
            }
        
            public void setUserAssistInteractorProxy(UserAssistInteractorProxy userAssistInteractorProxy) {
                this.userAssistInteractorProxy = userAssistInteractorProxy;
            }
        }
        
        public class MainApplication extends BaseApplication {
        
            private UserAssistInteractorPlaceholder userAssistInteractorPlaceholder;
            
            .......
        
            /**
             * 初始化主应用注入组件
             */
            private void initAppComponent() {
                UserApiModule userApiModule = new UserApiModule();
                FeedApiModule feedApiModule = new FeedApiModule();
        
                UserGlobal.init(userApiModule);
                FeedGlobal.init(feedApiModule);
        
                DaggerAppComponent.builder().appApplicationComponent(mApplicationComponent)
                        .userApiModule(userApiModule)
                        .feedApiModule(feedApiModule).build();
        
                userAssistInteractorPlaceholder.setUserAssistInteractorProxy(UserGlobal.getUserComponent().getUserAssistInteractorProxy());
            }
        
            /**
             * 初始化应用公共注入组件
             */
            private void initApplicationComponent() {
                userAssistInteractorPlaceholder = new UserAssistInteractorPlaceholder();
                mApplicationComponent = DaggerAppApplicationComponent.builder()
                        .applicationModule(new ApplicationModule(this))
                        .apiModule(new ApiModule(mDaoMaster, userAssistInteractorPlaceholder))
                        .build();
            }
        
            @Override
            public AppApplicationComponent getApplicationComponent() {
                return mApplicationComponent;
            }
        }
        
        
        
后续将会持续完善该框架，如：

1、添加单元测试

2、增加页面异常销毁对页面数据的恢复

3、增加页面重新创建对Presenter的复用等功能。


联系我：qqiabc521#163.com