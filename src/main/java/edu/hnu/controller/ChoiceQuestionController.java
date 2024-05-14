package edu.hnu.controller;

import com.github.pagehelper.Page;
import edu.hnu.entity.ChoiceQuestion;
import edu.hnu.service.ChoiceQuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ChoiceQuestion)表控制层
 *
 * @author lx
 * @since 2024-05-14 10:10:54
 */
@RestController
@RequestMapping("choiceQuestion")
public class ChoiceQuestionController {
    /**
     * 服务对象
     */
    @Resource
    private ChoiceQuestionService choiceQuestionService;

    /**
     * 分页查询
     *
     * @param choiceQuestion 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /*@GetMapping
    public ResponseEntity<Page<ChoiceQuestion>> queryByPage(ChoiceQuestion choiceQuestion, PageRequest pageRequest) {
        return ResponseEntity.ok(this.choiceQuestionService.queryByPage(choiceQuestion, pageRequest));
    }*/

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ChoiceQuestion> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.choiceQuestionService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param choiceQuestion 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ChoiceQuestion> add(ChoiceQuestion choiceQuestion) {
        return ResponseEntity.ok(this.choiceQuestionService.insert(choiceQuestion));
    }

    /**
     * 编辑数据
     *
     * @param choiceQuestion 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ChoiceQuestion> edit(ChoiceQuestion choiceQuestion) {
        return ResponseEntity.ok(this.choiceQuestionService.update(choiceQuestion));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.choiceQuestionService.deleteById(id));
    }

}
