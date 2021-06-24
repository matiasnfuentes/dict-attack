package ar.edu.unq.dictAttack;

import java.io.*;

public class PasswordReader {

    FileReader f;
    BufferedReader b;

    public PasswordReader(){
        try {
            this.f = new FileReader("./src/main/resources/dictionary.txt");
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
