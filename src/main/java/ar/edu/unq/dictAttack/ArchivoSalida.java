package ar.edu.unq.dictAttack;

import java.io.*;

public class ArchivoSalida {

    public void writeCrackedPass(String content){
        try (FileWriter writer = new FileWriter("./src/main/resources/cracked.txt", true);
             BufferedWriter bw = new BufferedWriter(writer)) {
             bw.write(content);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

}