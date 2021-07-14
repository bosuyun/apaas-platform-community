package com.bosuyun.platform.filestore;

import io.minio.MinioClient;

/**
 * Created by liuyuancheng on 2020/12/15  <br/>
 */
public class MinioClientSingleton {

    private static MinioClient sInstance;

    public static MinioClient getInstance() {
        if (sInstance == null) {//①　　
            synchronized (MinioClientSingleton.class) { //②
                if (sInstance == null) {
                    sInstance = MinioClient.builder()
                            .endpoint("https://play.min.io")
                            .credentials(
                                    "Q3AM3UQ867SPQQA43P2F",
                                    "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG"
                            )
                            .build();
                }
            }
        }
        return sInstance;
    }

}
