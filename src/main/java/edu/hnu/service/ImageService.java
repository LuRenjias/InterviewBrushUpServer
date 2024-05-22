package edu.hnu.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    /**
     * 上传文章/八股的图片.
     */
    int uploadImage(Integer category, Integer categoryId, Integer orderId, MultipartFile file) throws IOException;
}
