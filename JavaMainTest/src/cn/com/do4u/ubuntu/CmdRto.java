package cn.com.do4u.ubuntu;

/**
 * Created by root on 14-10-18.
 */
public class CmdRto {
    private String result ;
    private StringBuffer message ;
    private StringBuffer messageln ;
    private String[] array;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public StringBuffer getMessage() {
        return message;
    }

    public void setMessage(StringBuffer message) {
        this.message = message;
    }

    public String[] getArray(){
       return new String(message).split(Contant.sign.getStr());
    }

    @Override
    public String toString() {
        return getResult()+"\n"+getMessage();
    }
}
