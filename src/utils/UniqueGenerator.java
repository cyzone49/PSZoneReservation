package utils;

import java.util.Random;

public class UniqueGenerator {

    public static String generate(String prompt) {
        String validChars = "abcdefghijklmnopqrstuvwxyz0123456789";
        int tokenLength = 8;

        StringBuilder sb = new StringBuilder();
        sb.append(prompt);
        for(int i = 0; i < tokenLength; ++i) {
            Random random = new Random();
            int index = random.nextInt(35+1);
            sb.append(validChars.charAt(index));
        }
        String creationTimeStamp = Long.toString(System.currentTimeMillis());
        System.out.println("TIME STAMP = " + creationTimeStamp);
        sb.append(creationTimeStamp.substring(0,5));

        return sb.toString();
    }
}
