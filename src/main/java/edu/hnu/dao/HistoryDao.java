package edu.hnu.dao;

import edu.hnu.entity.History;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HistoryDao {
    int insert(History history);

    History queryByUserIdAndArticleId(@Param("userId") int userId,
                                      @Param("articleId") int articleId);

    List<History> queryByUserId(@Param("userId") Integer userId);

    int update(History history);
}
