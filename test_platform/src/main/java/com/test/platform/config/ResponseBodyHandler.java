package com.test.platform.config;

import com.test.shiro.dto.ShiroUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public class ResponseBodyHandler implements HandlerMethodReturnValueHandler {

    private HandlerMethodReturnValueHandler delegate;

    public ResponseBodyHandler(HandlerMethodReturnValueHandler handlerMethodReturnValueHandler){
        this.delegate = handlerMethodReturnValueHandler;
    }
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return delegate.supportsReturnType(methodParameter);
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        if (!(o instanceof ShiroUser)){
            o = new ShiroUser();
        }

        delegate.handleReturnValue(o,methodParameter,modelAndViewContainer,nativeWebRequest);

    }
}
