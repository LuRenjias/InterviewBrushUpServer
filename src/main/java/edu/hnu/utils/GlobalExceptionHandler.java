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
    log.error("访问数据库数据异常",ex);
    // 错误处理逻辑，例如记录日志、创建错误响应等
    return Result.error(StatusCode.DATABASE_ERROR);
  }

  @ExceptionHandler(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
  public Result handleDataTypeException(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException ex) {
    log.error("传入数据类型有误",ex);
    // 错误处理逻辑，例如记录日志、创建错误响应等
    return Result.error(StatusCode.DATA_TYPE_ERROR);
  }
}
