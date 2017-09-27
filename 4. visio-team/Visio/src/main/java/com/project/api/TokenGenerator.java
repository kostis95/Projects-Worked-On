/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.api;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class generates tokens for the users tokens are used in order to
 * identify mobile users
 *
 * One unique token per user!
 *
 * @author Youri
 */
public class TokenGenerator {

    /**
     * Generates a unique token (md5 salted) based on the user id and a random
     * number
     *
     * @param id the id of the user (prevents duplicates)
     */
    public static String getToken(int id) {
        String unhashedToken = Integer.toString(getRandomNumber()); // add a random number to the string
        unhashedToken += Integer.toString(id); // add the user id to the string

        try {
            MessageDigest m = MessageDigest.getInstance("MD5"); // get the md5 hash method
            m.update(unhashedToken.getBytes(), 0, unhashedToken.length()); // assign the string to the md5
            
            return new BigInteger(1, m.digest()).toString(16);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(TokenGenerator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /***
     * 
     * @return a random number
     */
    public static int getRandomNumber() {
        Random r = new Random(System.currentTimeMillis());
        return 100000000 + r.nextInt(999999999);
    }

}
