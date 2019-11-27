package com.atguigu.aliyunoss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.Test;

public class OssTest {
    //sdk方式创建Oss桶
    @Test
    public void testCreateBucket() {
        String endpoint = "oss-cn-shanghai.aliyuncs.com";
        String accessKeyId = "LTAI4Fonr6VVK65i75gvGhAc";
        String accessKeySecret = "58QmmlXQCrlV6zDflwO7DfukZzz6df";
        String bucketName = "san3";
        OSS ossClient = (new OSSClientBuilder()).build(endpoint, accessKeyId, accessKeySecret);

        ossClient.createBucket(bucketName);
        ossClient.shutdown();
    }
    @Test
    public void testDoesBucketExist(){

    }


}
