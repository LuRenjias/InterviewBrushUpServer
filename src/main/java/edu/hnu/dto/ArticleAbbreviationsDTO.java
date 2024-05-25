package edu.hnu.dto;

import edu.hnu.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleAbbreviationsDTO {
    private Integer id; // 文章id
    private String title; // 文章标题
    private String content; // 文章部分内容
    private LocalDateTime createTime; // 文章创建时间
    private Integer likesCount; // 文章点赞数量
    private Integer viewsCount; // 文章浏览量
    private Integer commentsCount; // 文章评论数量
    private List<Image> images; // 文章图片访问地址

    private Integer userId; // 文章作者id
    private String nickname; // 作者昵称
    private String avatarUrl; // 作者头像

    private LocalDateTime likeTime; // 点赞时间
    private LocalDateTime visitTime; // 访问时间

}
