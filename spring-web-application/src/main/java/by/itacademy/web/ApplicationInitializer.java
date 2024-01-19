package by.itacademy.web;

import by.itacademy.web.configuration.ContextConfiguration;
import jakarta.servlet.ServletContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(final ServletContext servletContext) {
        final var webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(ContextConfiguration.class);
        webContext.setServletContext(servletContext);

        final var dispatcherServlet = new DispatcherServlet(webContext);

        final var servlet = servletContext.addServlet("dispatcher", dispatcherServlet);
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }

}