/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus;

/**
 *
 * @author joshuagrierson
 */
public class SlackStatus {
    
    public final static int APP_WIDTH = 500;
    public final static int APP_HEIGHT = 500;
    public final static String APP_TITLE = "Slack Status v1.0";
    
    public final static String SLACK_AUTH_URL = "https://slack.com/oauth/authorize";
    public final static String SLACK_TOKEN_URL = "https://slack.com/api/oauth.access";
    public final static String SLACK_AUTH_TEST = "https://slack.com/api/auth.test";
    
    public final static String SLACK_CLIENT_ID = "198518280176.198627167713";
    public final static String SLACK_CLIENT_SECRET = "3e645862aaa11c9a128b36fcb4906391";
    public final static String SLACK_APP_SCOPE = "users:write+identify";
    
    public final static String APP_PREFS_NAME = "slackstatus.app.encreddesign";
    public final static String APP_PREFS_TOKEN_KEY = "slack_token_key";

    public static void main(String[] args) {
        new SlackApplication().launchApp(args);
    }
    
}
