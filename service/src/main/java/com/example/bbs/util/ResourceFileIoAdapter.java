package com.example.bbs.util;

import com.hankcs.hanlp.corpus.io.IIOAdapter;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

// http://haoyuanliu.github.io/2020/07/03/Sprint-Boot-%E5%B7%A5%E7%A8%8B%E4%B8%ADHanLP%E9%85%8D%E7%BD%AE%E8%B7%AF%E5%BE%84BUG%E8%A7%A3%E5%86%B3/

public class ResourceFileIoAdapter implements IIOAdapter {
    @Override
    public InputStream open(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        InputStream is = new FileInputStream(resource.getFile());
        return is;
    }

    @Override
    public OutputStream create(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        OutputStream os = new FileOutputStream(resource.getFile());
        return os;
    }
}
