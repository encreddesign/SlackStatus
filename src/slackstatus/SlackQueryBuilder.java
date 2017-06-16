/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus;

import java.util.ArrayList;

/**
 *
 * @author joshuagrierson
 */
public class SlackQueryBuilder {
    
    private String mBaseUrl;
   
    private final ArrayList<String> mQueryParams;
    
    private SlackQueryBuilder (String base_url) {
        
        this.mBaseUrl = base_url;
        this.mQueryParams = new ArrayList();
        
    }
    
    public SlackQueryBuilder addParam (String name, String value) {
        
        this.mQueryParams.add((name + "=" + value));
        return this;
        
    }
    
    public String getUrl () {
        return (this.mBaseUrl + "?" + this.joinArrayList());
    }
    
    private String joinArrayList () {
        
        String joined = "";
        
        for(int i = 0; i < this.mQueryParams.size(); i++) {
            
            joined += (this.mQueryParams.get(i) + "&");
            
        }
        
        return joined.substring(0, (joined.length() - 1));
        
    }
    
    public static SlackQueryBuilder newInstance (String base_url) {
        return new SlackQueryBuilder(base_url);
    }
    
}
