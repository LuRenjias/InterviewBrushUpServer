package edu.hnu.controller;

import edu.hnu.service.ImageService;
import edu.hnu.utils.Result;
import edu.hnu.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping
@Slf4j
public class ImageController {
    @Resource
    private ImageService imageService;

    /**
     * 上传文章/八股的图片.
     */
    @PostMapping("uploadImage")
    public Result uploadImage(Integer category, Integer categoryId, Integer orderId, MultipartFile file) throws IOException {
        log.info("uploadImage: 上传文章/八股的图片");

        int i = imageService.uploadImage(category, categoryId, orderId, file);

        return switch (i) {
            case 0 -> Result.error(StatusCode.FILE_IS_EMPTY);
            case 1 -> Result.error(StatusCode.ILLEGAL_FORMAT);
            case 2 -> Result.success();
            case 3 -> Result.error(StatusCode.ILLEGAL_UPLOAD);
            default -> null;
        };
    }
}
