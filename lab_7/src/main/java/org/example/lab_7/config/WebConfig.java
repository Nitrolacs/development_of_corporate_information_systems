package org.example.lab_7.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Locale;

/**
 * Класс конфигурации веб-приложения
 */
@Configuration
@EnableWebMvc
@ComponentScan("org.example.lab_7")
public class WebConfig implements WebMvcConfigurer {

    /**
     * Поле, которое хранит ссылку на контекст приложения Spring.
     */
    private final ApplicationContext applicationContext;

    /**
     * Внедрение с помощью Spring
     *
     * @param applicationContext
     */
    @Autowired
    public WebConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Создает и возвращает экземпляр класса SpringResourceTemplateResolver
     *
     * @return экземпляр класса
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new
                SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode("HTML5");
        return templateResolver;
    }

    /**
     * Создает и возвращает экземпляр класса SpringTemplateEngine
     *
     * @return экземпляр класса
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    /**
     * Настраивает резолверы представлений.
     *
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        registry.viewResolver(resolver);
    }

    /**
     * Метод, который настраивает параметры согласования содержимого для веб-приложения.
     * Согласование содержимого позволяет выбирать подходящий формат представления данных в зависимости от запроса клиента.
     *
     * @param configurer объект ContentNegotiationConfigurer, который предоставляет методы для настройки согласования содержимого.
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .favorPathExtension(true)
                .ignoreAcceptHeader(false)
                .useJaf(false)
                .defaultContentType(MediaType.TEXT_HTML)
                .mediaTypes(new HashMap<String, MediaType>() {
                    {
                        put("json", MediaType.APPLICATION_JSON);
                        put("html", MediaType.TEXT_HTML);
                    }
                });
    }

    /**
     * Метод, который создает и возвращает объект ContentNegotiatingViewResolver, который реализует интерфейс ViewResolver.
     * ContentNegotiatingViewResolver выбирает подходящее представление данных с помощью других реализаций ViewResolver в зависимости от согласования содержимого.
     *
     * @param cnManager      объект ContentNegotiationManager, который управляет стратегией согласования содержимого.
     * @param templateEngine объект SpringTemplateEngine, который используется для обработки шаблонов Thymeleaf.
     * @return объект ContentNegotiatingViewResolver, который выбирает подходящее представление данных.
     */
    @Bean
    @Autowired
    public ContentNegotiatingViewResolver viewResolver(ContentNegotiationManager cnManager,
                                                       SpringTemplateEngine templateEngine) {
        ContentNegotiatingViewResolver cnResolver = new ContentNegotiatingViewResolver();
        cnResolver.setContentNegotiationManager(cnManager);

        ThymeleafViewResolver htmlResolver = new ThymeleafViewResolver();
        htmlResolver.setTemplateEngine(templateEngine);

        List<ViewResolver> resolvers = new ArrayList<>();
        resolvers.add(htmlResolver);
        resolvers.add(new BikeJsonResolver());

        cnResolver.setViewResolvers(resolvers);
        return cnResolver;
    }

    /**
     * Класс BikeJsonResolver, который реализует интерфейс ViewResolver.
     * BikeJsonResolver возвращает представление данных в формате JSON, используя класс MappingJackson2JsonView.
     */
    private static class BikeJsonResolver implements ViewResolver {
        /**
         * Метод, который возвращает объект View (представляет данные в формате JSON).
         *
         * @param viewName имя представления, которое не используется в этом методе.
         * @param locale   локаль, которая не используется в этом методе.
         * @return объект MappingJackson2JsonView, который представляет данные в формате JSON.
         * @throws Exception если произошла ошибка при создании объекта View.
         */
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            MappingJackson2JsonView view = new MappingJackson2JsonView();
            view.setObjectMapper(new ObjectMapper());
            return view;
        }
    }
}
