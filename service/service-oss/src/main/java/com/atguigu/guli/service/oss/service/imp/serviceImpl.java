package com.atguigu.guli.service.oss.service.imp;

import org.joda.time.DateTime;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.atguigu.guli.service.oss.service.service;
import com.atguigu.guli.service.oss.util.OssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class serviceImpl implements service {


    @Autowired
    private OssProperties ossProperties;
    @Override
    public String upload(InputStream inputStream, String module, String originalFilename) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ossProperties.getEndpoint();
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ossProperties.getKeyId();
        String accessKeySecret = ossProperties.getKeySecret();
        String bucketName=ossProperties.getBucketname();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {//要是还没有桶就创建一个
            ossClient.createBucket(bucketName);
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        }

        String folder= new DateTime().toString("yyyy/MM/dd");
        String Filename= UUID.randomUUID().toString();
        String fileExtention=originalFilename.substring(originalFilename.lastIndexOf("."));//从后向前截取
       //文件夹/2019/11/25/uuid.扩展名    ====》详细到桶中的某个文件夹下的某个文件
        String key=new StringBuffer()
                .append(module)
                .append("/")
                .append(folder)
                .append("/")
                .append(Filename)
                .append(fileExtention).toString();
        // 上传文件流。
        ossClient.putObject(bucketName, key, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
//URL地址   某个文件的访问地址
        return new StringBuffer()
                .append("https://")
                .append(bucketName)
                .append(".")
                .append(endpoint)
                .append("/")
                .append(key).toString();
    }


}
