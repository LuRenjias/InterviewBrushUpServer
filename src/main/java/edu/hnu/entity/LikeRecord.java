package edu.hnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeRecord {
    private int id;
    private int userId; // 用户 id
    private int articleId; // 文章 id
    private LocalDateTime likeTime; // 点赞时间
}
