package cn.com.do4u.ubuntu;



/**

 * Created by root on 14-10-18.
 */
public class IP_Access {
    private Exec exec = new Exec();
    private String chain = "JAVA";
    private String ip;
    private int port;


    public void setChain(String chain) {
        this.chain = chain;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public IP_Access(String ip){
        this.ip = ip;
    }

    public IP_Access(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public boolean validIp(){
        boolean b = true;
        if(ip =="cuowu" ){

        }
        return b;
    }


    public boolean validIPandPort(){
        boolean b = true;
        if(port == 0){

        }
        return b;
    }


    public static void main(String[] args) {
        test("9.9.9.9");

    }

    private static void test(String ip){
        IP_Access ip_access = new IP_Access(ip);
        ip_access.setChain("INPUT");
        ip_access.addUdp( );
        ip_access.addTcp( );
        ip_access.queryInfo();
        CmdRto rto = ip_access.queryLine( );
        ip_access.delAllLines(rto.getMessage().toString());
        ip_access.queryInfo();

    }

    public CmdRto addTcp(){
        int port1;
        if(port == 0) {
            port1 = 80;
        }else{
            port1 = port;
        }
        return addTcp(ip,port1);
    }

    public CmdRto addUdp(){
        int port1;
        if(port == 0) {
            port1 = 80;
        }else{
            port1 = port;
        }
        return addUdp(ip, port1);
    }

    public CmdRto queryInfo(){
        return queryInfo(ip);
    }

    public CmdRto queryInfoBr(){
        return queryInfoBr(ip);
    }

    public CmdRto queryLine(){
        return queryLine(ip);
    }

    public CmdRto delAllLines(){
        CmdRto rto = queryLine(ip);
        CmdRto returnRto = new CmdRto();
        if(Contant.success.getStr().equals(rto.getResult())){
            returnRto = delAllLines(rto.getMessage().toString());
        }
        return returnRto;
    }

    /**
     * 新建一个数据链，将所有程序自定义的ip规则放到一起
     iptables -t filter -N JAVA #新建一个自定义链集中管理
     iptables -t filter -L JAVA  #查询fiter表中是否存在JAVA链 ？能否一条命令直接判断是否存在返回0 or 1
   **/

   /**  假设条件： 远程ip：8.8.8.8    本机带开放端口9999
     /add
     iptables -t filter -A JAVA-p tcp -s 8.8.8.8 --dport 9999 -j ACCEPT
   **/

    private CmdRto addTcp(String ip,int port){
        String cmd = String.format("iptables -t filter -A %s -p tcp -s %s --dport %d -j ACCEPT", chain, ip, port);
        String[] cmds = {"/bin/bash","-c",cmd};
        System.out.println("Command is " + cmd);
        CmdRto cmdRto = exec.exec(cmds);
        System.out.println(cmdRto);
        return cmdRto;
    }

    private CmdRto addUdp(String ip,int port){
        String cmd = String.format("iptables -t filter -A %s -p udp -s %s --dport %d -j ACCEPT", chain, ip, port);
        String[] cmds = {"/bin/bash","-c",cmd};
        System.out.println("Command is " + cmd);
        CmdRto cmdRto = exec.exec(cmds);
        System.out.println(cmdRto);
        return cmdRto;
    }

    /**
     /query
     iptables -t filter -nvL JAVA --line-numbers | grep -c 8.8.8.8   #返回ip创建规则的条数
     iptables -t filter -nvL JAVA --line-numbers | grep 8.8.8.8 | awk  {print $1}  # 返回ip对应规则的序号，可以多条
    **/
    private CmdRto queryInfo(String ip){
        String cmd = String.format("iptables -t filter -nvL %s --line-numbers | grep %s ",chain , ip);
        String[] cmds = {"/bin/bash","-c",cmd};
        System.out.println("Command is " + cmd);
        CmdRto cmdRto = exec.execln(cmds);
        System.out.println(cmdRto);
        return cmdRto;
    }

    private CmdRto queryInfoBr(String ip){
        String cmd = String.format("iptables -t filter -nvL %s --line-numbers | grep %s ",chain , ip);
        String[] cmds = {"/bin/bash","-c",cmd};
        System.out.println("Command is " + cmd);
        CmdRto cmdRto = exec.exec(cmds,null,null, "<br>");
        System.out.println(cmdRto);
        return cmdRto;
    }

    private CmdRto queryLine(String ip){
        String cmd = String.format("iptables -t filter -nvL %s --line-numbers | grep %s | awk '{print $1}' ",chain , ip);
        String[] cmds = {"/bin/bash","-c",cmd};
        System.out.println("Command is " + cmd);
        CmdRto cmdRto = exec.execln(cmds);
        System.out.println(cmdRto);
        return cmdRto;
    }

    /**
     /del
     iptables -t filter -D JAVA 12   #删除filter表JAVA链中序号为12的规则
     iptables -t filter -F JAVA  #清空filter表中JAVA链全部规则
     * */
    private CmdRto del(String line){
        String cmd = String.format("iptables -t filter -D %s %s",chain , line);
        System.out.println("Command is " + cmd);
        String[] cmds = {"/bin/bash","-c",cmd};
        CmdRto cmdRto = exec.execln(cmds);
        System.out.println(cmdRto);
        return cmdRto;
    }

    private CmdRto delAllLines(String line){
        CmdRto returnRto = new CmdRto();
        CmdRto cmdRto = new CmdRto();
        if(line != null && line.length() > 0){
            String[] lines = line.split("\n");
            for(int i = lines.length ; i > 0 ; i-- ){
                cmdRto = del(lines[i-1]);
                System.out.println("delAllLines result is " + cmdRto);
            }
        }else {
            returnRto.setResult(Contant.error.getStr());
            returnRto.setMessage(new StringBuffer("your input line is null"));
            return returnRto;
        }

        returnRto = cmdRto;

        return returnRto;

    }

}
