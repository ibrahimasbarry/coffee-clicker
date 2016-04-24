/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coffeeclicker;

import static coffeeclicker.KfjmbbCoffeeClicker.stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/**
 *
 * @author Kynan
 */
public class MenuController implements Initializable {
    
    private Image titleImage;
    
    @FXML
    private ImageView titleImageView;
    
    @FXML
    private Label versionLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // prevents stage from being resized
        KfjmbbCoffeeClicker.stage.setResizable(false);
        
        // load title resources (load dynamically to ensure it is bundled with the project)
        titleImage = new Image(this.getClass().getResourceAsStream("KfjmbbStartIcon.png"));
        titleImageView.setImage(titleImage);  
    }
    
    @FXML
    private void handleStartNewGameButtonAction(ActionEvent event) {
        try {
            Parent gameRoot = FXMLLoader.load(getClass().getResource("Game.fxml"));
            Scene gameScene = new Scene(gameRoot);

            stage.setScene(gameScene);
            stage.show();
        } catch(Exception ex) {
            System.out.println("Could not load game scene.");
        }
    }
    
    @FXML
    private void handleLoadGameButtonAction(ActionEvent event) {   
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root = (Parent)loader.load();
            GameController controller = (GameController)loader.getController();
    
            GameManager loadedGame;
            
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                try
                {
                   FileInputStream fileIn = new FileInputStream(file.getPath());
                   ObjectInputStream in = new ObjectInputStream(fileIn);
                   loadedGame = (GameManager) in.readObject();
                   in.close();
                   fileIn.close();
                   
                   // load data into game manager and button states to pass to controller
                   loadManagerFromFile(controller, loadedGame);
                   
                   // show the new stage at the end
                   Scene scene = new Scene(root);
                   stage.setScene(scene);
                   stage.show();
                   
                }catch(IOException ioex)
                {
                   System.out.println("Error loading game manager object from file from "+ file.getPath());
                }catch(ClassNotFoundException cnfex)
                {
                   System.out.println("Class not found exception occurred while opening " + file.getPath());
                }
            }
        } catch (IOException ioex) {
            System.out.println("IOException when trying to load game.");
        }
    }
    
    @FXML
    private void handleExitAction(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void handleAboutMenuItem(ActionEvent event) {
        Alert about = new Alert(AlertType.INFORMATION);
        about.setTitle("About");
        about.setHeaderText("Coffee Clicker - " + versionLabel.getText());
        about.setContentText("Coffee Clicker was created by Kynan Justis as a final project for CS3330 at the University of Missouri.\n\nThanks for playing!");
        about.showAndWait();
    }
    
    private void loadManagerFromFile(GameController controller, GameManager loadedGame) {
        controller.setGameManager(loadedGame);
        
        controller.getGameManager().setCurrentMultiplier(loadedGame.getCurrentMultiplier());
        controller.getGameManager().setCurrentUpgradeList(loadedGame.getCurrentUpgradeList());
        
        controller.getCounterLabel().setText("Cups of Coffee: " + controller.getGameManager().getCounter().toString());
        
        for (String upgrade : controller.getGameManager().getCurrentUpgradeList()) {
            switch (upgrade) {
                case "1":
                    controller.getButton1().setDisable(true);
                    controller.getUpgradeLabel1().setTextFill(Color.rgb(160, 160, 160));
                    break;
                case "2":
                    controller.getButton2().setDisable(true);
                    controller.getUpgradeLabel2().setTextFill(Color.rgb(160, 160, 160));
                    break;
                case "3":
                    controller.getButton3().setDisable(true);
                    controller.getUpgradeLabel3().setTextFill(Color.rgb(160, 160, 160));
                    break;
                case "4":
                    controller.getButton4().setDisable(true);
                    controller.getUpgradeLabel4().setTextFill(Color.rgb(160, 160, 160));
                    break;
                case "5":
                    controller.getButton5().setDisable(true);
                    controller.getUpgradeLabel5().setTextFill(Color.rgb(160, 160, 160));
                    break;
                default:
                    System.out.println("No button or label states to load.");
                    break;
            }
        }
    }
}
