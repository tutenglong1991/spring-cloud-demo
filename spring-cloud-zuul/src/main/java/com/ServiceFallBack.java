package com;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.netflix.zuul.context.RequestContext;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;


import javax.servlet.http.HttpServletRequest;
/**
 * 
* @ClassName: ServiceFallBack 
* @Description: 熔断器 
* @author zhangsike
* @date 2018年5月1日 下午9:52:03 
*
 */
@Component
public class ServiceFallBack implements FallbackProvider
{

	@Override
	public String getRoute()
	{
		return "spring-boot-service-c";
	}

	@Override
	public ClientHttpResponse fallbackResponse()
	{
		return new ClientHttpResponse()
		{
			@Override
			public HttpStatus getStatusCode() throws IOException
			{
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException
			{
				return this.getStatusCode().value();
			}

			@Override
			public String getStatusText() throws IOException
			{
				return this.getStatusCode().getReasonPhrase();
			}

			@Override
			public void close()
			{

			}

			@Override
			public InputStream getBody() throws IOException
			{
				RequestContext ctx = RequestContext.getCurrentContext();
				HttpServletRequest request = ctx.getRequest();
				return new ByteArrayInputStream((request.getRequestURI() + " 对应的服务不可用").getBytes());
			}

			@Override
			public HttpHeaders getHeaders()
			{
				HttpHeaders headers = new HttpHeaders();
				MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
				headers.setContentType(mt);
				return headers;
			}
		};
	}

	@Override
	public ClientHttpResponse fallbackResponse(Throwable cause)
	{
		return new ClientHttpResponse()
		{
			@Override
			public HttpStatus getStatusCode() throws IOException
			{
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException
			{
				return this.getStatusCode().value();
			}

			@Override
			public String getStatusText() throws IOException
			{
				return this.getStatusCode().getReasonPhrase();
			}

			@Override
			public void close()
			{

			}

			@Override
			public InputStream getBody() throws IOException
			{
				RequestContext ctx = RequestContext.getCurrentContext();
				HttpServletRequest request = ctx.getRequest();
				return new ByteArrayInputStream((request.getRequestURI() + " 对应的服务不可用1").getBytes());
			}

			@Override
			public HttpHeaders getHeaders()
			{
				HttpHeaders headers = new HttpHeaders();
				MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
				headers.setContentType(mt);
				return headers;
			}
		};
	}
}
