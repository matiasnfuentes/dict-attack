package ar.edu.unq.dictAttack;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class DecodeTask implements Runnable {

    public static void main(String[] args) {
        DecodeTask d  = new DecodeTask("perla.crespi.escamilla;a1c96fa7f92043af5fc468c7c09005dac6a9341a06bcf5e648e60c420b9d3046",99);
        d.run();

    }

    private String hashToDecode;
    private int maxSalt;

    public DecodeTask(String hashToDecode, int maxSalt){
        this.hashToDecode = hashToDecode;
        this.maxSalt = maxSalt;
    }

    @Override
    public void run(){
        try {
            ArchivoSalida writer = new ArchivoSalida();
            MessageDigest digest =  MessageDigest.getInstance("SHA-256");
            String [] parts = hashToDecode.split(";");
            String user = parts[0];
            byte[] hash = hexToByteArray(parts[1]);
            String toHash = "";
            String pass;
            String salt;
            byte [] bytes;
            byte [] digested = new byte[0];
            int i = 0;
            while (i<=maxSalt && !Arrays.equals(digested, hash)){
                PasswordReader reader = new PasswordReader();
                salt = i + "#";
                pass = reader.getPassword();
                while(pass!=null && !Arrays.equals(digested, hash)){
                    toHash = salt + pass;
                    bytes = toHash.getBytes();
                    digested = digest.digest(bytes);
                    pass = reader.getPassword();
                }
                i++;
            }
            String hashObtained = user + " " + "pass : " + toHash.split("#")[1] + "\n";
            writer.writeCrackedPass(hashObtained);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private String byteArrayTOHex(byte[] a ){
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a){
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
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
