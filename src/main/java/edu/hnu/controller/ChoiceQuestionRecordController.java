package edu.hnu.controller;

import com.github.pagehelper.Page;
import edu.hnu.entity.ChoiceQuestionRecord;
import edu.hnu.service.ChoiceQuestionRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ChoiceQuestionRecord)表控制层
 *
 * @author lx
 * @since 2024-05-14 10:12:18
 */
@RestController
@RequestMapping("choiceQuestionRecord")
public class ChoiceQuestionRecordController {
    /**
     * 服务对象
     */
    @Resource
    private ChoiceQuestionRecordService choiceQuestionRecordService;

    /**
     * 分页查询
     *
     * @param choiceQuestionRecord 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    /*@GetMapping
    public ResponseEntity<Page<ChoiceQuestionRecord>> queryByPage(ChoiceQuestionRecord choiceQuestionRecord, PageRequest pageRequest) {
        return ResponseEntity.ok(this.choiceQuestionRecordService.queryByPage(choiceQuestionRecord, pageRequest));
    }*/

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<ChoiceQuestionRecord> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.choiceQuestionRecordService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param choiceQuestionRecord 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<ChoiceQuestionRecord> add(ChoiceQuestionRecord choiceQuestionRecord) {
        return ResponseEntity.ok(this.choiceQuestionRecordService.insert(choiceQuestionRecord));
    }

    /**
     * 编辑数据
     *
     * @param choiceQuestionRecord 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<ChoiceQuestionRecord> edit(ChoiceQuestionRecord choiceQuestionRecord) {
        return ResponseEntity.ok(this.choiceQuestionRecordService.update(choiceQuestionRecord));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.choiceQuestionRecordService.deleteById(id));
    }

}

