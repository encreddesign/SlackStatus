/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus;

import java.util.ArrayList;

/**
 *
 * @author Joshua
 */
public class SlackUtils {
    
    public static String joinString (ArrayList<String> array, String split) {
        
        String joined = "";
        
        for(String value : array) {
            
            if(split != null) {
                joined += (value + split);
            } else {
                joined += (value + ",");
            }
        
        }
        
        return joined.substring(0, (joined.length() - 1));
        
    }
    
    public static String[] getArray (String stringed) {
        return stringed.split(",");
    }
    
}
