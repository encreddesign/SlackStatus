/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus.views;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import slackstatus.events.AuthenticateUser;
import slackstatus.events.CollectAuthCode;

/**
 *
 * @author Joshua
 */
public class SlackView extends SlackAbstractView {
    
    public final TextField mSlackCodeField;
    
    public final Label mSlackCodeFieldLabel;
    public final Label mSlackErrorLabel;
    
    private final Hyperlink mSlackInfoLabel;
    
    public final Button mSlackCommitButton;
    
    private final Text mSlackPageTitle;
    
    public SlackView () {
        
        this.mSlackCodeField = new TextField();
        this.mSlackCommitButton = new Button("Add User");
        
        this.mSlackErrorLabel = new Label();
        this.mSlackCodeFieldLabel = new Label("Auth Code");
        
        this.mSlackInfoLabel = new Hyperlink();
        
        this.mSlackPageTitle = new Text("Link User");
        
        this.attachComponents();
        
    }
    
    void attachComponents () {
        
        this.mSlackPageTitle.setFont(Font.font("Helvetica", FontWeight.NORMAL, 20));
        
        this.mSlackInfoLabel.setText("I need an Access Code");
        this.mSlackInfoLabel.setOnAction(new CollectAuthCode());
        
        this.mSlackErrorLabel.setWrapText(true);
        this.mSlackErrorLabel.setTextFill(Color.web("#f4424b"));
        
        this.mSlackCommitButton.setOnAction(new AuthenticateUser(this));
        
        this.add(this.mSlackPageTitle, 0, 0);
        this.add(this.mSlackCodeField, 0, 3);
        this.add(this.mSlackCommitButton, 0, 4);
        
        this.add(this.mSlackCodeFieldLabel, 0, 2);
        this.add(this.mSlackInfoLabel, 0, 6);
        this.add(this.mSlackErrorLabel, 0, 7);
        
    }
    
}
