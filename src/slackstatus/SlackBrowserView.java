/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus;

import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author joshuagrierson
 */
public class SlackBrowserView extends Region {
    
    private final WebView mWebView;
    private final WebEngine mWebEngine;
    
    public SlackBrowserView () {
    
        this.mWebView = new WebView();
        this.mWebEngine = this.mWebView.getEngine();
        
        handleWebEngine();
        getChildren().add(this.mWebView);
    
    }
    
    protected void handleWebEngine () {
        
        String buildUrl = SlackQueryBuilder
                .newInstance(SlackStatus.SLACK_AUTH_URL)
                .addParam("client_id", SlackStatus.SLACK_CLIENT_ID)
                .addParam("scope", SlackStatus.SLACK_APP_SCOPE).getUrl();
        
        this.mWebEngine.load(SlackStatus.SLACK_AUTH_URL);
        
    }
    
}
