package com.ljj.foolmvp.appcomm.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.ljj.foolmvp.greendao.DaoSession;
import com.ljj.foolmvp.greendao.UserEntityDao;
import com.ljj.foolmvp.greendao.FeedEntityDao;

/**
 * Created by lijunjie on 2017/12/28.
 */

@Entity
public class FeedEntity {

    @Id(autoincrement = true)
    private Long id;

    private String title;

    private String content;

    private Long ownerId;

    @ToOne(joinProperty = "ownerId")
    private UserEntity owner;

    private int likeCount;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1856103460)
    private transient FeedEntityDao myDao;

    @Generated(hash = 2052211237)
    public FeedEntity(Long id, String title, String content, Long ownerId,
            int likeCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.ownerId = ownerId;
        this.likeCount = likeCount;
    }

    @Generated(hash = 398937755)
    public FeedEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public int getLikeCount() {
        return this.likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    @Generated(hash = 1847295403)
    private transient Long owner__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 152791202)
    public UserEntity getOwner() {
        Long __key = this.ownerId;
        if (owner__resolvedKey == null || !owner__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserEntityDao targetDao = daoSession.getUserEntityDao();
            UserEntity ownerNew = targetDao.load(__key);
            synchronized (this) {
                owner = ownerNew;
                owner__resolvedKey = __key;
            }
        }
        return owner;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 891578537)
    public void setOwner(UserEntity owner) {
        synchronized (this) {
            this.owner = owner;
            ownerId = owner == null ? null : owner.getId();
            owner__resolvedKey = ownerId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1988632329)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFeedEntityDao() : null;
    }



}
