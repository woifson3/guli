package com.atguigu.guli.service.oss.service;



import java.io.InputStream;

public interface service {

    String upload(InputStream inputStream,String module,String originalFilename);//流，桶中文件夹的名字，文件原始名称
}
