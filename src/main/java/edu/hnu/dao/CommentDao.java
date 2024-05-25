package edu.hnu.dao;

import edu.hnu.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (Comment)表数据库访问层
 *
 * @author lx
 * @since 2024-05-14 10:12:46
 */
@Mapper
public interface CommentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Comment queryById(Integer id);

    /**
     * 统计总行数
     *
     * @param comment 查询条件
     * @return 总行数
     */
    long count(Comment comment);

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int insert(Comment comment);

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int update(Comment comment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    @Select("select * from comment")
    List<Comment> listAll();

    List<Comment> listIn(@Param("category") Integer category,
                         @Param("ids") String ids);

    List<Comment> queryRootByCategoryIdWithCategory(@Param("category") Integer category,
                                                    @Param("categoryId") Integer categoryId);

    List<Comment> queryByParentComment(Comment comment);

}

