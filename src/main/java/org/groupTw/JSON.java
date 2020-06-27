package org.groupTw;
import java.io.FileReader;
import java.io.PrintWriter;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSON {
    public static void JSONWriteToFile() throws Exception{
        JSONObject jo = new JSONObject();

        jo.put("Name","Maciej");
        jo.put("Surname","Kot");
        jo.put("Age",25);


        PrintWriter pw = new PrintWriter("JSONExample.json");
        pw.write(jo.toJSONString());
        System.out.println("Saved in File");
        pw.flush();
        pw.close();
    }
    public static void JSONReadFromFile() throws Exception{
        Object obj = new JSONParser().parse(new FileReader("JSONExample.json"));

        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;

        String Name = (String) jo.get("Name");
        System.out.println(Name);

        String Surname = (String) jo.get("Surname");
        System.out.println(Surname);

        long Age = (long) jo.get("Age");
        System.out.println(Age);

    }
}
