package edu.hnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    private Integer id;
    private Integer category; // 所属类型，0 是文章，1 是八股
    private Integer categoryId; // 对于类别内容的 id
    private Integer orderId; // 图片在内容中的顺序，1 表示第一张，2表示第二张……
    private String  imageUrl; // 图片的访问地址
}
