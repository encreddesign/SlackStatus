/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus.events;

import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import slackstatus.SlackHttpInterface;
import slackstatus.SlackStatus;
import slackstatus.views.SlackAbstractView;
import slackstatus.views.SlackLoadingView;
import slackstatus.views.SlackSignedInView;
import slackstatus.views.SlackView;

/**
 *
 * @author Joshua
 */
public class SignedInCallback implements SlackHttpInterface {
    
    private SlackView mSlackView;
    private SlackLoadingView mSlackLoadingView;
    private SlackSignedInView mSlackSignedInView;
    
    @Override
    public void PreResponse (SlackAbstractView view) {
        
        this.mSlackLoadingView = new SlackLoadingView();
        this.mSlackLoadingView.setAlignment(Pos.CENTER);
        this.mSlackLoadingView.setVgap(10);
        this.mSlackLoadingView.setHgap(10);
        this.mSlackLoadingView.setPadding(new Insets(25, 25, 25, 25));
        
        ((Stage) view.getScene().getWindow()).setScene(
                new Scene(this.mSlackLoadingView, SlackStatus.APP_WIDTH, SlackStatus.APP_HEIGHT));
        
    }

    @Override
    public void OnResponse(SlackAbstractView view, HashMap<String, String> response) {
        
        if(response != null) {
            
            if(response.get("team_id") != null) {
                this.loadSignedInView(this.mSlackLoadingView, response);
            } else {
                this.loadSlackView(this.mSlackLoadingView);
            }
            
        }
        
    }
    
    void loadSlackView (SlackAbstractView view) {
        
        this.mSlackView = new SlackView();
        this.mSlackView.setAlignment(Pos.CENTER);
        this.mSlackView.setVgap(10);
        this.mSlackView.setHgap(10);
        this.mSlackView.setPadding(new Insets(25, 25, 25, 25));
        
        ((Stage) view.getScene().getWindow()).setScene(
                new Scene(this.mSlackView, SlackStatus.APP_WIDTH, SlackStatus.APP_HEIGHT));
        
    }
    
    void loadSignedInView (SlackAbstractView view, HashMap response) {
        
        this.mSlackSignedInView = new SlackSignedInView(response);
        this.mSlackSignedInView.setAlignment(Pos.CENTER);
        this.mSlackSignedInView.setVgap(10);
        this.mSlackSignedInView.setHgap(10);
        this.mSlackSignedInView.setPadding(new Insets(25, 25, 25, 25));
        
        ((Stage) view.getScene().getWindow()).setScene(
                new Scene(this.mSlackSignedInView, SlackStatus.APP_WIDTH, SlackStatus.APP_HEIGHT));
        
    }
    
}
