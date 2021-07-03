package ar.edu.unq.dictAttack;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class DecodeTask implements Runnable {


    private String hashToDecode;
    private int maxSalt;
    private String path;

    public DecodeTask(String hashToDecode, int maxSalt, String path){
        this.hashToDecode = hashToDecode;
        this.maxSalt = maxSalt;
        this.path = path;
    }

    @Override
    public void run(){
        try {
            MessageDigest digest =  MessageDigest.getInstance("SHA-256");
            String [] parts = hashToDecode.split(";");
            String usuario = parts[0];
            byte[] hashBuscado = hexToByteArray(parts[1]);
            String passwordConSalt = "";
            String pass;
            String salt;
            byte [] bytes;
            byte [] hashActual = new byte[0];
            int i = 0;

            while (i<=this.maxSalt && !Arrays.equals(hashActual, hashBuscado)){
                Reader reader = new Reader(this.path);
                salt = i + "#";
                pass = reader.getPassword();
                while(pass!=null && !Arrays.equals(hashActual, hashBuscado)){
                    passwordConSalt = salt + pass;
                    bytes = passwordConSalt.getBytes();
                    hashActual = digest.digest(bytes);
                    pass = reader.getPassword();
                }
                i++;
            }

            String hashObtained = usuario + " " + "pass : " + passwordConSalt.split("#")[1] + "\n";
            ArchivoSalida.write(hashObtained);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private byte[] hexToByteArray(String hash) {
        byte[] res = new byte[hash.length() / 2];
        for (int i = 0; i < res.length; i++) {
            int index = i * 2;
            int j = Integer.parseInt(hash.substring(index, index + 2), 16);
            res[i] = (byte) j;
        }
        return res;
    }

}
