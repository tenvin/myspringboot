package com.twg.config.freemarker;

import freemarker.template.TemplateModelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by tenvin on 2017/7/2.
 */
@Component
public class FreemarkerConfig {
    Logger log = LoggerFactory.getLogger(FreemarkerConfig.class);

    @Autowired
    private freemarker.template.Configuration configuration;
    @Autowired
    private TestDirective testDirective;
    @Autowired
    private EllipsisDirective ellipsisDirective;

    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        configuration.setSharedVariable("test",testDirective);
        configuration.setSharedVariable("ellipsis",ellipsisDirective);

        log.info("init freemarker sharedVariables {site} success...");
    }
}
