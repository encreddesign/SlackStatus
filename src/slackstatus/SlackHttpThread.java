/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus;

/**
 *
 * @author Joshua
 */
public class SlackHttpThread extends Thread {
    
    private final String mSlackAuthoriseURL;
    
    public SlackHttpThread () {
        
        this.mSlackAuthoriseURL = new String(
                SlackQueryBuilder.newInstance(SlackStatus.SLACK_TOKEN_URL)
                .addParam("client_id", SlackStatus.SLACK_CLIENT_ID)
                .addParam("client_secret", SlackStatus.SLACK_CLIENT_SECRET).getUrl()
        );
        
    }

    @Override
    public void run() {
        
        final String response = SlackHttp.newInstance(this.mSlackAuthoriseURL)
                .open().getResponse();
        
    }
    
}
