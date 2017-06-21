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
import slackstatus.SlackUtils;
import slackstatus.events.ResetApp;
import slackstatus.events.SaveUpdatesHandler;

/**
 *
 * @author Joshua
 */
public class SlackSignedInView extends SlackAbstractView {
    
    private final Text mSlackWelcomeMessage;
    
    private final Text mSlackStatusText;
    
    public final Label mSlackErrorLabel;
    private final Label mSlackDayLabel;
    
    public final ListView<String> mSlackDayChooser;
    
    private final Button mSlackSubmitButton;
    private final Button mSlackResetButton;
    
    private Preferences mSlackPrefs;
    
    private final HashMap<String, String> mSlackResponse;
    
    public SlackSignedInView (HashMap<String, String> response) {
        
        this.mSlackStatusText = new Text();
        this.mSlackWelcomeMessage = new Text();
        
        this.mSlackErrorLabel = new Label();
        this.mSlackDayLabel = new Label("Choose Days");
        
        this.mSlackDayChooser = new ListView<String>();
        this.mSlackSubmitButton = new Button("Save Changes");
        this.mSlackResetButton = new Button("Reset");
        
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
                
                if(this.mSlackResponse.get("access_token") != null){
                    this.storeTokenInPrefs(this.mSlackResponse.get("access_token"));
                }
                
            }
            
        }
        
        this.mSlackDayChooser.setPrefWidth(100);
        this.mSlackDayChooser.setPrefHeight(200);
        this.mSlackDayChooser.setItems(this.fillDaysArray());
        this.mSlackDayChooser.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.applyListSelectionItems();
        
        this.mSlackSubmitButton.setOnAction(new SaveUpdatesHandler(this));
        this.mSlackResetButton.setOnAction(new ResetApp(this));
        
        this.add(this.mSlackWelcomeMessage, 0, 0);
        this.add(this.mSlackStatusText, 0, 2);
        this.add(this.mSlackDayLabel, 0, 3);
        this.add(this.mSlackDayChooser, 0, 4);
        
        this.add(this.mSlackSubmitButton, 0, 5);
        this.add(this.mSlackResetButton, 1, 5);
        
        this.add(this.mSlackErrorLabel, 0, 6);
        
    }
    
    void storeTokenInPrefs (String token) {
        
        try {
            
            this.mSlackPrefs = Preferences.userRoot().node(SlackStatus.APP_PREFS_NAME);
            
            this.mSlackPrefs.put(SlackStatus.APP_PREFS_TOKEN_KEY, token);
            
        } catch (Exception ex) {
            
            this.mSlackErrorLabel.setText(ex.getMessage());
            System.err.println("Security Error: " + ex.getMessage());
            ex.printStackTrace();
            
        }
        
    }
    
    void applyListSelectionItems () {
        
        try {
            
            this.mSlackPrefs = Preferences.userRoot().node(SlackStatus.APP_PREFS_NAME);
            
            if(this.mSlackPrefs.get(SlackStatus.APP_PREFS_DAYS_KEY, null) != null) {
                
                final String[] days = SlackUtils.getArray(this.mSlackPrefs.get(SlackStatus.APP_PREFS_DAYS_KEY, null));
                for(int i = 0; i < days.length; i++) {
                    
                    final String day = days[i].split(":")[0];
                    final int idx = Integer.valueOf(days[i].split(":")[1]);
                    
                    this.mSlackDayChooser.getSelectionModel().select(idx);
                    
                }
                
            }
            
        } catch (Exception ex) {
            
            this.mSlackErrorLabel.setText(ex.getMessage());
            System.err.println("Security Error: " + ex.getMessage());
            ex.printStackTrace();
            
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
    
}
