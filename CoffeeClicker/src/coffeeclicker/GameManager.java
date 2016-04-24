/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeclicker;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 *
 * @author Kynan
 */
public class GameManager implements Serializable { 
    
    private Integer counter;  
    private Integer currentMultiplier;
    
    private ArrayList<String> upgradeList;
    
    public GameManager() {
        this.counter = 0;
        this.currentMultiplier = 1;
        
        upgradeList = new ArrayList<>();
    }

    public Integer getCounter() {     
        return this.counter;
    }
    
    public void setCounter(Integer score) {
        this.counter += score;
    }
    
    public Integer getCurrentMultiplier() {
        return this.currentMultiplier;
    }
    
    public void setCurrentMultiplier(Integer multiplier) {
        this.currentMultiplier += multiplier;
    }
    
    public Integer getNumCurrentUpgrades() {
        Integer numUpgrades = 0;
        
        for(String current: this.upgradeList) {
            numUpgrades++;
        }
        
        return numUpgrades;
    }
    
    public ArrayList<String> getCurrentUpgradeList() {
        return this.upgradeList;
    }
    
    public void setCurrentUpgradeList(ArrayList list) {
        this.upgradeList = list;
    }
       
    public Integer getUpgradePrice(Label label) {
        return Integer.parseInt(label.getText());
    }
    
    public void addUpgradeToList(String upgradeNum) {
        this.upgradeList.add(upgradeNum);
    }

    public boolean canPurchaseUpgrade(Label label, Integer currentCounter) {
        if (currentCounter >= Integer.parseInt(label.getText())) {
            return true;
        } else {
            return false;
        }
    }
    
    public void purchaseUpgrade(Label scoreLabel, Button button, Label label) {
        String buttonName = button.getText();
        Upgrade upgrade = new Upgrade();
        
        switch(buttonName) {
            case "Upgrade 1":
                this.setCounter(-10);
                this.setCurrentMultiplier(4);
                upgrade.setUpgradeMessage("Baby's First Cup");
                upgrade.showAchievement(upgrade.getUpgradeMessage());
                addUpgradeToList("1");
                printCurrentUpgrades(upgradeList);
                break;
            case "Upgrade 2":
                this.setCounter(-100);
                this.setCurrentMultiplier(10);
                upgrade.setUpgradeMessage("Baby's First Pot");
                upgrade.showAchievement(upgrade.getUpgradeMessage());
                addUpgradeToList("2");
                printCurrentUpgrades(upgradeList);
                break;
            case "Upgrade 3":
                this.setCounter(-1000);
                this.setCurrentMultiplier(100);
                upgrade.setUpgradeMessage("Functional Adult");
                upgrade.showAchievement(upgrade.getUpgradeMessage());
                addUpgradeToList("3");
                printCurrentUpgrades(upgradeList);
                break;
            case "Upgrade 4":
                this.setCounter(-10000);
                this.setCurrentMultiplier(500);
                upgrade.setUpgradeMessage("Disfunctional College Student");
                upgrade.showAchievement(upgrade.getUpgradeMessage());
                addUpgradeToList("4");
                printCurrentUpgrades(upgradeList);
                break;
            case "Upgrade 5":
                this.setCounter(-100000);
                this.setCurrentMultiplier(1000);
                upgrade.setUpgradeMessage("Crippling Addiction");
                upgrade.showAchievement(upgrade.getUpgradeMessage());
                addUpgradeToList("5");
                printCurrentUpgrades(upgradeList);
                break;
        }

        scoreLabel.setText("Cups of Coffee: " + this.getCounter().toString());
        button.setDisable(true);
        label.setTextFill(Color.rgb(160, 160, 160));
    }
    
    public void printCurrentUpgrades(ArrayList<String> list) {
        if (list.size() >= 0) {
            System.out.println("You currently have these upgrades: ");
            
            for (String upgradeNum : list) {   
                System.out.print(upgradeNum + "   ");
            }
        } else {
            System.out.println("You have no upgrades.");
        }
    }
    
    public void checkWinCondition() {
        if (upgradeList.size() == 5) {
            Alert winner = new Alert(Alert.AlertType.INFORMATION);
            winner.setTitle("Winner");
            winner.setHeaderText("Congratulations!");
            winner.setContentText("You've beaten Coffee Clicker! While there are no more upgrades, you may continue to play as normal. Thanks for playing!");
            winner.showAndWait();
        }
    }
}
