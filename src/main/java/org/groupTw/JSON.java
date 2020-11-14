package org.groupTw;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Class handles Json file, it can write and read information and present it to application.
 */
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
            pw = new PrintWriter(outputFile);
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }


    }

    /**
     * Adds new value to Json file.
     *
     * @param key   desired Key String
     * @param value desired Value
     */
    public void JSONAddValue(String key, String value) {

        jo.put(key, value);
    }

    /**
     * Saves all changes to Json file and closes it.
     */
    public void JSONCloseWithSave() {
        System.out.println("Saved in File");
        pw.write(jo.toJSONString());
        pw.flush();
        pw.close();
    }

    /**
     * Method opens Json file, and reads from it value behind given key.
     *
     * @param key Unique String which we want value .
     * @return Value of key.
     */
    public String JSONReadFromFile(String key) {

        Object obj;
        try {
            obj = new JSONParser().parse(new InputStreamReader(getClass().getResourceAsStream(file)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;

        String v = (String) jo.get(key);
        System.out.println(v);

        return v;

    }
}
