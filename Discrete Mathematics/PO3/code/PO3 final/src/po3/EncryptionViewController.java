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
public class EncryptionViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane parent;
    @FXML
    public TextField nValue = new TextField();
    @FXML
    public TextField mValue = new TextField();
    @FXML
    private int encryptInt;
    @FXML
    private String encryptString;
    @FXML
    private String encryptString2;
    @FXML
    public TextField step1 = new TextField();
    @FXML
    public TextField step11 = new TextField();
    @FXML
    public TextField step2 = new TextField();
    @FXML
    public TextField step3 = new TextField();
    
    Integer[] PandQ = null;
    Encryption encryption = new Encryption();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }    
    
        @FXML
     private void encryptStart(ActionEvent event) throws IOException {
        encryptString = nValue.getText();
        Integer es = Integer.parseInt(encryptString);
        encryption.setNValue(es);
        encryption.findPrimePair();
        
        //2.2.1
        //1. Finding P and Q
        Integer[] PandQ = encryption.getPrimeNumbers();
        String [] PandQString = new String[2];
        for(int i = 0; i < 2; i++){
            PandQString[i] = PandQ[i].toString();
        }
        String step1output = "p is " + PandQString[0] + "\n" 
                + ", q is " + PandQString[1];
        step1.setText(step1output);  
        
        
        String step11output = "Amount of time in milliseconds " + encryption.getTimeElapsed();
        step11.setText(step11output); 
     
    }  
    
     @FXML
    private void encryptStart2(ActionEvent event) throws IOException {
        //2. Find possible E-values and choose a random e-value
        Integer[] PandQ = encryption.getPrimeNumbers();
        ArrayList<Integer> possibleValuesOfE = encryption.calculatePossibleEValues(PandQ[0], PandQ[1]);
        Integer randomNumberOfArraySize = ThreadLocalRandom.current().nextInt(0, possibleValuesOfE.size());
        //System.out.println(randomNumberOfArraySize);
        Integer chosenEValue = possibleValuesOfE.get(randomNumberOfArraySize);
        encryption.setEValue(chosenEValue);
        System.out.println("e is " + chosenEValue);
        
        String step2output = "e is " + encryption.getEValue();
        step2.setText(step2output);
    }  
    
    @FXML
    private void encryptStart3(ActionEvent event) throws IOException {
        encryptString2 = mValue.getText();
        //3.1 convert to Caesar Cipher
        encryption.convertMessageToCaesarCipher(encryptString2);

        //3.2 Encrypt the Message
        encryption.encryptMessage();
        System.out.println("Message after encryption is: " + encryption.getEncryptedMessage() +"\n");
        
        String step3output = "Message after encryption is: " + encryption.getEncryptedMessage();
        step3.setText(step3output);
    }
        
}
