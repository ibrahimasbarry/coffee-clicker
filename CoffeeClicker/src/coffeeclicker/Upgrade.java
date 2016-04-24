/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeclicker;

import javafx.scene.control.Alert;

/**
 *
 * @author Kynan
 */
public class Upgrade extends Achievement implements Achievable {
    
    private String upgradeMessage;
    
    public Upgrade() {
        System.out.println("New upgrade created!");
    }
    
    public String getUpgradeMessage() {
        return upgradeMessage;
    }
    
    public void setUpgradeMessage(String newMessage) {
        this.upgradeMessage = newMessage;
    }
    
    @Override
    public void showAchievement(String achievementName) {
        Alert achievementAlert = new Alert(Alert.AlertType.INFORMATION);
        achievementAlert.setTitle("Achievement");
        achievementAlert.setHeaderText("Achievement Unlocked: " + achievementName);
        achievementAlert.setContentText("Now you can make coffee much faster!");
        achievementAlert.showAndWait();
    }
}
