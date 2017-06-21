/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus.events;

import java.util.prefs.Preferences;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.paint.Color;
import slackstatus.SlackStatus;
import slackstatus.views.SlackSignedInView;

/**
 *
 * @author joshuagrierson
 */
public class SaveUpdatesHandler implements EventHandler {
    
    private final SlackSignedInView mSlackSignedInView;
    
    private Preferences mSlackPrefs;
    
    public SaveUpdatesHandler (SlackSignedInView view) {
        this.mSlackSignedInView = view;
    }

    @Override
    public void handle(Event event) {
        
        final ListView<String> daysSelector = this.mSlackSignedInView.mSlackDayChooser;
        
        this.storeDaysInPrefs(this.getDaysAsString(daysSelector.getSelectionModel()));
        
    }
    
    private String getDaysAsString (MultipleSelectionModel model) {
        
        String daysString = "";
        final ObservableList list = model.getSelectedItems();
        final ObservableList<Integer> indices = model.getSelectedIndices();
        
        for(int i = 0; i < list.size(); i++) {
            
            daysString += (list.get(i) + ":" + String.valueOf(indices.get(i)) + ",");
            
        }
        
        return daysString.substring(0, (daysString.length() - 1));
        
    }
    
    void storeDaysInPrefs (String days) {
        
        try {
            
            this.mSlackPrefs = Preferences.userRoot().node(SlackStatus.APP_PREFS_NAME);
            
            this.mSlackPrefs.put(SlackStatus.APP_PREFS_DAYS_KEY, days);
            
            System.out.println("Saved User Preferences");
            this.mSlackSignedInView.mSlackErrorLabel.setText("Saved");
            this.mSlackSignedInView.mSlackErrorLabel.setTextFill(Color.web("#34af82"));
            
        } catch (SecurityException ex) {
            
            this.mSlackSignedInView.mSlackErrorLabel.setText(ex.getMessage());
            System.err.println("Security Error: " + ex.getMessage());
            
        }
        
    }
    
}
