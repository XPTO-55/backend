package br.com.cpa.spring.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AwsConfig {

  @Value("${aws.s3.region}")
  private String region;

  @Value("${aws.s3.key}")
  private String key;

  @Value("${aws.s3.secret}")
  private String secret;

  @Bean
  public AmazonS3 AutheticationS3() {

    log.info("Autenticando S3...");
    return AmazonS3ClientBuilder
        .standard()
        .withRegion(region)
        .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(key, secret)))
        .build();
  }

}
