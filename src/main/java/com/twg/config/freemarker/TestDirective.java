package com.twg.config.freemarker;

import com.twg.entity.User;
import com.twg.service.UserService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by tenvin on 2017/7/2.
 */
@Component
public class TestDirective implements TemplateDirectiveModel {

    @Autowired
    private UserService userService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels,
                        TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {

        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
        User user = userService.findByUsername("AAA");
        environment.setVariable("user", builder.build().wrap(user));
        templateDirectiveBody.render(environment.getOut());

    }
}
