package com.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 * 覆盖springboot默认的异常处理
 * 
 * @author zincredible
 * @date 2019/06/14 09:44:04
 */
@Controller
public class ExceptionController extends BasicErrorController {

	public ExceptionController(ServerProperties serverProperties) {
		super(new DefaultErrorAttributes(), serverProperties.getError());
	}

	/**
	 * 覆盖默认的Json响应
	 */
	@Override
	public ResponseEntity<Map<String, Object>> error(
			HttpServletRequest request) {
		Map<String, Object> body = getErrorAttributes(request,
				isIncludeStackTrace(request, MediaType.ALL));
		HttpStatus status = getStatus(request);
		return new ResponseEntity<Map<String, Object>>(body, status);
	}

	/**
	 * 覆盖默认的HTML响应
	 */
	@Override
	public ModelAndView errorHtml(HttpServletRequest request,
			HttpServletResponse response) {
		// 请求的状态
		HttpStatus status = getStatus(request);
		response.setStatus(getStatus(request).value());

		Map<String, Object> model = getErrorAttributes(request,
				isIncludeStackTrace(request, MediaType.TEXT_HTML));
		ModelAndView modelAndView = resolveErrorView(request, response, status,
				model);
		// 指定自定义的视图
		return (modelAndView == null
				? new ModelAndView("index", model)
				: modelAndView);
	}

}
