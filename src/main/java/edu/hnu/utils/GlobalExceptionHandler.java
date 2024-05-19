package edu.hnu.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(DataAccessException.class)
  public Result handleSqlException(DataAccessException ex) {
    log.info("访问数据库数据异常");
    // 错误处理逻辑，例如记录日志、创建错误响应等
    return Result.error("数据库访问异常");
  }

  @ExceptionHandler(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
  public Result handleDataTypeException(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException ex) {
    log.info("传入数据类型有误");
    // 错误处理逻辑，例如记录日志、创建错误响应等
    return Result.error("传入数据类型有误");
  }
}
