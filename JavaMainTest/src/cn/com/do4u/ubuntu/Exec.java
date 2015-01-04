package cn.com.do4u.ubuntu;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * Description:
 * Copyright: Copyright (c) 2013
 * Company: do4u.com.cn
 *
 * @author ChenYu
 * @Date 14-5-21 下午12:59
 */
public class Exec {

    /**
     * @param cmdStr
     * @return
     * @throws Exception
     */

    public CmdRto exec( String[] cmdStr, File dir) {
        return exec(cmdStr,null,dir,"");
    }
    public CmdRto exec( String[] cmdStr )  {
        return exec(cmdStr,null,null, "");
    }

    public CmdRto execln( String[] cmdStr, File dir) {
        return exec(cmdStr,null,dir,"\n");
    }
    public CmdRto execln( String[] cmdStr )  {
        return exec(cmdStr,null,null, "\n");
    }

    public CmdRto execSign( String[] cmdStr, File dir) {
        return exec(cmdStr,null,dir,Contant.sign.getStr());
    }
    public CmdRto execSign( String[] cmdStr )  {
        return exec(cmdStr,null,null,Contant.sign.getStr());
    }


    public CmdRto exec(String[] cmdArr,
                       String[] envP,
                       File dir,String sign) {
        StringBuffer str = new StringBuffer();
        CmdRto rto = new CmdRto();
        int flag = 0;

        Runtime r = Runtime.getRuntime();
        System.out.println("cmd start");

        Process p = null;
        try {
            p = r.exec(cmdArr, envP, dir);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));


            String readerStr;
            while ((readerStr = reader.readLine()) != null) {
                str.append(readerStr+sign);
            }

            String errorStr;
            while ((errorStr = error.readLine()) != null) {
                flag = -1;
                str.append(errorStr+sign);
            }
        } catch (Exception e) {
            rto.setResult(Contant.error.getStr());
            rto.setMessage(new StringBuffer(e.getMessage()));
            return rto;
        }
        if (flag == 0) {
            rto.setResult(Contant.success.getStr());
        } else {
            rto.setResult(Contant.error.getStr());
        }
        rto.setMessage(str);

        if (p != null)
            p.destroy();

        p = null;

        System.out.println("cmd end");
        return rto;
    }



}
