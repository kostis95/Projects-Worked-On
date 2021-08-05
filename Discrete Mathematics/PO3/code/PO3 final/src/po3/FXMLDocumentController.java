/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Mats
 */
public class FXMLDocumentController implements Initializable {
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void loadEncryption(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("encryptionView.fxml"));        
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        
        stage.setTitle("Encryption view");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
    @FXML
    private void loadDecryption(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("decryptionView.fxml"));        
        Parent root2 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        
        stage.setTitle("Decryption view");
        stage.setScene(new Scene(root2));
        stage.show();;
    }  
    
    @FXML
    private void exitApplication(ActionEvent event) throws IOException {
        System.exit(0); 
    }  
}
