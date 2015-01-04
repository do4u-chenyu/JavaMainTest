package cn.com.do4u.acceptip;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description:
 * Copyright: Copyright (c) 2013
 * Company: 中科软科技股份有限公司
 *
 * @author ChenYu
 * @Date 14-10-29 下午3:06
 */
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入Servlet");
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        request.setAttribute("11","sdfasdfa");
        out.println("<html><body><hi>你好</hi><body></html>");
        System.out.println("退出Servlet");
        System.out.println("        resp.setCharacterEncoding();:"+response.getCharacterEncoding());

    }
}
