package edu.hnu.dao;

import edu.hnu.entity.Collection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Collection)表数据库访问层
 *
 * @author lx
 * @since 2024-05-14 10:12:33
 */
@Mapper
public interface CollectionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Collection queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param collection 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    //List<Collection> queryAllByLimit(Collection collection, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param collection 查询条件
     * @return 总行数
     */
    long count(Collection collection);

    /**
     * 新增数据
     *
     * @param collection 实例对象
     * @return 影响行数
     */
    int insert(Collection collection);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Collection> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Collection> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Collection> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Collection> entities);

    /**
     * 修改数据
     *
     * @param collection 实例对象
     * @return 影响行数
     */
    int update(Collection collection);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

