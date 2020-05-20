package com;

import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 
 * @ClassName: AccessFilter
 * @Description: token校验/安全认证
 * @author zhangsike
 * @date 2018年5月1日 下午9:42:18
 *
 */
@Component
public class AccessFilter extends ZuulFilter
{

	private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

	@Override
	public String filterType()
	{
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder()
	{
		return 0;
	}

	@Override
	public boolean shouldFilter()
	{
		return true;
	}

	@Override
	public Object run()
	{
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		Object token = request.getParameter("token");

		// 校验token
		if (token == null)
		{
			logger.info("token为空，禁止访问!");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.getResponse().setContentType("application/json;charset=UTF-8");
			ctx.getResponse().setCharacterEncoding("UTF-8");
			ctx.setResponseBody("{\"error\":\"token为空，禁止访问!\"}");
			return null;
		} else
		{
			ctx.setSendZuulResponse(true);
			ctx.setResponseStatusCode(200);
		}
		// 添加Basic Auth认证信息
		ctx.addZuulRequestHeader("Authorization", "Basic " + getBase64Credentials("app01", "*****"));
		return null;
	}

	private String getBase64Credentials(String username, String password)
	{
		String plainCreds = username + ":" + password;
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		return new String(base64CredsBytes);
	}

}
