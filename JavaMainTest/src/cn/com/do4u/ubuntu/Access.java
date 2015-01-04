package cn.com.do4u.ubuntu;
/**
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



public class Access extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String ip = req.getRemoteAddr();
        IP_Access ip_access = new IP_Access(ip);
        ip_access.setChain("INPUT");
        String cmd = req.getParameter("cmd");
        StringBuffer str = new StringBuffer("<html><head><title>access ip</title><head><body>");

        if("add".equals(cmd)){
            str.append(ip_access.addTcp()).append("<br>");
            str.append(ip_access.addTcp()).append("<br>");
        }

        if("query".equals(cmd)){
            str.append(ip_access.queryInfoBr().getMessage()).append("<br>");
        }

        if("del".equals(cmd)){
            str.append(ip_access.delAllLines()).append("<br>");
        }
        str.append("</body></html>");
        out.write(str.toString());
        out.flush();
        out.close();
        super.service(req, resp);
    }
}
*/

public class Access {

}