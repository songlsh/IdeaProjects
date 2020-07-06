package com.sls.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

//@Component
public class AccessFilter extends ZuulFilter {

    private  static  final  Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    /**
     *
     * filterType 默认有4中不同生命周期的过滤类型 pre前 routing中 post【routing和error之后】后 error错误
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 执行顺序 越小越好
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        logger.info("request method is {}, request url is {}", request.getMethod(), request.getRequestURL().toString());

        String accessToken = request.getParameter("accessToken");
        if (null == accessToken) {
            logger.warn(" token is empty");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            return  null;
        }
        logger.info("access is ok");
        return null;
    }
}
