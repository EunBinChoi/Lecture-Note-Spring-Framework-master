package me.spring.config;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;


public class WebInitializer implements WebApplicationInitializer { // web.xml

	@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        registerDispatcherServlet(servletContext);
        registerCharacterEncodingFilter(servletContext);
    }

    private void registerDispatcherServlet(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.register(RootConfig.class);

        servletContext.setInitParameter("contextConfigLocation", RootConfig.class.getName());
        servletContext.addListener(new ContextLoaderListener(root));
        

        AnnotationConfigWebApplicationContext webapp = new AnnotationConfigWebApplicationContext();
        webapp.register(WebAppConfig.class);


        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("appServlet", new DispatcherServlet(webapp));
        dispatcher.setInitParameter("contextConfigLocation", WebAppConfig.class.getName());
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

    }

    private void registerCharacterEncodingFilter(ServletContext servletContext) {
        FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        characterEncodingFilter.setInitParameter("encoding", "UTF-8");
        characterEncodingFilter.setInitParameter("forceEncoding", "true");
        characterEncodingFilter.addMappingForServletNames(EnumSet.allOf(DispatcherType.class), true, "/**");
    }

    /*
     * 
     * / : localhost:8000/cp/
     * /*: localhost:8000/cp/a/,  localhost:8000/cp/b/ ......
     * /**: 모든 하위 경로 다 포함 
     * ( localhost:8000/cp/,  localhost:8000/cp/a/,  localhost:8000/cp/a/b/,  localhost:8000/cp/a/b/c/)
     * 
     * 
     * */


}