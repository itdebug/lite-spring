package com.itdebug.springframework.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/8
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class DefaultResourceLoader implements ResourceLoader {

	@Override
	public Resource getResource(String location) {
		Assert.notNull(location, "location must not be null");
		if (location.startsWith(CLASSPATH_URL_PREFIX)) {
			return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
		} else {
			try {
				URL url = new URL(location);
				return new UrlResource(url);
			} catch (MalformedURLException e) {
				return new FileSystemResource(location);
			}
		}
	}
}
