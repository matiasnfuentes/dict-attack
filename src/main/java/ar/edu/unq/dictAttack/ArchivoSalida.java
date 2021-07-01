package ar.edu.unq.dictAttack;

import java.io.*;

public class ArchivoSalida {

    public static BufferedWriter bw;

    public static synchronized void write(String pass) {
        try{
            if( bw == null ) {
                bw =  new BufferedWriter(new FileWriter("./src/main/resources/cracked.txt", true));
            }
            bw.write(pass);
            bw.flush();
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }


}