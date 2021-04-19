package com.ez;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Animation {
    public static final int BASE_NUMBER = 60;
    int numberOfImages = 1;
    private String imgFileLocation = "";
    private String sound = "bass";
    String blendMode = "0";
    String initialX = ".5";
    String initialY = ".5";
    String initialSX = ".5";
    String initialSY = ".5";

    String frameSpeed = "frame";
    String x = ".5";
    String y = ".5";
    String sx = ".5";
    String sy = ".5";
    String rot = "0";
    String scale = "0";
    String a = "1";
    String burn = "1";


    public Animation() {
    }

    /*
    [img31]
    img=D:\MakingMusic\Automatic Music Machine\Images\anim1.png
    init_1=blendmode = 0;
    init_2=sx=0.33;
    init_3=sy=0.33;
    init_4=x=0.5;
    init_5=y=0.5;

    code_1=sound = max(max(bass_att*0.9,mid_att*0.75),treb_att*0.65)*(bass_att*0.5+mid_att*0.25+treb_att*0.25)+0.1;
    code_2=rot =0;
    code_3=currentFrame1 = frame*0.04;
    code_4=sx=0.33;
    code_5=sy=0.33;
    code_6=a = 0.5*sound*above(sound,0.1)* ( equal(currentFrame1%24,1) || equal(currentFrame1%24,2) );
    code_7=x=0.5;
    code_8=y=0.5;
    code_11=burn=a;
     */
     ;


    public String createImgString(String textFromFile,int  imgNumber) {

        String[] unformatted = textFromFile.split("\n");

        StringBuilder foramtted = new StringBuilder();
        int lineIndex = 1;
        String  linePrefix = "img";
        for (String s : unformatted) {
            switch(s){ // change compiler state
                case "img":
                    linePrefix = "img";
                    lineIndex = 1;
                    foramtted.append("\n").append("[img").append(imgNumber+BASE_NUMBER).append("]\n");
                    continue;
                case "init":
                    linePrefix = "init_";
                    lineIndex = 1;
                    foramtted.append("\n");

                    continue;
                case "code":
                    linePrefix = "code_";
                    lineIndex = 1;
                    foramtted.append("\n");
                    continue;
            }
            switch(linePrefix) { // write the line
                case "img":
                    foramtted.append("img=").append(replaceParams(s, imgNumber)).append("\n");
                    break;
                default:
                    foramtted.append(linePrefix).append(lineIndex++).append("=").append(replaceParams(s, imgNumber)).append("\n");
            }
        }
        return foramtted.toString();
    }

    private String replaceParams(String a, int imgNumber) {
        a = a.replace("{imgFileLocation}", this.imgFileLocation);
        a = a.replace("{initialX}", initialX);
        a = a.replace("{initialY}", initialY);
        a = a.replace("{initialSX}", initialSX);
        a = a.replace("{initialSY}", initialSY);
        a = a.replace("{initialX}", initialX);
        a = a.replace("{frameSpeed}", frameSpeed);
        a = a.replace("{burn}", this.burn);
        a = a.replace("{x}", this.x);
        a = a.replace("{a}", this.a);
        a = a.replace("{y}", this.y);
        a = a.replace("{sx}", this.sx);
        a = a.replace("{sy}", this.sy);
        a = a.replace("{burn}", this.burn);
        a = a.replace("{rot}", this.rot);
        a = a.replace("{scale}", this.scale);
        a = a.replace("{sound}", this.sound);
        a = a.replace("{blendMode}", this.blendMode);
        a = a.replace("{numberOfImages}", Integer.toString(this.numberOfImages));
        a = a.replace("{1}", Integer.toString(imgNumber));
        return a.replace("{2}", Integer.toString(imgNumber + BASE_NUMBER));
    }

    String createAnimations() {
        String textFromFile = "";
        String initFromFile = "";
        try {
            textFromFile = Files.readString(Path.of("imageCode.txt"), StandardCharsets.US_ASCII);
            initFromFile = Files.readString(Path.of("initImageCode.txt"), StandardCharsets.US_ASCII);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder imagesSection = new StringBuilder("/*Animations*/\n");

        imagesSection.append(createImgString(initFromFile,0));

        for (int i = 1; i <= numberOfImages; i++) {
            imagesSection.append(createImgString(textFromFile,i));
        }
        return imagesSection.toString();
    }
}
