package cn.com.do4u.ubuntu.jetty;

import cn.com.do4u.ubuntu.IP_Access;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by root on 14-10-26.
 */
public class IP_AccessHandler extends AbstractHandler {


    @Override
    public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        String host = httpServletRequest.getRemoteHost();

        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        request.setHandled(true);

        PrintWriter out = httpServletResponse.getWriter();
        String ip = httpServletRequest.getRemoteAddr();
        IP_Access ip_access = new IP_Access(ip);
        ip_access.setChain("INPUT");
        String cmd = httpServletRequest.getParameter("cmd");
        StringBuffer str = new StringBuffer("<html><head><title>access ip</title><head><body>");

        if("add".equals(cmd)){
            str.append(ip_access.addTcp()).append("<br>");
            str.append(ip_access.addTcp()).append("<br>");
        }else if("query".equals(cmd)){
            str.append(ip_access.queryInfoBr().getMessage()).append("<br>");
        }else if("del".equals(cmd)){
            str.append(ip_access.delAllLines()).append("<br>");
        }else{
            str.append(ip_access.addTcp()).append("please input command !<br>");
        }
        str.append("</body></html>");
        out.write(str.toString());
        out.flush();
        out.close();
    }
}
