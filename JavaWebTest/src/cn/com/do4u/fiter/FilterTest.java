package cn.com.do4u.fiter;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description: 测试过滤器和Servlet方法调用关系
 * Copyright: Copyright (c) 2013
 * Company: 中科软科技股份有限公司
 *
 * @author ChenYu
 * @Date 14-10-29 下午3:08
 */
public class FilterTest implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("进入过滤器");
        PrintWriter pr = resp.getWriter();
        pr.print("aaaaaaa");

        //对请求进行检查或过滤

        //进入Servlet 处理
        chain.doFilter(req, resp);

        //在返回客户之前对响应进行处理

        pr.print("dddddd");
        System.out.println("出过滤器");

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
