package edu.hnu.service.impl;

import cn.hutool.core.io.FileTypeUtil;
import edu.hnu.dao.ArticleDao;
import edu.hnu.dao.ImageDao;
import edu.hnu.dao.IntegratedQuestionDao;
import edu.hnu.entity.Article;
import edu.hnu.entity.Image;
import edu.hnu.entity.IntegratedQuestion;
import edu.hnu.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service("imageService")
public class ImageServiceImpl implements ImageService {

    @Resource
    private ImageDao imageDao;

    @Resource
    private ArticleDao articleDao;

    @Resource
    private IntegratedQuestionDao integratedQuestionDao;

    @Value("${info.image-base-url}")
    private String imageBaseUrl;

    private final List<String> typeList = Arrays.asList("xbm", "tif", "pjp", "svgz", "jpg", "jpeg", "ico", "tiff", "gif", "svg", "jfif", "webp", "png", "bmp", "pjpeg", "avif");
    private final String imageBasePath = System.getProperty("user.dir") + File.separator
            + "src" + File.separator + "main" + File.separator + "resources" + File.separator
            + "static" + File.separator + "image" + File.separator;

    @Override
    public int uploadImage(Integer category, Integer categoryId, Integer orderId, MultipartFile file) throws IOException {
        // 内容不存在
        switch (category) {
            case 0: // 文章
                Article article = articleDao.queryById(categoryId);
                if (article == null) {
                    return 3;
                }
                break;
            case 1: // 八股
                IntegratedQuestion integratedQuestion = integratedQuestionDao.queryById(categoryId);
                if (integratedQuestion == null) {
                    return 3;
                }
        }

        // 未上传
        if (file.isEmpty()) {
            return 0;
        }

        // 判断类型
        String fileName = file.getOriginalFilename();
        String fileType = fileName != null ? fileName.substring(fileName.lastIndexOf(".") + 1) : "err";
        System.out.println(fileType.toLowerCase());
        if (!typeList.contains(fileType.toLowerCase())) {
            return 1;
        } else {
            String type = FileTypeUtil.getType(file.getInputStream());
            System.out.println(type);
            if (!typeList.contains(type)) {
                return 1;
            }
        }

        // 使用UUID表示文件名
        String uuName = UUID.randomUUID().toString();
        // 设置文件访问地址
        String imageUrl = imageBaseUrl + uuName + "." + fileType;
        // 设置文件保存位置
        String savePath = imageBasePath + uuName + "." + fileType;

        File saveFile = new File(savePath);
        if (!saveFile.exists()) saveFile.mkdirs();

        // 保存文件
        file.transferTo(saveFile);

        Image image = new Image(0, category, categoryId, orderId, imageUrl);
        imageDao.insert(image);
        return 2;

    }
}
