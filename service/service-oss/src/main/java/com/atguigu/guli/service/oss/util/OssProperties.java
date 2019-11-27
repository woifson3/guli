package com.atguigu.guli.service.oss.util;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "aliyun.oss.file")
public class OssProperties {
    public String endpoint;
    public String keyId;
    public String keySecret;
    public String bucketname;
}
