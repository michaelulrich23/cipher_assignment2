package org.example;

public class VigenereCipher {
    
    static final int modulo = 26;
    
    public static String decrypt(String ct, String key){
        StringBuilder pt = new StringBuilder();
        for(int i = 0; i < ct.length(); i++){
            int inNum = ct.charAt(i) - 'a';
            inNum -= key.charAt(i % key.length()) - 'a';
            inNum += modulo;
            inNum %= modulo;
            char inChar = (char) (inNum + 'a');
            pt.append(inChar);
        }
        return pt.toString();
    }

}
