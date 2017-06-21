/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus.views;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author joshuagrierson
 */
public class SlackLoadingView extends SlackAbstractView {
    
    private final Text mSlackLoadingText;
    
    public SlackLoadingView () {
        
        this.mSlackLoadingText = new Text("Loading...");
        
        this.attachComponents();
        
    }
    
    void attachComponents () {
        
        this.mSlackLoadingText.setFont(Font.font("Helvetica", FontWeight.NORMAL, 15));
        this.add(this.mSlackLoadingText, 0, 0);
        
    }
    
}
