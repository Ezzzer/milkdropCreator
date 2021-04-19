package com.ez;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImgIniTemplate {
    String template;
    Animation animation;
    public ImgIniTemplate(){
        try {
            animation = readAnimationFile();// create Animation Instance
            template = Files.readString(Path.of("milk2_img.ini.template"), StandardCharsets.US_ASCII);
            setImagesSection(animation.createAnimations());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Animation readAnimationFile() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        try(Reader reader = Files.newBufferedReader(Paths.get("animationTemplate.json"))) {

            return gson.fromJson(reader, Animation.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void setImagesSection(String section) {
        template = template.replace("/*ImagesSection*/",section);
    }

    public void writeTemplate(){
        try {
            Files.write(Path.of("test.ini"),  template.getBytes());
            Files.write(Path.of("C:\\Users\\ezer\\AppData\\Roaming\\Winamp\\Plugins\\Milkdrop2\\milk2_img.ini"),  template.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
