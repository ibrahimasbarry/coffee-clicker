/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeclicker;

import static coffeeclicker.KfjmbbCoffeeClicker.stage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Kynan
 */
public class GameController implements Initializable {
    
    private Image coffeeButtonImage;
    
    private GameManager gameManager;
    
    @FXML 
    private Label counterLabel;
    
    @FXML
    private Button coffeeButton;
    
    @FXML
    private Button upgradeButton1;
    
    @FXML
    private Button upgradeButton2;
    
    @FXML
    private Button upgradeButton3;
    
    @FXML
    private Button upgradeButton4;
    
    @FXML
    private Button upgradeButton5;
    
    @FXML
    private Label upgradeLabel1;
    
    @FXML
    private Label upgradeLabel2;
    
    @FXML
    private Label upgradeLabel3;
    
    @FXML
    private Label upgradeLabel4;
    
    @FXML
    private Label upgradeLabel5;
    
    @FXML
    private void handleCoffeeClick() {
        gameManager.setCounter(1 * gameManager.getCurrentMultiplier());
        counterLabel.setText("Cups of Coffee: " + gameManager.getCounter());
    }
    
    @FXML
    private void handleUpgradeClick(ActionEvent event) {
        Button buttonPressed;
        String buttonName = "";
        
        try {
            buttonPressed = (Button)event.getSource();
            buttonName = buttonPressed.getText();
        } catch (Exception ex) {
            System.out.println("Event source could not be converted to a button.");
        }
        
        switch (buttonName) {
            case "Upgrade 1":
                if (gameManager.canPurchaseUpgrade(upgradeLabel1, gameManager.getCounter())) {
                    gameManager.purchaseUpgrade(counterLabel, upgradeButton1, upgradeLabel1);
                }  
                break;
            case "Upgrade 2":
                if (gameManager.canPurchaseUpgrade(upgradeLabel2, gameManager.getCounter())) {
                    gameManager.purchaseUpgrade(counterLabel, upgradeButton2, upgradeLabel2);
                }
                break;
            case "Upgrade 3":
                if (gameManager.canPurchaseUpgrade(upgradeLabel3, gameManager.getCounter())) {
                    gameManager.purchaseUpgrade(counterLabel, upgradeButton3, upgradeLabel3);
                } 
                break;
            case "Upgrade 4":
                if (gameManager.canPurchaseUpgrade(upgradeLabel4, gameManager.getCounter())) {
                    gameManager.purchaseUpgrade(counterLabel, upgradeButton4, upgradeLabel4);
                } 
                break;
            case "Upgrade 5":
                if (gameManager.canPurchaseUpgrade(upgradeLabel5, gameManager.getCounter())) {
                    gameManager.purchaseUpgrade(counterLabel, upgradeButton5, upgradeLabel5);
                    gameManager.checkWinCondition();
                } 
                break;
            default:
                System.out.println("Upgrade does not exist!");
                break;
        }
    }
    
    @FXML
    private void handleReturnToMenu(ActionEvent event) {
        try {
            Parent menuRoot = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene menuScene = new Scene(menuRoot);
            stage.setScene(menuScene);
            stage.show();
        } catch(Exception ex) {
            System.out.println("Could not load menu scene.");
        }
    }
    
    @FXML
    private void handleSave(ActionEvent event) {
        GameManager gameSave;
        gameSave = createManagerFromCurrentState();
        
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try
            {
               FileOutputStream fileOut = new FileOutputStream(file.getPath());
               ObjectOutputStream out = new ObjectOutputStream(fileOut);
               out.writeObject(gameSave);
               out.close();
               fileOut.close();
            }catch(IOException ioex)
            {
                System.out.println("Exception occurred while saving to " + file.getPath());
            } 
        } 
    }

    @FXML
    private void handleGameExitAction(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // create instance of game manager here
        gameManager = new GameManager();
        
        // load title resources (load dynamically to ensure it is bundled with the project)
        coffeeButtonImage = new Image(this.getClass().getResourceAsStream("KfjmbbCoffeeButton.png"));
        coffeeButton.setGraphic(new ImageView(coffeeButtonImage));
        
        /* place button at correct location (this has to be dynamic because due to the nature of the AnchorPane and Button,
         * the loading of the image causes the size of the button to change while it's position remains the same, which looks bad */
        coffeeButton.setLayoutX(stage.getWidth() / 2 - coffeeButtonImage.getWidth() / 2);
        counterLabel.setText("Cups of Coffee: " + gameManager.getCounter());
    }
    
    private GameManager createManagerFromCurrentState() {
        GameManager savedGame = new GameManager();
        
        // save counter
        savedGame.setCounter(this.gameManager.getCounter());
        
        // save multiplier
        savedGame.setCurrentMultiplier(this.gameManager.getCurrentMultiplier());

        // save list of upgrades
        savedGame.setCurrentUpgradeList(this.gameManager.getCurrentUpgradeList());
        
        return savedGame;
    }
    
    public GameManager getGameManager() {
        return this.gameManager;
    }
    
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    
    public Label getCounterLabel() {
        return this.counterLabel;
    }
    
    public Button getButton1() {
        return this.upgradeButton1;
    }
    
    public Button getButton2() {
        return this.upgradeButton2;
    }
    
    public Button getButton3() {
        return this.upgradeButton3;
    }
    
    public Button getButton4() {
        return this.upgradeButton4;
    }
    
    public Button getButton5() {
        return this.upgradeButton5;
    }
    
    public Label getUpgradeLabel1() {
        return this.upgradeLabel1;
    }
    
    public Label getUpgradeLabel2() {
        return this.upgradeLabel2;
    }
    
    public Label getUpgradeLabel3() {
        return this.upgradeLabel3;
    }
    
    public Label getUpgradeLabel4() {
        return this.upgradeLabel4;
    }
    
    public Label getUpgradeLabel5() {
        return this.upgradeLabel5;
    }
}
