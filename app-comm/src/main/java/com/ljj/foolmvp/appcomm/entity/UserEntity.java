package com.ljj.foolmvp.appcomm.entity;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.converter.PropertyConverter;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lijunjie on 2017/12/28.
 */

@Entity
public class UserEntity {

    @Id(autoincrement = true)
    private Long id;

    @Unique()
    private String name;

    private int age;

    private String email;

    private String phoneNumber;

    private String description;

    @Convert(converter = RelationshipConverter.class, columnType = Integer.class)
    private Relationship relationShip = Relationship.DEFAULT;

    @Generated(hash = 1716579813)
    public UserEntity(Long id, String name, int age, String email, String phoneNumber,
                      String description, Relationship relationShip) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.relationShip = relationShip;
    }

    @Generated(hash = 1433178141)
    public UserEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Relationship getRelationShip() {
        return this.relationShip;
    }

    public void setRelationShip(Relationship relationShip) {
        this.relationShip = relationShip;
    }

    public static class RelationshipConverter implements PropertyConverter<Relationship, Integer> {

        @Override
        public Relationship convertToEntityProperty(Integer databaseValue) {
            int relationship = databaseValue.intValue();
            if (1 == relationship) {
                return Relationship.FOLLOWED;
            } else if (-1 == relationship) {
                return Relationship.OWNER;
            }
            return Relationship.DEFAULT;
        }

        @Override
        public Integer convertToDatabaseValue(Relationship entityProperty) {
            return entityProperty.value();
        }
    }

}
