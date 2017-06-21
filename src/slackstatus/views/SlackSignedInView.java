/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus.views;

import java.util.HashMap;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import slackstatus.SlackStatus;
import slackstatus.events.SaveUpdatesHandler;

/**
 *
 * @author Joshua
 */
public class SlackSignedInView extends SlackAbstractView {
    
    private final Text mSlackWelcomeMessage;
    
    private final Text mSlackStatusText;
    
    private final Label mSlackErrorLabel;
    private final Label mSlackDayLabel;
    
    private final ListView<String> mSlackDayChooser;
    
    private final Button mSlackSubmitButton;
    
    private Preferences mSlackPrefs;
    
    private final HashMap<String, String> mSlackResponse;
    
    public SlackSignedInView (HashMap<String, String> response) {
        
        this.mSlackStatusText = new Text();
        this.mSlackWelcomeMessage = new Text();
        
        this.mSlackErrorLabel = new Label();
        this.mSlackDayLabel = new Label("Choose Days");
        
        this.mSlackDayChooser = new ListView<String>();
        this.mSlackSubmitButton = new Button("Save Changes");
        
        this.mSlackResponse = response;
        
        this.attachComponents();
        
    }
    
    void attachComponents () {
        
        this.mSlackStatusText.setFont(Font.font("Helvetica", FontWeight.NORMAL, 15));
        this.mSlackWelcomeMessage.setFont(Font.font("Helvetica", FontWeight.NORMAL, 25));
        
        this.mSlackErrorLabel.setTextFill(Color.web("#f4424b"));
        
        if(this.mSlackResponse != null) {
            
            if(this.mSlackResponse.get("team") != null) {
                this.mSlackWelcomeMessage.setText("Team: " + this.mSlackResponse.get("team"));
            } else if(this.mSlackResponse.get("team_name") != null) {
                this.mSlackWelcomeMessage.setText("Team: " + this.mSlackResponse.get("team_name"));
            }
            
            if(this.mSlackResponse.get("team_id") != null) {
                
                this.mSlackStatusText.setFill(Color.web("#34af82"));
                this.mSlackStatusText.setText(this.mSlackResponse.get("team_id"));
                
                this.storeTokenInPrefs(this.mSlackResponse.get("access_token"));
                
            }
            
        }
        
        this.mSlackDayChooser.setPrefWidth(100);
        this.mSlackDayChooser.setPrefHeight(200);
        this.mSlackDayChooser.setItems(this.fillDaysArray());
        this.mSlackDayChooser.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        this.mSlackSubmitButton.setOnAction(new SaveUpdatesHandler(this));
        
        this.add(this.mSlackWelcomeMessage, 0, 0);
        this.add(this.mSlackStatusText, 0, 2);
        this.add(this.mSlackDayLabel, 0, 3);
        this.add(this.mSlackDayChooser, 0, 4);
        this.add(this.mSlackSubmitButton, 0, 5);
        this.add(this.mSlackErrorLabel, 0, 6);
        
    }
    
    void storeTokenInPrefs (String token) {
        
        try {
            
            this.mSlackPrefs = Preferences.userRoot().node(SlackStatus.APP_PREFS_NAME);
            
            if(this.mSlackPrefs.get(SlackStatus.APP_PREFS_TOKEN_KEY, null) == null) {
                this.mSlackPrefs.put(SlackStatus.APP_PREFS_TOKEN_KEY, token);
            }
            
        } catch (SecurityException ex) {
            
            this.mSlackErrorLabel.setText(ex.getMessage());
            System.err.println("Security Error: " + ex.getMessage());
            
        }
        
    }
    
    private ObservableList fillDaysArray () {
        
        return FXCollections.observableArrayList(
                
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"
                
        );
        
    }
    
    void submitUpdate () {}
    
}
