package utils;

import java.io.Serializable;
import java.util.Random;

public class UniqueChecker implements Serializable {


    /**
     * Generate id/authToken that is not associated with an existing User for a new User object
     * @return String unique id/authToken
     */
    public static String generateUniqueID()
    {
        String validChars = "abcdefghijklmnopqrstuvwxyz0123456789";
        int tokenLength = 8;

        StringBuilder tokenBuilder = new StringBuilder();

        boolean unique = false;

        while( !unique ) {
            for(int i = 0; i < tokenLength; i++) {
                Random random = new Random();
                int index = random.nextInt(validChars.length());
                tokenBuilder.append(validChars.charAt(index));
            }
            if(checkUniqueness(tokenBuilder.toString())) {
                unique = true;
            }
        }

        return tokenBuilder.toString();
    }

    /**
     * Checks for uniqueness of generated authToken
     * @param authToken
     * @return boolean for authtoken validity
     */
    public static boolean checkUniqueness(String authToken){
        //TODO: grab all auth tokens from DB

//        for (User user : UserManager.getInstance().getUsers()) {
//            if (authToken.equals(user.getAuthtoken())){
//                return false;
//            }
//        }
        return true;
    }


}

