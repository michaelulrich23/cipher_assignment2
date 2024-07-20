package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static final int key = 12;
    private static final int modulo = 26;

    public static void main(String[] args) throws IOException {
        String cipher = readInputFile("1.txt");
        String[] cosets = makeCosets(cipher);
        StringBuilder vigKey = new StringBuilder();
        for (int i = 0; i < key; i++) {
            double minimum = 100;
            int indexOfMin = modulo;
            double[] cosetScore = new double[modulo];
            for (int j = 0; j < modulo; j++) {
                cosetScore[j] = CaesarCipher.solveCaesar(cosets[i], j);
                CaesarCipher.decrypt(cosets[i], j);
                if(cosetScore[j] < minimum){
                    minimum = cosetScore[j];
                    indexOfMin = j;
                }
            }
            vigKey.append((char) ('a' + indexOfMin));
        }
        String plainText = VigenereCipher.decrypt(cipher, vigKey.toString());
        System.out.println("PT: " + plainText + "\n");
        System.out.println("CT: " + cipher + "\n");
        System.out.println("KEY: " + vigKey);
    }

    private static String readInputFile(String input) throws IOException {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(input))){
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line);
            }
        }
        return text.toString();
    }

    private static String[] makeCosets(String cipher) {
        String[] cosets = new String[key];
        for (int i = 0; i < key; i++) {
            StringBuilder cosetBuilder = new StringBuilder();
            for (int j = i; j < cipher.length(); j += key) {
                cosetBuilder.append(cipher.charAt(j));
            }
            cosets[i] = cosetBuilder.toString();
        }
        return cosets;
    }
}