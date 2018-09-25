package com.bizvane.basecontroller.config;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.bizvane.utils.exception.BizException;
import com.bizvane.utils.responseinfo.ResponseData;

    /**
     * 异常处理类
     */
    @RestControllerAdvice
    public class CommonExceptionAdvice {
        private static Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);


        @InitBinder
        public void initBinder(WebDataBinder binder) {
            /**
             * 自动转换日期类型的字段格式
             */
            binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        }
        
        /**
         * 400 - Bad Request
         */
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(MissingServletRequestParameterException.class)
        public ResponseData<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
          ResponseData<Object> responseData = new ResponseData<>();
          responseData.setCode(-1);
          responseData.setMessage("参数解析失败");
          return responseData;
        }
        /**
         * 400 - Bad Request
         */
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseData<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
          ResponseData<Object> responseData = new ResponseData<>();
          responseData.setCode(-1);
          responseData.setMessage("参数解析失败");
          return responseData;
        }

        /**
         * 400 - Bad Request
         */
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseData<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
            logger.error("参数验证失败", e);
            BindingResult result = e.getBindingResult();
            FieldError error = result.getFieldError();
            String field = error.getField();
            String code = error.getDefaultMessage();
            String message = String.format("%s:%s", field, code);
            
            ResponseData<Object> responseData = new ResponseData<>();
            responseData.setCode(-1);
            responseData.setMessage("参数验证失败: "+ message);
            return responseData;
            
        }

        /**
         * 400 - Bad Request
         */
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(BindException.class)
        public ResponseData<Object> handleBindException(BindException e) {
            logger.error("参数绑定失败", e);
            BindingResult result = e.getBindingResult();
            FieldError error = result.getFieldError();
            String field = error.getField();
            String code = error.getDefaultMessage();
            String message = String.format("%s:%s", field, code);
            
            ResponseData<Object> responseData = new ResponseData<>();
            responseData.setCode(-1);
            responseData.setMessage("参数绑定失败: "+ message);
            return responseData;
        }


        /**
         * 400 - Bad Request
         */
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseData<Object> handleServiceException(ConstraintViolationException e) {
            logger.error("参数验证失败", e);
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            ConstraintViolation<?> violation = violations.iterator().next();
            String message = violation.getMessage();
            
            ResponseData<Object> responseData = new ResponseData<>();
            responseData.setCode(-1);
            responseData.setMessage("参数验证失败: "+ message);
            return responseData;
        }

        /**
         * 400 - Bad Request
         */
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(ValidationException.class)
        public ResponseData<Object> handleValidationException(ValidationException e) {
            logger.error("参数验证失败", e);
            
            e.getMessage();
            ResponseData<Object> responseData = new ResponseData<>();
            responseData.setCode(-1);
            responseData.setMessage("参数验证失败：" + e);
            return responseData;
        }

        /**
         * 404 - Not Found
         */
        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ExceptionHandler(NoHandlerFoundException.class)
        public ResponseData<Object> noHandlerFoundException(NoHandlerFoundException e) {
            logger.error("Not Found", e);
            
            ResponseData<Object> responseData = new ResponseData<>();
            responseData.setCode(-1);
            responseData.setMessage("请求资源不存在");
            return responseData;
        }


        /**
         * 405 - Method Not Allowed
         */
        @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
        @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
        public ResponseData<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
            logger.error("不支持当前请求方法", e);
            ResponseData<Object> responseData = new ResponseData<>();
            responseData.setCode(-1);
            responseData.setMessage("请求方法正确");
            return responseData;
        }

        /**
         * 415 - Unsupported Media Type
         */
        @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
        @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
        public ResponseData<Object> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
            logger.error("不支持当前媒体类型", e);
            
            ResponseData<Object> responseData = new ResponseData<>();
            responseData.setCode(-1);
            responseData.setMessage("不支持当前媒体类型");
            return responseData;
        }
        /**
         * 业务层需要自己声明异常的情况
         */
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        @ExceptionHandler(BizException.class)
        public ResponseData<Object> handleServiceException(BizException e) {
            logger.error("业务逻辑异常", e);
            ResponseData<Object> responseData = new ResponseData<>();
            responseData.setCode(-1);
            responseData.setMessage("业务逻辑异常：" + e.getMessage());
            return responseData;
        }

        /**
         * 获取其它异常。包括500
         * @param e
         * @return
         * @throws Exception
         */
        @ExceptionHandler(value = Exception.class)
        public ResponseData<Object> defaultErrorHandler(Exception e){
            logger.error("Exception", e);
            
            ResponseData<Object> responseData = new ResponseData<>();
            responseData.setCode(-1);
            responseData.setMessage("发生异常：未知错误");
            return responseData;
        }
        
        @ExceptionHandler(value = Throwable.class)
        public ResponseData<Object> defaultErrorHandler(Throwable e){
          ResponseData<Object> responseData = new ResponseData<>();
          responseData.setCode(-1);
          responseData.setMessage("发生异常：未知错误");
          return responseData;
        }



    }