package edu.hnu.controller;

import edu.hnu.dto.ChoiceQuestionDoneDTO;
import edu.hnu.dto.ChoiceQuestionListDTO;
import edu.hnu.dto.ChoiceQuestionListOrderByTimeDTO;
import edu.hnu.dto.ChoiceQuestionUnDoneDTO;
import edu.hnu.entity.ChoiceQuestion;
import edu.hnu.entity.ChoiceQuestionRecord;
import edu.hnu.service.ChoiceQuestionService;
import edu.hnu.utils.JwtUtils;
import edu.hnu.utils.Result;
import edu.hnu.utils.StatusCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ChoiceQuestion)表控制层
 *
 * @author lx
 * @since 2024-05-14 10:10:54
 */
@Slf4j
@RestController
@RequestMapping("choiceQuestion")
public class ChoiceQuestionController {
    /**
     * 服务对象
     */
    @Resource
    private ChoiceQuestionService choiceQuestionService;
    @Resource
    private HttpServletRequest request;

    @GetMapping("getQuestion")
    public Result getQuestion(Integer category) {
        log.info("读取题库数据，题库 category：{}", category);
        if (category == null) {
            return Result.error(StatusCode.NO_SELECT_ID);
        }
        List<ChoiceQuestion> choiceQuestionList = choiceQuestionService.queryByCategory(category);
        return Result.success(choiceQuestionList);
    }

    /**
     * 做题.
     *
     * @param category 题目类别
     * @param page     当前进度
     */
    @GetMapping("getChoiceQuestion")
    public Result getChoiceQuestion(Integer category, Integer page) {
        log.info("读取题库题目数据，题库 category：{}，当前page:{}", category, page);
        List<ChoiceQuestion> list = choiceQuestionService.ListChoiceQuestion(page, 1, category);
        ChoiceQuestion choiceQuestion = list.get(0); // 得到题库数据
        String token = request.getHeader("token");
        Integer user_id = JwtUtils.getUserId(token);
        ChoiceQuestionRecord choiceQuestionRecord = choiceQuestionService.queryRecordById(user_id, choiceQuestion.getId());
        if (choiceQuestionRecord != null) {
            return Result.success(new ChoiceQuestionDoneDTO(true, choiceQuestion.getQuestion(), choiceQuestionRecord.getUserOption(), choiceQuestion.getType(), choiceQuestion.getCorrectOption()));
        }
        return Result.success(new ChoiceQuestionUnDoneDTO(false, choiceQuestion.getQuestion(), choiceQuestion.getOptions(), choiceQuestion.getType()));
    }

    /**
     * 返回指定题库数据.
     *
     * @param category 类型
     */
    @GetMapping("getChoiceQuestionList")
    public Result getChoiceQuestionList(Integer category) {
        log.info("读取题库题目数据，返回列表，题库 category：{}", category);
        String token = request.getHeader("token");
        Integer user_id = JwtUtils.getUserId(token);
        ChoiceQuestionListDTO choiceQuestionListDTO = new ChoiceQuestionListDTO(category, choiceQuestionService.countByCategory(category), choiceQuestionService.countCompletedQuestions(category, user_id));
        return Result.success(choiceQuestionListDTO);
    }

    /**
     * 返回全部数据列表.
     */
    @GetMapping("getAllChoiceQuestionList")
    public Result getAllChoiceQuestionList() {
        log.info("读取全部题库题目数据，返回列表");
        String token = request.getHeader("token");
        Integer user_id = JwtUtils.getUserId(token);
        List<ChoiceQuestionListDTO> list = choiceQuestionService.listAll(user_id);
        return Result.success(list);
    }

    @GetMapping("getExerciseSet")
    public Result getExerciseSet() {
        String token = request.getHeader("token");
        Integer user_id = JwtUtils.getUserId(token);
        List<ChoiceQuestionListOrderByTimeDTO> list = choiceQuestionService.queryByUId(user_id);
        return Result.success(list);
    }

    /**
     * 根据选择题题干部分模糊查询.
     */
    @GetMapping("search")
    public Result search(String keyword, Integer orderType) {
        log.info("search: 根据选择题题干部分模糊查询");

        List<ChoiceQuestion> choiceQuestions = choiceQuestionService.queryByQuestion(keyword, orderType);

        return Result.success(choiceQuestions);
    }
}

