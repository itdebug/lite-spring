package com.itdebug.springframework.bean;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/6
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class UserService {

    private String name;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void getUser() {
        System.out.println("获取用户对象");
    }

    @Override public String toString() {
        return "UserService{" + "name='" + name + '\'' + '}';
    }
}
