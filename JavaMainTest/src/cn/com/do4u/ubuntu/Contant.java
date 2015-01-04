package cn.com.do4u.ubuntu;

/**
 * Created by root on 14-10-18.
 */
enum Contant {

    success("success"),
    error("error"),
    sign("|**##^^sign^^##**|");

    private String str;
    Contant(String str){
        this.str = str;
    }

    String getStr(){
        return str;
    }



}
