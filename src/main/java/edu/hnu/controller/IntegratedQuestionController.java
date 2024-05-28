package edu.hnu.controller;

import com.alibaba.fastjson.JSONObject;
import edu.hnu.dto.IntegratedQuestionDTO;
import edu.hnu.entity.IntegratedQuestion;
import edu.hnu.service.IntegratedQuestionService;
import edu.hnu.utils.JwtUtils;
import edu.hnu.utils.Result;
import edu.hnu.utils.StatusCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (IntegratedQuestion)表控制层
 *
 * @author lx
 * @since 2024-05-14 10:13:20
 */
@Slf4j
@RestController
@RequestMapping("integratedQuestion")
public class IntegratedQuestionController {
  /**
   * 服务对象
   */
  @Resource
  private IntegratedQuestionService integratedQuestionService;
  @Resource
  private HttpServletRequest request;

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("getQuestion")
  public Result queryById(Integer id) {
    log.info("读取八股数据，八股id：{}", id);
    if (id == null) {
      return Result.error(StatusCode.NO_SELECT_ID);
    }
    IntegratedQuestion integratedQuestion = integratedQuestionService.queryById(id);
    return Result.success(integratedQuestion);
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

  /**
   * 获取答案.
   *
   * @param id 主键
   * @return 八股答案字符串
   */
  @GetMapping("getAnswer")
  public Result getAnswer(Integer id) {
    log.info("读取八股答案，八股id：{}", id);
    if (id == null) {
      return Result.error(StatusCode.NO_SELECT_ID);
    }
    String answer = integratedQuestionService.queryAnswerById(id);
    return Result.success(answer);
  }

  /**
   * 获取指定类型八股列表.
   *
   * @param category 八股种类
   */
  @GetMapping("getQuestionList")
  public Result getQuestionList(Integer category){
    log.info("获取指定类型八股列表，category：{}",category);
    List<IntegratedQuestionDTO> list = integratedQuestionService.queryByCategory(category);
    return Result.success(list);
  }

  /**
   * 上传八股题.
   *
   * @param jsonObject 封装的数据对象
   */
  @PostMapping("uploadIntegratedQuestion")
  public Result uploadIntegratedQuestion(@RequestBody JSONObject jsonObject){
    log.info("读取全部题库题目数据，返回列表");
    String token = request.getHeader("token");
    Integer user_id = JwtUtils.getUserId(token);
    String question = jsonObject.getString("question");
    String answer = jsonObject.getString("answer");
    Integer category = jsonObject.getInteger("category");
    String uploadTime = jsonObject.getString("upload_time");
    Integer importanceLevel = jsonObject.getInteger("importanceLevel");
    IntegratedQuestion integratedQuestion = new IntegratedQuestion(user_id,question,answer,category,importanceLevel,uploadTime,0,0);
    integratedQuestionService.insert(integratedQuestion);
    return Result.success();
  }
}

