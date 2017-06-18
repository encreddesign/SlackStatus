/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus;

import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Joshua
 */
public class SlackJSONParser {
    
    private final JSONParser mParser;

    private SlackJSONParser () {
        this.mParser = new JSONParser();
    }

    public HashMap<String, String> getHashMap ( String json ) {

        try {

            JSONObject jsonObj = (JSONObject) this.mParser.parse(json);
            return jsonObj;

        } catch (ParseException ex) {

            System.err.println("JSON Object error: " + ex.getMessage());
            return null;

        }

    }

    public static SlackJSONParser newInstance () {
        return new SlackJSONParser();
    }

}
