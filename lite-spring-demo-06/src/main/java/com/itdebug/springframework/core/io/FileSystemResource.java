package com.itdebug.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @创建人 Eric.Lu
 * @创建时间 2023/8/8
 * @地址 https://github.com/itdebug/
 * @描述
 */
public class FileSystemResource implements Resource {

    private final File file;
    private final String path;

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return Files.newInputStream(this.file.toPath());
    }

    public final String getPath() {
        return path;
    }
}
