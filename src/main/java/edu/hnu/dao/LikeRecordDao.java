package edu.hnu.dao;

import edu.hnu.entity.LikeRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeRecordDao {
    int insert(LikeRecord record);

    int deleteById(@Param("id") int id);

    int deleteByUserIdAndArticleId(@Param("userId") int userId,
                                   @Param("articleId") int articleId);

    LikeRecord queryByUserIdAndArticleId(@Param("userId") int userId,
                                         @Param("articleId") int articleId);
}
