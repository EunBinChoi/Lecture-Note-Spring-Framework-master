package me.spring.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class AppConfig {
	@Bean(name = "propertySourcesPlaceholderConfigurer")
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	/*
	 * Dispatcher Servlet에서
	 * Request Mapping 핸들러 어댑터 -> HandlerMethodArgumentResolver -- (MappingJacksonHttpMessageConverter) --> Controller
	 *
	 * */
	// https://velog.io/@jihoson94/Spring-MVC-HandlerAdapter-%EB%B6%84%EC%84%9D%ED%95%98%EA%B8%B0
//	@Bean(name = "requestMappingHandlerAdapter")
//	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
//		RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
//		MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
//		List<MediaType> media = new ArrayList<>();
//		media.add(MediaType.valueOf("text/plain;charset=UTF-8"));
//		media.add(MediaType.valueOf("multipart/form-data;charset=UTF-8"));
//		media.add(MediaType.valueOf("application/x-www-form-urlencoded;charset=UTF-8"));
//		media.add(MediaType.valueOf("application/json;charset=UTF-8"));
//
//		converter.setSupportedMediaTypes(media);
//		adapter.getMessageConverters().add(converter);
//		return adapter;
//	}


	/*
	 * 가장 우선으로 적용되도록 @RequestMapping 을 이용한 핸들러 매핑 전략을 등록한다.
	 * 따라서 다른 디폴트 핸들러 매핑 전략은 자동 등록되지 않는다는 점을 기억해두자.
	 *
	 * */
//	@Bean(name = "defaultAnnotationHandlerMapping")
//	public DefaultAnnotationHandlerMapping defaultAnnotationHandlerMapping() {
//		return new DefaultAnnotationHandlerMapping();
//	}

	/* AnnotationMethodHandlerAdapter에 @Valid 추가
	 *
	 * AnnotationMethodHandlerAdapter는 DispatcherServlet 이 자동으로 등록해주는 디폴트 핸들러 어댑터다.
	 * 하지만 디폴트 설정을 변경하려면 빈으로 등록해야 한다.
	 * <mvc:annotation-driven> 은 기본적으로 이 AnnotationMethodHandlerAdapter 를 빈으로 등록한다.
	 * 따라서 <mvc:annotation-driven> 을 사용했을 때는 직접 AnnotationMethodHandlerAdapter 빈을 등록해서는 안된다.
	 * 또, 핸들러 어댑터 전략을 빈으로 등록했으므로 그 밖의 디폴트 핸들러 어댑터 전략은 자동등록되지 않는다.

	 * */
	@Bean(name = "annotationMethodHandlerAdapter")
	public AnnotationMethodHandlerAdapter annotationMethodHandlerAdapter() {
		AnnotationMethodHandlerAdapter annotationMethodHandlerAdapter = new AnnotationMethodHandlerAdapter();
		// web binding할 때 @valid annotation을 통해 데이터를 검증하겠다!
		ConfigurableWebBindingInitializer configurableWebBindingInitializer = new ConfigurableWebBindingInitializer();
		configurableWebBindingInitializer.setValidator(localValidatorFactoryBean());
		annotationMethodHandlerAdapter.setWebBindingInitializer(configurableWebBindingInitializer);
		return annotationMethodHandlerAdapter;
	}



	/*
	 * @Valid 사용을 위함
	 * 자동 등록되는 ConfigurableWebBindingInitializer 의 validator 프로퍼티에 적용할 Validator 타입의 빈을 지정할 수 있다.
	 * 모든 컨테이너에 일괄 적용하는 검증기는 디폴트로 추가되는 JSR-303 방식의 LocalValidatorFactoryBean 이면 충분하다.
	 * 모델 단위의 검증기는 컨트롤러 레벨에서 직접 DI 받아서 사용하는 편이 낫기 때문이다.
	*/
	@Bean(name = "localValidatorFactoryBean")
	public LocalValidatorFactoryBean localValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}
	

	
	
	/*
	 * simple 예외 리졸버
	 * */
	@Bean(name = "simpleMappingExceptionResolver")
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
		
		Properties mapping = new Properties();
		mapping.setProperty("NullPointerException", "error/nullError");
		mapping.setProperty("ArrayIndexOutOfBoundsException", "error/arrayBoundsError");
		mapping.setProperty("ArithmeticException", "error/arithmeticError");
		simpleMappingExceptionResolver.setExceptionMappings(mapping);
		
		simpleMappingExceptionResolver.setDefaultErrorView("error/generalError"); 
		// 등록되지 않은 exception에 보여줄 뷰
		return simpleMappingExceptionResolver;
	}
	
	
}
