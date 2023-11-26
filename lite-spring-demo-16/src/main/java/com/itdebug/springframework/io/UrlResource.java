package com.itdebug.springframework.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/8
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class UrlResource implements Resource {

	private final URL url;

	public UrlResource(URL url) {
		Assert.notNull(url, "URL must not be null");
		this.url = url;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		URLConnection connection = this.url.openConnection();
		try {
			return connection.getInputStream();
		} catch (IOException e) {
			if (connection instanceof HttpURLConnection) {
				((HttpURLConnection) connection).disconnect();
			}
			throw e;
		}
	}
}
