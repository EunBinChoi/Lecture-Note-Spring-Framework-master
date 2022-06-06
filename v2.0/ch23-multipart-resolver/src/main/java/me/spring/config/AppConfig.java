package me.spring.config;

import java.util.Locale;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

@Configuration
public class AppConfig {
	@Bean(name = "propertySourcesPlaceholderConfigurer")
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}



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
		mapping.setProperty("NumberFormatException", "error/numberFormatError");
		simpleMappingExceptionResolver.setExceptionMappings(mapping);
		
		simpleMappingExceptionResolver.setDefaultErrorView("error/generalError"); 
		// 등록되지 않은 exception에 보여줄 뷰
		return simpleMappingExceptionResolver;
	}


	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource messageSource() {
		/* ResourceBundleMessageSource: 서버를 배포할 때 messageSource 파일을 읽음
		 * ReloadableResourceBundleMessageSource: 서버 재배포 없이도 리로딩할 시간을 지정해서 해당 시간마다 messageSource 파일을 다시 읽음
		 * */
		
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/messageSource/messageSource"); // 기본 확장자가 *.properties
		/* 만약 Locale 값이 있으면 messageSource_언어코드_국가코드.properties
		 *                없으면 messageSource.properties
		 * 
		 * */

		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(180); // 리소스를 3분 간격으로 다시 리로드
		
		Locale.setDefault(Locale.US); // 제공하지 않는 언어로 요청이 들어왔을 때 MessageSource에서 사용할 기본 언어
		
		return messageSource;
	}
	
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		final int MAX_UPLOAD_SIZE = 10 * 1024 * 1024;
		final int MAX_MEMORY_SIZE = 10240;
		
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(MAX_UPLOAD_SIZE);   // 기본값 -1 (제한 없음), 1024 * 1024 * 10 = 10MB
		multipartResolver.setMaxInMemorySize(MAX_MEMORY_SIZE); // 기본값 10240B, 디스크에 임시 파일을 생성하기 전에 메모리 보관 최대 바이트 크기
		multipartResolver.setDefaultEncoding("UTF-8");
		return multipartResolver;
	}
}
