package org.groupTw;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSON {
    private PrintWriter pw;
    private String file;
    private JSONObject jo;

    public JSON(String file){
        this.file =file;

        jo = new JSONObject();
    }

    public void JSONWriteToFile() {

        try {
            pw = new PrintWriter(file) ;
        } catch (FileNotFoundException e) {
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
            obj = new JSONParser().parse(new FileReader(file));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;

        String v = (String) jo.get(value);
        System.out.println(v);

        return v;

    }
}
