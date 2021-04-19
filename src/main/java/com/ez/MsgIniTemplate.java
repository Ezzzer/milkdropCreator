package com.ez;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MsgIniTemplate {
    String template;
    Messages messages;
    public MsgIniTemplate(){
        try {
            messages = readMessagesFile();// create Animation Instance
            template = Files.readString(Path.of("milk2_msg.ini.template"), StandardCharsets.US_ASCII);
            template = messages.replaceParams(template);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Messages readMessagesFile() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        try(Reader reader = Files.newBufferedReader(Paths.get("msgTemplate.json"))) {

            return gson.fromJson(reader, Messages.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeTemplate(){
        try {
            Files.write(Path.of("C:\\Users\\ezer\\AppData\\Roaming\\Winamp\\Plugins\\Milkdrop2\\milk2_msg.ini"),  template.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
