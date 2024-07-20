package org.example;


public class CaesarCipher {
    static final int modulo = 26;
    
    public static String decrypt(String ct, int key){
        StringBuilder pt = new StringBuilder();
        
        for (char c : ct.toCharArray()){
            int inNum = (c - 'a');
            inNum = inNum - key;
            inNum = inNum + modulo;
            inNum = inNum % modulo;
            char inChar = (char) (inNum + 'a');
            pt.append(inChar);
        }
        return pt.toString();
    }
    
    public static double solveCaesar(String input, int key){
        L1MonogramFitness f = new L1MonogramFitness();
        double score;
        String tmp = decrypt(input, key);
        score = f.evaluate(tmp);
        return score;
    }
}
