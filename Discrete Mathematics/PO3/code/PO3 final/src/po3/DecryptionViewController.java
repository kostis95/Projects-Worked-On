/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po3;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mats
 */
public class DecryptionViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane parent;
    @FXML
    public TextField nValue = new TextField();
    @FXML
    public TextField eValue = new TextField();
    @FXML
    public TextField step2outputD = new TextField();
    @FXML
    public TextField encryptedMessage = new TextField();
    @FXML
    private String decryptString;
    @FXML
    private String decryptString2;
    @FXML
    private String decryptInputString;

    Encryption encryption = new Encryption();
    Decryption decryption = new Decryption();
    Integer decriptionKey;

    
    @FXML
    public TextField step1outputD = new TextField();
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
     private void decryptStart(ActionEvent event) throws IOException {
        decryptString = nValue.getText();
        decryptString2 = eValue.getText();
        Integer NValue = Integer.parseInt(decryptString); 
        Decryption decryption = new Decryption(NValue, 
                Integer.parseInt(decryptString2));
        decryption.findPrimePair();
        System.out.println(decryptString);
 
//        //2.2.2
//        //1. Calculating D
        decryption.findDecryptionKey();
        decriptionKey = decryption.getDecryptionKey();
        System.out.println("d is after Decryption is: " + decryption.getDecryptionKey());
        
        String step1output = ("d is  after Decryption is: " + decryption.getDecryptionKey());
        step1outputD.setText(step1output); 
    }  
     
    @FXML
    private void decryptStart2(ActionEvent event) throws IOException {
        //2. Decrypting the message
        decryptInputString = encryptedMessage.getText();
        decryption.NValue = Integer.parseInt(decryptString);
        decryption.setDecryptionKey(decriptionKey);
        decryption.decryptMessage(decryptInputString);
        System.out.println("Message after Decryption is: " + decryption.getDecryptedMessage());
        
        String step2output = ("Message after Decryption is: " + decryption.getDecryptedMessage());
        step2outputD.setText(step2output); 
    }
}
