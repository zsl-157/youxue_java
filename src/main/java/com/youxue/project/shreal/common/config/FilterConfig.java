package com.youxue.project.shreal.common.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.youxue.project.shreal.entity.Result;
import org.springframework.core.annotation.Order;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

@WebFilter(urlPatterns = "/api/v2/*")
@Order(1)
public class FilterConfig implements Filter {

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String url = req.getRequestURI();
        HttpSession session = req.getSession();
        String sessionAttribute = (String) session.getAttribute("l");
        if (ObjectUtils.isEmpty(sessionAttribute)){
            render(req,res);
        }else{
            filterChain.doFilter(req,res);
        }
    }

    @Override
    public void destroy() {

    }

    public void render(HttpServletRequest req,ServletResponse res) throws IOException {
        OutputStream outputStrream = res.getOutputStream();
        Result result = new Result();
        result.setCm(0,"请登录后操作");
        String resultStr = JSON.toJSONString(result);
        outputStrream.write(resultStr.getBytes("UTF-8"));
        outputStrream.flush();
        outputStrream.close();
    }
}
