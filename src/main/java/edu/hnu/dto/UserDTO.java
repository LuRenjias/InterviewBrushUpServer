package edu.hnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String nickname;
    private String avatarUrl;
    private String gender;
    private Integer followingCount; // 关注数
    private Integer followersCount; // 粉丝数
    private String userIdentity;
    private Long articleCount;
    private boolean followed; // 是否关注，true表示关注，false表示未关注
}
