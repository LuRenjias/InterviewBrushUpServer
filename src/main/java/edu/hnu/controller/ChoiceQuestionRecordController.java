package edu.hnu.controller;

import com.alibaba.fastjson.JSONObject;
import edu.hnu.dto.ChoiceQuestionErrorSetDTO;
import edu.hnu.dto.ResultAnswerDTO;
import edu.hnu.service.ChoiceQuestionRecordService;
import edu.hnu.utils.JwtUtils;
import edu.hnu.utils.Result;
import edu.hnu.utils.StatusCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

  /**
   * 错题集.
   */
  @GetMapping("getErrorSet")
  public Result getErrorSet(){
    String token = request.getHeader("token");
    Integer user_id = JwtUtils.getUserId(token);
    List<ChoiceQuestionErrorSetDTO> list = choiceQuestionRecordService.queryErrorSet(user_id);
    return Result.success(list);
  }
}

