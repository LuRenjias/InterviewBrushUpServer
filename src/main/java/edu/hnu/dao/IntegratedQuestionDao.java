package edu.hnu.dao;

import edu.hnu.dto.IntegratedQuestionDTO;
import edu.hnu.entity.IntegratedQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (IntegratedQuestion)表数据库访问层
 *
 * @author lx
 * @since 2024-05-14 10:13:20
 */
@Mapper
public interface IntegratedQuestionDao {

    /**
     * 查询所有八股数据.
     *
     * @return 所有八股数据
     */
    @Select("select * from interview_brush_up.integrated_question")
    List<IntegratedQuestion> list();

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    IntegratedQuestion queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param integratedQuestion 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    //List<IntegratedQuestion> queryAllByLimit(IntegratedQuestion integratedQuestion, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param integratedQuestion 查询条件
     * @return 总行数
     */
    long count(IntegratedQuestion integratedQuestion);

    /**
     * 新增数据
     *
     * @param integratedQuestion 实例对象
     * @return 影响行数
     */
    int insert(IntegratedQuestion integratedQuestion);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<IntegratedQuestion> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<IntegratedQuestion> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<IntegratedQuestion> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<IntegratedQuestion> entities);

    /**
     * 修改数据
     *
     * @param integratedQuestion 实例对象
     * @return 影响行数
     */
    int update(IntegratedQuestion integratedQuestion);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过主键更新审核通过.
     *
     * @param idList 主键
     */
    void updateStateByIdList1(List<Integer> idList);

    /**
     * 查询八股答案.
     *
     * @param id 八股 id
     * @return answer字段
     */
    String queryAnswerById(Integer id);

    /**
     * 通过主键更新审核不通过.
     *
     * @param idList 主键
     */
    void updateStateByIdList2(List<Integer> idList);

    /**
     * 返回指定类型的八股数据(审核通过).
     */
    List<IntegratedQuestionDTO> list1(Integer category);

    /**
     * 返回指定类型的八股数据(审核通过,限制数量).
     */
    List<IntegratedQuestionDTO> listLimit(int skipCount, Long recommendCount, Integer category);

    List<IntegratedQuestionDTO> queryByQuestion(@Param("question") String question,
                                                    @Param("column") String colum,
                                                    @Param("status") Integer status);
}

