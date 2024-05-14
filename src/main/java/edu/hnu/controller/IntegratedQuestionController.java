package edu.hnu.controller;

import com.github.pagehelper.Page;
import edu.hnu.entity.IntegratedQuestion;
import edu.hnu.service.IntegratedQuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (IntegratedQuestion)表控制层
 *
 * @author lx
 * @since 2024-05-14 10:13:20
 */
@RestController
@RequestMapping("integratedQuestion")
public class IntegratedQuestionController {
    /**
     * 服务对象
     */
    @Resource
    private IntegratedQuestionService integratedQuestionService;

    /**
     * 分页查询
     *
     * @param integratedQuestion 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /*@GetMapping
    public ResponseEntity<Page<IntegratedQuestion>> queryByPage(IntegratedQuestion integratedQuestion, PageRequest pageRequest) {
        return ResponseEntity.ok(this.integratedQuestionService.queryByPage(integratedQuestion, pageRequest));
    }*/

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<IntegratedQuestion> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.integratedQuestionService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param integratedQuestion 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<IntegratedQuestion> add(IntegratedQuestion integratedQuestion) {
        return ResponseEntity.ok(this.integratedQuestionService.insert(integratedQuestion));
    }

    /**
     * 编辑数据
     *
     * @param integratedQuestion 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<IntegratedQuestion> edit(IntegratedQuestion integratedQuestion) {
        return ResponseEntity.ok(this.integratedQuestionService.update(integratedQuestion));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.integratedQuestionService.deleteById(id));
    }

}

