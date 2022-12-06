package pidvn.auth.utils;

import java.util.Random;

public class RandomUtil {

    public static String random(int length, String type) {

        String [] character = new String[] {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "B", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        };

        String [] number = new String[] {"0","1","2","3","4","5","6","7","8","9"};

        String [] special = new String[] {"@","$","!","%","*","#","?","&","`"};

        String randomString = "";
        String lookup[] = null;
        int upperBound = 0;
        Random random = new Random();

        lookup = null;

        if (type.equals("character")) {
            lookup = character;
            upperBound = 32;
        }else if (type.equals("number")) {
            lookup = number;
            upperBound = 10;
        }else if (type.equals("special")) {
            lookup = special;
            upperBound = 9;
        }

        for (int i = 0; i < length; i++)
        {
            randomString = randomString + lookup[random.nextInt(upperBound)];
        }

        return randomString;

    }

}
