/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus;

import java.util.HashMap;
import slackstatus.views.SlackAbstractView;

/**
 *
 * @author Joshua
 */
public interface SlackHttpInterface {
    
    public void OnResponse (SlackAbstractView view, HashMap<String, String> response);
    
}
