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
import slackstatus.views.SlackSignedInView;

/**
 *
 * @author Joshua
 */
public class SignedInCallback implements SlackHttpInterface {
    
    private SlackSignedInView mSlackSignedInView;

    @Override
    public void OnResponse(SlackAbstractView view, HashMap<String, String> response) {
        
        if(response != null) {
            
            if(response.get("error") != null) {
                this.loadSignedInView(view, response);
            }
            
        }
        
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