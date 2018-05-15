package com.ljj.foolmvp.appcomm.entity;

/**
 * Created by lijunjie on 2018/1/2.
 */

public enum Relationship {
    DEFAULT(0),
    FOLLOWED(1),
    OWNER(-1);

    private final int relation;

    Relationship(int i) {
        this.relation = i;
    }

    public int value() {
        return relation;
    }
}
