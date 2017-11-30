/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iso;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.evaluator.AttributeEvaluatorFactory;
import org.apache.tiles.extras.complete.CompleteAutoloadTilesContainerFactory;
import org.apache.tiles.extras.complete.CompleteAutoloadTilesInitializer;
import org.apache.tiles.factory.AbstractTilesContainerFactory;
import org.apache.tiles.request.render.BasicRendererFactory;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.freemarker.render.FreemarkerRendererBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 *
 * @author ivo
 */
@Configuration
@EnableWebMvc
public class ConfigurationForTiles extends WebMvcConfigurerAdapter{
    
    
    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions(new String[] { "WEB-INF/tiles-def.xml" });
        configurer.setCheckRefresh(true);
        configurer.setTilesInitializer(new CompleteAutoloadTilesInitializer() {
           @Override
           protected AbstractTilesContainerFactory createContainerFactory(ApplicationContext context) {
                return new CompleteAutoloadTilesContainerFactory() {
                    @Override
                    protected void registerAttributeRenderers(BasicRendererFactory rendererFactory, ApplicationContext applicationContext, TilesContainer container, AttributeEvaluatorFactory attributeEvaluatorFactory) {
                        super.registerAttributeRenderers(rendererFactory, applicationContext, container, attributeEvaluatorFactory);
                        FreemarkerRendererBuilder freemarkerRenderer = FreemarkerRendererBuilder.createInstance();
                        freemarkerRenderer.setApplicationContext(applicationContext)
                                .setParameter("TemplatePath", "/")
                                .setParameter("NoCache", "true")
                                .setParameter("ContentType", "text/html")
                                .setParameter("template_update_delay", "0")
                                .setParameter("default_encoding", "UTF-8");
                        rendererFactory.registerRenderer("freemarker", freemarkerRenderer.build());
                    }
                };
            }
        });
        return configurer;
    }
    
    @Bean
    public TilesViewResolver tilesViewResolver() {
        TilesViewResolver resolver = new TilesViewResolver();
        return resolver;
    }
    
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver viewResolver = new TilesViewResolver();
        registry.viewResolver(viewResolver);
    }
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
