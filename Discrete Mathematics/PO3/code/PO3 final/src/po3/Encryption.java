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
import java.util.ArrayList;
import java.util.Scanner;

public class Encryption {

    private Integer[] primeNumbers = new Integer[2];
    private String CaesarCipher = "";
    private Integer NValue;
    private Integer EValue;
    private String encryptedMessage = "";
    private double timeElapsed;

    public double getTimeElapsed() {
        return timeElapsed;
    }

    public Encryption() {
    }

    public Encryption(Integer N, Integer E) {
        this.NValue = N;
        this.EValue = E;
    }

    //Find whether a number consists of two prime numbers, and if so save them
    public void findPrimePair() {
        int NValue = this.getNValue();
        long startTime = System.nanoTime();
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
                this.primeNumbers[0] = (x);
                this.primeNumbers[1] = (i);
//                System.out.println("p is " + i + "\nq is " + x);
                timeElapsed = System.nanoTime() - startTime;
                System.out.println("Amount of time busy finding p and q: " + timeElapsed / 1000000);
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


    public ArrayList<Integer> calculatePossibleEValues(int p, int q){

        Integer relatiefPriemMet = (p - 1) * (q - 1);
        Integer[] arrayOfPrimes = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47};
        ArrayList<Integer> possibleValuesOfE = new ArrayList<>();

        for(int i = 0; i < arrayOfPrimes.length && arrayOfPrimes[i] < relatiefPriemMet; i++){
            if(relatiefPriemMet % arrayOfPrimes[i] != 0){
                possibleValuesOfE.add(arrayOfPrimes[i]);
            }
        }
        //System.out.println("(p-1)(q-1) = " + relatiefPriemMet);
        //System.out.println(possibleValuesOfE + " ");
        return possibleValuesOfE;
    }


    //Here we encrypt the CaesarCipher form of the encryptedMessage
    public void encryptMessage(){
        String[] splitMessage = this.CaesarCipher.split(" ");
        for(int i = 0; i < splitMessage.length; i++){
            Integer numberToEncrypt = Integer.parseInt(splitMessage[i]);
            Integer encryptedNumber = BigInteger.valueOf(numberToEncrypt).pow(this.EValue).mod(BigInteger.valueOf(this.NValue)).intValue();
            encryptedMessage += encryptedNumber.toString() + " ";
        }
    }


    //Here we convert the whole encryptedMessage to CaesarCipher
    public void convertMessageToCaesarCipher(String messageToEncrypt){

        for(int i = 0; i < messageToEncrypt.length(); i++){
            char currentChar = messageToEncrypt.charAt(i);
            String newStringLetter = convertLetterToCaesarCipher(Character.toString(currentChar));
            if (newStringLetter == null){ continue; }
            this.CaesarCipher = this.CaesarCipher + newStringLetter + " ";
        }
    }


    //The CaesarCipher conversion per letter
    public static String convertLetterToCaesarCipher(String letter){

        String[][] caesarCipher = {{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
        "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", " ", ".", ","}, {"1", "2" ,"3",
                "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
                "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"}};
        String newLetter = null;

        for(int i = 0; i < 29; i++){
            if(letter.toUpperCase().equals(caesarCipher[0][i])){
                newLetter = caesarCipher[1][i];
                break;
            }
        }
        return newLetter;
    }



    public Integer[] getPrimeNumbers() {
        return primeNumbers;
    }

    public String getCaesarCipher() {
        return CaesarCipher;
    }

    public Integer getNValue() {
        return NValue;
    }

    public void setNValue(Integer NValue) {
        this.NValue = NValue;
    }

    public Integer getEValue() {
        return EValue;
    }

    public void setEValue(Integer EValue) {
        this.EValue = EValue;
    }

    public String getEncryptedMessage() {
        return encryptedMessage;
    }
}

