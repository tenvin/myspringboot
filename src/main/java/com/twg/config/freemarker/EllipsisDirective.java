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
public class EllipsisDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        String text = "";
        int length = 0;
        if(params.get("text") != null){
            text = ((SimpleScalar) params.get("text")).getAsString();
        }
        if(params.get("length") != null){
            length = Integer.valueOf(((SimpleScalar) params.get("length")).getAsString());
        }
        if(length < text.length()){
            text = "..." + text.substring(text.length() - length);
        }
        env.getOut().write(text);
    }
}
