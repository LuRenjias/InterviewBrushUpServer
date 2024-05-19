package edu.hnu.dao;

import edu.hnu.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (Article)表数据库访问层
 *
 * @author lx
 * @since 2024-05-14 10:10:33
 */
@Mapper
public interface ArticleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Article queryById(Integer id);

    /**
     * 查询所有文章数据
     *
     * @return 文章数据对象列表
     */
    @Select("select * from article")
    List<Article> list();

    /**
     * 查询指定行数据
     *
     * @param article 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    //List<Article> queryAllByLimit(Article article, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param article 查询条件
     * @return 总行数
     */
    long count(Article article);

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 影响行数
     */
    int insert(Article article);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Article> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Article> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Article> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Article> entities);

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 影响行数
     */
    int update(Article article);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 修改审核状态为成功
     *
     * @param idList 文章 id
     * @return 影响行数
     */
    int updateStateByIdList(List<Integer> idList);

    int deleteByIdList(List<Integer> idList);
}

