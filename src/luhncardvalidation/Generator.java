/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luhncardvalidation;

import java.util.Random;

/**
 *
 * @author kiss-
 */
public class Generator {

    public static long generate(boolean b) {
        long rndCardNumber = 0;
        Random rnd = new Random();      
        while (!Validator.isValid(rndCardNumber)) {
            rndCardNumber = rnd.nextLong();
        }
        String rndCardNumberString = rndCardNumber + "";
        if (!b) {
            int i = rnd.nextInt(3);
            switch (i) {
                case 0:
                    rndCardNumber--;
                    break;
                case 1:
                    
                    int j = 4;
                    while (j == 4 || j == 5 || j == 6 || (j == 3 && rndCardNumberString.charAt(1) == 7)) {
                        j = rnd.nextInt(9) + 1;
                    }
                    String temp = rndCardNumberString.substring(1, rndCardNumberString.length() - 1);
                    String changed = j + rndCardNumberString.substring(1, rndCardNumberString.length() - 1);
                    rndCardNumber = Long.parseLong(changed);
                    break;
                case 2:
                    boolean isShortEnough = false;
                    while (!isShortEnough) {
                        rndCardNumber = rndCardNumber / 10;
                        rndCardNumberString = rndCardNumber + "";
                        if (rndCardNumberString.length() < 13) {
                            isShortEnough = true;
                        }
                    }
                    break;
            }

        }
        return rndCardNumber;
    }


}
