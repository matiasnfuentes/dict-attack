package ar.edu.unq.dictAttack;

import java.io.*;

public class Reader {

    FileReader f;
    BufferedReader b;

    public Reader(String path){
        try {
            this.f = new FileReader(path);
            this.b = new BufferedReader(f);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getPassword(){
        String cadena = "";
        try {
            cadena = b.readLine();
            if(cadena == null){
                b.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cadena;
    }

}
