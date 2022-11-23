package br.com.cpa.spring.helpers;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class AwsHelper {
  private static final Logger LOG = LoggerFactory.getLogger(AwsHelper.class);

  @Autowired
  private AmazonS3 amazonS3;

  @Value("${aws.s3.bucket}")
  private String s3BucketName;

  // @Async annotation ensures that the method is executed in a different thread
  public S3ObjectInputStream findByName(String fileName) {
    LOG.info("Downloading file with name {}", fileName);
    return amazonS3.getObject(s3BucketName, fileName).getObjectContent();
  }

  public void save(final byte[] bytesFile, final String fileName) {
    try {
      InputStream file = new ByteArrayInputStream(bytesFile);
      // final String fileName = LocalDateTime.now() + "_" + file.getName();
      LOG.info("Uploading file with name {}", fileName);
      final PutObjectRequest putObjectRequest = new PutObjectRequest(s3BucketName, fileName, file, null);
      putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
      amazonS3.putObject(putObjectRequest);
    } catch (AmazonServiceException e) {
      LOG.error("Error {} occurred while uploading file", e.getLocalizedMessage());
    }
  }
}
