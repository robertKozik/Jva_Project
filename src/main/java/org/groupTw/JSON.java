package org.groupTw;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.json.simple.*;

public class JSON {
    private PrintWriter pw;
    private String file;
    private JSONObject jo;

    public JSON(String file){
        this.file = file;

        jo = new JSONObject();
    }

    public void JSONWriteToFile() {

        try {
            URL resourceUrl = getClass().getResource(this.file);
            File outputFile = new File(resourceUrl.toURI());
            pw = new PrintWriter(outputFile) ;
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }


    }
    public void JSONAddValue(String name, String value){

        jo.put(name,value);
    }

    public void JSONCloseWithSave(){
        System.out.println("Saved in File");
        pw.write(jo.toJSONString());
        pw.flush();
        pw.close();
    }

    public String JSONReadFromFile(String value)  {

        Object obj = null;
        try {
            obj = new JSONParser().parse(new InputStreamReader(getClass().getResourceAsStream(file)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;

        String v = (String) jo.get(value);
        //System.out.println(v);

        return v;

    }
}
