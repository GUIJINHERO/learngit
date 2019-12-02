package com.test.platform.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.List;

public class ResponseBodyWrapper implements InitializingBean {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;



    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        decreate(returnValueHandlers);
        requestMappingHandlerAdapter.setReturnValueHandlers(returnValueHandlers);
    }

    private void decreate( List<HandlerMethodReturnValueHandler> returnValueHandlers ){
        returnValueHandlers.forEach(handler ->{
            if(handler instanceof RequestResponseBodyMethodProcessor){
                returnValueHandlers.set(returnValueHandlers.indexOf(handler),new ResponseBodyHandler(handler));
            }
        });
    }
}
