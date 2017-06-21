/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus;

import javafx.application.Platform;
import slackstatus.views.SlackAbstractView;

/**
 *
 * @author Joshua
 */
public class SlackHttpThread extends Thread {
    
    private final SlackAbstractView mSlackAbstractView;
    
    private SlackQueryBuilder mSlackQueryBuilder;
    private SlackHttpInterface mSlackHttpInterface;
    
    public SlackHttpThread (SlackAbstractView view) {
        this.mSlackAbstractView = view;
    }
    
    public void setUrl (String url) {
        this.mSlackQueryBuilder = SlackQueryBuilder.newInstance(url);
    }

    public void addParam (String name, String value) {
        this.mSlackQueryBuilder.addParam(name, value);
    }
    
    public void setCallback (SlackHttpInterface httpInterface) {
        this.mSlackHttpInterface = httpInterface;
    }
    
    @Override
    public void run() {
        
        Platform.runLater(new Runnable(){

            @Override
            public void run() {
                mSlackHttpInterface.PreResponse(mSlackAbstractView);
            }
            
        });
        
        final String response = SlackHttp.newInstance(this.mSlackQueryBuilder.getUrl())
                .open().getResponse();
        
        if(response != null) {

            Platform.runLater(new Runnable(){

                @Override
                public void run() {
                    mSlackHttpInterface.OnResponse(mSlackAbstractView, SlackJSONParser.newInstance().getHashMap(response));
                }
                
            });

        }
        
    }
    
}
