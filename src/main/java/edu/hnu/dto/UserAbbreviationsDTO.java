package edu.hnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAbbreviationsDTO {
    private Integer id;
    private String nickname;
    private String avatarUrl;
    private boolean followed; // 是否关注，true表示关注，false表示未关注
}
