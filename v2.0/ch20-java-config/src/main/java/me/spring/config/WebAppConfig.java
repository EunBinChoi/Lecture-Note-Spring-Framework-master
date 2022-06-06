package me.spring.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


/* Controller, View Resolver */
@Configuration
@EnableWebMvc // <annotation-driven /> 
				// (@Controller에 요청을 보내기 위해 필요한 HandlerMapping과
				// HandlerAdapter를 Bean으로 등록)
@ComponentScan(basePackages = "me.spring", // me.spring.*, me.spring.*.* ...
		useDefaultFilters = false, // spring에서 관리하고 있는 spring stereotype들을 등록하지 않음!
		includeFilters = { 
				@Filter(type = FilterType.ANNOTATION, 
						value = { Controller.class }) })
public class WebAppConfig implements WebMvcConfigurer { // servlet-context.xml

	@Bean(name = "internalResourceViewResolver")
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}

	///////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		/*  /resources/ : /resources/ 만 포함!
		 *  /resources/*: /resources/css, /resources/js, /resources/icon,  /resources/sql
		 *  
		 *  /resources/** : /resources/css/header.css, /resources/js/header.js
		 *  				/resources/css/member/login.css
		 *  (resources로 시작되는 모든 경로, 1계층이든, 2계층이든, 3계층 이상)
		 * */
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub

		registry.addViewController("/").setViewName("index");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		// application/octect-stream
		// 바이트 배열 읽기/쓰기
		converters.add(new ByteArrayHttpMessageConverter());

		// 모든 미디어 타입(*/*)을 String으로 읽음. text/plain에 대한 String을 씀
		converters.add(new StringHttpMessageConverter());

		// application/x-www-form-urlencoded
		// Form data is read from and written into a MultiValueMap.
		converters.add(new FormHttpMessageConverter());

		// application/xml
		converters.add(createXmlHttpMessageConverter());

		// application/json
		converters.add(new MappingJacksonHttpMessageConverter());
	}

	private HttpMessageConverter<Object> createXmlHttpMessageConverter() {
		MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();

		XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
		xmlConverter.setMarshaller(xstreamMarshaller);
		xmlConverter.setUnmarshaller(xstreamMarshaller);

		return xmlConverter;

	}

	@Override
	public Validator getValidator() {
		// TODO Auto-generated method stub

		return null;
	}

}
