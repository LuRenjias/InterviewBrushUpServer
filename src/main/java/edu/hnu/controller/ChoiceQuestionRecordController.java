package edu.hnu.controller;

import com.alibaba.fastjson.JSONObject;
import edu.hnu.dto.ResultAnswerDTO;
import edu.hnu.entity.ChoiceQuestionRecord;
import edu.hnu.service.ChoiceQuestionRecordService;
import edu.hnu.utils.JwtUtils;
import edu.hnu.utils.Result;
import edu.hnu.utils.StatusCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (ChoiceQuestionRecord)表控制层
 *
 * @author lx
 * @since 2024-05-14 10:12:18
 */
@Slf4j
@RestController
@RequestMapping("choiceQuestionRecord")
public class ChoiceQuestionRecordController {
  /**
   * 服务对象
   */
  @Resource
  private ChoiceQuestionRecordService choiceQuestionRecordService;
  @Resource
  private HttpServletRequest request;
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

  /**
   * 判断答案是否正确.
   *
   * @param jsonObject 封装的对象
   */
  @PostMapping("submitAnswer")
  public Result submitAnswer(@RequestBody JSONObject jsonObject) {
    Integer id = jsonObject.getInteger("id");
    String answer = jsonObject.getString("answer");
    String choiceTime = jsonObject.getString("choice_time");
    String token = request.getHeader("token");
    Integer user_id = JwtUtils.getUserId(token);
    log.info("判断答案，user_id:{},answer:{},question_id:{}",user_id,answer,id);
    if (id == null || answer == null || choiceTime == null) {
      return Result.error(StatusCode.MISSING_DATA);
    }
    Boolean res = choiceQuestionRecordService.queryByIdAndAnswer(id, user_id, answer, choiceTime);
    if(res){
      return Result.success(new ResultAnswerDTO(true,choiceQuestionRecordService.queryAnswer(id)));
    } else {
      return Result.success(new ResultAnswerDTO(false,choiceQuestionRecordService.queryAnswer(id)));
    }
  }
}

