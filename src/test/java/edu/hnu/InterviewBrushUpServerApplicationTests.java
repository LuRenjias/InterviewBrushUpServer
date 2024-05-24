package edu.hnu;

import edu.hnu.entity.User;
import edu.hnu.service.ArticleService;
import edu.hnu.service.FollowService;
import edu.hnu.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class InterviewBrushUpServerApplicationTests {

    @Resource
    private FollowService followService;

    @Resource
    protected ArticleService articleService;

    @Test
    void contextLoads() {
        Integer id = 3;
        User user = new User();
        user.setId(id);
        System.out.println(JwtUtils.getToken(user));
        followService.followList(4);
        articleService.myList(id);
    }

}
