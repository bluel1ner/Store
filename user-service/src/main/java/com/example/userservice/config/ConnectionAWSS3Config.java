package com.example.userservice.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Neevels
 * @version 1.0
 * @date 3/24/2023 2:28 PM
 */
@Configuration
public class ConnectionAWSS3Config {
    private final String awsId = "YCAJEC2QrKhEprDoVeRIF2WB0";
    private final String awsKey = "YCNE-vwuDtHSwRIr6FwQtd47UQKVzm_8sIqCu5aR";

    @Bean
    public BasicAWSCredentials basicAWSCredentials() {
        return new BasicAWSCredentials(awsId, awsKey);
    }
    @Bean
    public AmazonS3 amazonS3() {
        return  AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials()))
                .withEndpointConfiguration(
                        new AmazonS3ClientBuilder.EndpointConfiguration(
                                "storage.yandexcloud.net","ru-central1"
                        )
                )
                .build();
    }

}
