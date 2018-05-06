package top.sillyfan.security.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 记录请求的时间
 */
//@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("time filter start");

        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("time filter end");

    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
