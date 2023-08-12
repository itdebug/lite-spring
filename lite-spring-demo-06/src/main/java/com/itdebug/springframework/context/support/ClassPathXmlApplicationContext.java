package com.itdebug.springframework.context.support;

import com.itdebug.springframework.beans.factory.exception.SpringBeansException;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/12
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    public ClassPathXmlApplicationContext(String configLocation) throws SpringBeansException {
        this(new String[]{configLocation});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) throws SpringBeansException {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return this.configLocations;
    }


}
