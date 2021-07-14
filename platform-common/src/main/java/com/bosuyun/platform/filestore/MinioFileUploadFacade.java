package com.bosuyun.platform.filestore;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 文件上传
 * <p>
 * Created by liuyuancheng on 2020/12/15  <br/>
 */
@ApplicationScoped
public class MinioFileUploadFacade implements IFileUpload {

    /**
     * 分块上传
     */
    @Override
    public void multiPartUpload() {

    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient = MinioClientSingleton.getInstance();
            // Make 'asiatrip' bucket if not exist.
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("asiatrip").build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("asiatrip").build());
            } else {
                System.out.println("Bucket 'asiatrip' already exists.");
            }
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("asiatrip")
                            .object("容器化.png")
                            .filename("/Users/jerrylau/Downloads/容器化.png")
                            .build());
            System.out.println("'/home/user/Photos/asiaphotos.zip' is successfully uploaded as "
                    + "object 'asiaphotos-2015.zip' to bucket 'asiatrip'.");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}
