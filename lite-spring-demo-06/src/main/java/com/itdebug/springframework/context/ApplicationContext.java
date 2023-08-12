package com.itdebug.springframework.context;

import com.itdebug.springframework.beans.factory.ListableBeanFactory;
import com.itdebug.springframework.beans.factory.config.HierarchicalBeanFactory;
import com.itdebug.springframework.core.io.ResourceLoader;

/**
 * 应用上下文
 *
 * @创建人 Eric.Lu
 * @创建时间 2023/8/12
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader {
}
