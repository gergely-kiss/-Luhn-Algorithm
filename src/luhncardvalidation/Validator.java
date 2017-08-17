/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luhncardvalidation;

/**
 *
 * @author kiss-
 */
public class Validator {

    private static String msg;

    public static boolean isValid(long cardNumber) {
        String cardNumberString = cardNumber + "";
        boolean isValid = isCorrectSizeRange(cardNumberString)
                && isCorrectPrefix(cardNumberString) && ((calculation(cardNumber) % 10) == 0);
        return isValid;
    }

    public static boolean isCorrectSizeRange(String cardNumber) {

        if (cardNumber.length() >= 13 && cardNumber.length() <= 16) {
            return true;
        } else {
            msg = "Length of card number is invalid (" + cardNumber.length() + ")";
            return false;
        }
    }

    private static boolean isCorrectPrefix(String cardNumber) {
        switch (cardNumber.charAt(0)) {
            case '4':
                msg = "Visa";
                return true;
            case '5':
                msg = "Master Card";
                return true;

            case '6':
                msg = "Discover";
                return true;
            case '3':
                if (cardNumber.charAt(1) == '7') {
                    msg = "American Express";
                    return true;
                }
            default:
                msg = "Incorrect prefix";
                return false;
        }
    }

    private static int calculation(long cardNumber) {
        int length = (cardNumber + "").length();
        int sumOne = 0;
        int sumTwo = 0;
        for (int i = length - 1; i >= 0; i--) {
            if ((length % 2) == (i + 1) % 2) {
                sumTwo += cardNumber % 10;
                cardNumber = cardNumber / 10;
            } else {
                sumOne += calcOne(cardNumber);
                cardNumber = cardNumber / 10;
                
            }
        }
        if (((sumOne + sumTwo) % 10) != 0) {
            msg = "Invalid sum of card number calculation";
        }
        return sumOne + sumTwo;
    }

    private static int calcOne(long cardNumber) {
        int calcOne = 0;
        calcOne += cardNumber % 10;
        if (calcOne < 5) {
            calcOne = calcOne * 2;
        } else {
            calcOne = ((calcOne * 2) % 10) + 1;
        }
        return calcOne;
    }

    public static String getMsg() {
        return msg;
    }
}
