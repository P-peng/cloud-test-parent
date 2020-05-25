package com.ge.test.service.api.member.dto;

import java.io.Serializable;

/**
 * @author dengzhipeng
 * @version 1.0
 * @date 2020/5/20 0020
 */
public class UserDto implements Serializable {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
