/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po3;

/**
 *
 * @author Mats
 */
import java.math.BigInteger;

public class Decryption {

    private Integer decryptionKey;
    private Integer [] cipheredMessage;
    private Integer P;
    private Integer Q;
    private String decryptedMessage = "";
    Encryption encryption = new Encryption();
    Integer NValue;
    Integer EValue;
    String encryptedMessage;

    public Decryption(Integer NValue, Integer EValue) {
        this.NValue = NValue;
        this.EValue = EValue;
    }
    
    public Decryption(){
    }


    // Step 1 find d.
    public void findDecryptionKey(){
        findPrimePair();

        Integer pMinusOneTimesQMinusOne = (this.P - 1) * (this.Q - 1);

        // Finding the inverse: (e * a) modulo (p-1)(q-1), choose a so that the answer is 1
        for(int i = 1; i < NValue; i++) {
            Integer findingInverse = EValue * i % pMinusOneTimesQMinusOne;
            if (findingInverse == 1){
                decryptionKey = i;
                setDecryptionKey(decryptionKey);
                break;
            }
        }
    }


    public void decryptMessage(String encryptedMessage){
       
        decryptToRSA(encryptedMessage);
        decryptCaesarCipher();
    }


    //Decrypting by using the decryption key
    public void decryptToRSA(String encryptedMessage){
        String[] splitMessage = encryptedMessage.split(" ");
        Integer [] cipheredMessage = new Integer[splitMessage.length];

        for(int i = 0; i < splitMessage.length; i++){
            Integer currentNumber = Integer.parseInt(splitMessage[i]);

            //Big Integer is used because Double can not handle numbers of a large size
            Integer intValue = BigInteger.valueOf(currentNumber).pow(decryptionKey).mod(BigInteger.valueOf(NValue)).intValue();
            cipheredMessage[i] = intValue;
        }
        setCipheredMessage(cipheredMessage);
    }


    double power(double d, int n){
        double p=1;
        for(int i=0;i<n;i++)
            p=p*d;
        return(p) ;
    }

    //The conversion of CaesarChiper Message to real Message
    public void decryptCaesarCipher(){
        for(int i = 0; i < cipheredMessage.length; i++){
            String realLetter = convertCaesarCipherToLetter(cipheredMessage[i]);
            decryptedMessage += realLetter;
        }
    }


    //The conversion of CaesarChiper to letter
    public static String convertCaesarCipherToLetter(Integer number){

        String[][] caesarCipher = {{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", " ", ".", ","}, {"1", "2" ,"3",
                "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
                "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"}};
        String newLetter = caesarCipher[0][number - 1];
        return newLetter;
    }


    public void findPrimePair() {
        int NValue = this.NValue;
        int flag = 0;

        // Generating primes using Sieve
        boolean []isPrime= new boolean[NValue + 1];
        SieveOfEratosthenes(NValue, isPrime);

        // Traversing all numbers to find first
        // pair
        for (int i = 2; i < NValue; i++) {
            int x = NValue / i;

            if (isPrime[i] && isPrime[x] && x != i && x * i == NValue)
            {
                //can be used to print
                flag = 1;
                this.P = x;
                this.Q = i;
                return;
            }
        }
        if (flag==0)
            System.out.println("No such pair found");
    }


    private void SieveOfEratosthenes(int n, boolean isPrime[])
    {
        // Initialize all entries of boolean array
        // as true. A value in isPrime[i] will finally
        // be false if i is Not a prime, else true
        // bool isPrime[n+1];
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i <= n; i++)
            isPrime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            // If isPrime[p] is not changed, then it is
            // a prime
            if (isPrime[p] == true) {
                // Update all multiples of p
                for (int i = p * 2; i <= n; i += p)
                    isPrime[i] = false;
            }
        }
    }


    public void setEncryptedMessage(String encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
    }

    public void setCipheredMessage(Integer[] cipheredMessage) {
        this.cipheredMessage = cipheredMessage;
    }

    public Integer getP() {
        return P;
    }

    public void setP(Integer p) {
        P = p;
    }

    public Integer getQ() {
        return Q;
    }

    public void setQ(Integer q) {
        Q = q;
    }

    public void setNValue(Integer NValue) {
        this.NValue = NValue;
    }

    public String getDecryptedMessage() {
        return decryptedMessage;
    }

    public void setDecryptionKey(Integer decryptionKey) {
        this.decryptionKey = decryptionKey;
    }

    public Integer getDecryptionKey() {
        return decryptionKey;
    }
}

