package cn.com.do4u.ubuntu.jetty;

import org.eclipse.jetty.server.Server;

/**
 * Created by root on 14-10-26.
 */
public class StartMain {


    public static void main(String[] args) throws Exception {
        Server server = new Server(12306);
        server.setHandler(new IP_AccessHandler());
        server.start();
        server.join();

    }
}
