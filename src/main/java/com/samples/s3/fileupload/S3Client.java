package com.samples.s3.fileupload;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.net.URL;

@Service
@Slf4j
public class S3Client implements ContentManager {

    private AmazonS3 s3client;
    @Value("${s3Properties.endpointUrl}")
    private String endpointUrl;
    @Value("${s3Properties.accessKey}")
    private String accessKey;
    @Value("${s3Properties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void buildS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setSignerOverride("AWSS3V4SignerType");
        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpointUrl, Regions.US_EAST_1.name()))
                .withPathStyleAccessEnabled(true)
                .withClientConfiguration(clientConfiguration)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
        this.s3client = s3Client;
    }

    @Override
    public String uploadFile(File file, String bucketName) {
        s3client.putObject(
                new PutObjectRequest(bucketName, file.getName(), file)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
        URL url = s3client.getUrl(bucketName, file.getName());
        log.info("URL " + url);
        return url.toString();
    }


}
