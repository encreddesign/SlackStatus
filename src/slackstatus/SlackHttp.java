/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Joshua
 */
public class SlackHttp {
    
    private final SlackQueryBuilder mUrl;
    
    private String mMethod;
    
    private HttpsURLConnection mHttpsConnection;
    
    private String mHttpResponse;
    
    private SlackHttp (SlackQueryBuilder url) {
        
        this.mUrl = url;
        this.mMethod = "GET";
        
    }
    
    public SlackHttp get () {
        
        this.mMethod = "GET";
        
        return this;
        
    }
    
    public SlackHttp post () {
        
        this.mMethod = "POST";
        
        return this;
        
    }
    
    public SlackHttp open () {
        
        try {
            
            final URL urlObj = new URL(
                    (this.mMethod == "GET" ? this.mUrl.getUrl() : this.mUrl.getBaseUrl())
            );
            this.mHttpsConnection = (HttpsURLConnection) urlObj.openConnection();
            
            this.mHttpsConnection.setRequestMethod(this.mMethod);
            
            if(this.mMethod == "POST") {
                
                this.mHttpsConnection.setDoOutput(true);
                this.writePostData();
                
            }
            
            int code = this.mHttpsConnection.getResponseCode();
            if(code >= 400) {
                throw new Exception("Request failed - " + code);
            }
            
            this.mHttpResponse = this.getStringedResponse();
            
        } catch (Exception ex) {
            
            this.mHttpResponse = ex.getMessage();
            System.err.println("HttpError: " + ex.getMessage());
            
        }
        
        return this;
        
    }
    
    public String getResponse () {
        return this.mHttpResponse;
    }
    
    private String getStringedResponse () throws Exception {
        
        String line = "";
        StringBuffer stringed = new StringBuffer();
        
        final BufferedReader buffered = new BufferedReader(
                new InputStreamReader(this.mHttpsConnection.getInputStream())
        );
        
        do {
            stringed.append(line);
        } while((line = buffered.readLine()) != null);
        
        buffered.close();
        
        return stringed.toString();
        
    }
    
    private void writePostData () throws Exception {
        
        final DataOutputStream output = new DataOutputStream(this.mHttpsConnection.getOutputStream());
        
        output.writeBytes(this.mUrl.getParams());
        output.flush();
        output.close();
        
    }
    
    public static SlackHttp newInstance (SlackQueryBuilder url) {
        return new SlackHttp(url);
    }
    
}
