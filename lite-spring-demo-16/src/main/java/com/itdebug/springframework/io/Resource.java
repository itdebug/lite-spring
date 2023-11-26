package com.itdebug.springframework.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/8
 * @地址 https://github.com/itdebug/
 * @描述
 */
public interface Resource {

	InputStream getInputStream() throws IOException;
}
