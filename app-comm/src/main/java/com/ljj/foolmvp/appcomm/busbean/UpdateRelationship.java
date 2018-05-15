package com.ljj.foolmvp.appcomm.busbean;

import com.ljj.foolmvp.appcomm.entity.Relationship;

/**
 * Created by lijunjie on 2018/1/15.
 */

public class UpdateRelationship {
    private Long userId;
    private Relationship relationship;

    public UpdateRelationship(){}

    public UpdateRelationship(Long userId,Relationship relationship){
        this.userId = userId;
        this.relationship = relationship;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }
}
