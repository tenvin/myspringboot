package com.twg.config;

import com.twg.config.freemarker.EllipsisDirective;
import com.twg.config.freemarker.TestDirective;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by tenvin on 2017/7/2.
 */
@Component
@Slf4j
public class FreemarkerConfig {

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
