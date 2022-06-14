package com.esgi.modules.file.infrastructure;

import com.esgi.modules.file.domain.Base64File;
import com.esgi.modules.file.domain.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.Base64;

@Repository
public class S3FileRepository implements FileRepository {
    @Value("${s3.bucket}")
    private String bucketName;
    private final S3Client s3Client;

    public S3FileRepository(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public void save(byte[] file, String fileName) {
        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileName)
                        .build(),
                RequestBody.fromBytes(file));
    }

    @Override
    public Base64File get(String fileName) {
        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(
                GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileName)
                        .build());
        try {
            byte[] bytes = response.readAllBytes();
            String base64File = Base64.getEncoder().encodeToString(bytes);
            return new Base64File(base64File);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String fileName) {
        s3Client.deleteObject(
                DeleteObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileName)
                        .build()
        );
    }
}
