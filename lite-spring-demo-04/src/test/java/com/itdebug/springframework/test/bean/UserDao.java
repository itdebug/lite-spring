package com.itdebug.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/7
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "Eric.Lu");
        hashMap.put("10002", "Toms");
        hashMap.put("10003", "Jacky");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
