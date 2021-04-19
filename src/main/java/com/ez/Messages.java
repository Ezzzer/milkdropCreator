package com.ez;

import org.w3c.dom.css.RGBColor;

import java.awt.*;

public class Messages {
    private String firstMsg = "";
    private String secondMsg = "";
    private String font = "";
    private String size = "";
    private String r = "";
    private String g = "";
    private String b = "";
    private String time = "";
    private String fade = "";
    private String growth = "";
    private String randx = "";
    private String randy = "";
    private String randr = "";
    private String randg = "";
    private String randb = "";

    public Messages() {
    }

    public String replaceParams(String a) {
        a = a.replace("{1}", firstMsg);
        a = a.replace("{3}", font);
        a = a.replace("{4}", size);
        a = a.replace("{5}", r);
        a = a.replace("{6}", g);
        a = a.replace("{7}", b);
        a = a.replace("{8}", time);
        a = a.replace("{9}", fade);
        a = a.replace("{10}", growth);
        a = a.replace("{11}", randx);
        a = a.replace("{12}", randy);
        a = a.replace("{13}", randr);
        a = a.replace("{14}", randg);
        a = a.replace("{15}", randb);

        return a.replace("{2}", secondMsg);
    }

}
