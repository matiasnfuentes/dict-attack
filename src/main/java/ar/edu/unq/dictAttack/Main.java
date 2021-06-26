package ar.edu.unq.dictAttack;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(2);
        ThreadPool threadPool = new ThreadPool(buffer,8);
        String pathDiccionario = "./src/main/resources/dictionary.txt";
        String pathHash = "./src/main/resources/passwd.txt";
        PasswordReader reader = new PasswordReader(pathHash);
        String hash = reader.getPassword();

        try{
            while(hash != null){
                buffer.write(new DecodeTask(hash,99,pathDiccionario));
                hash= reader.getPassword();
            }
            for(int i = 0; i<8; i++){
                buffer.write(new PoisonPill());
            }
        }
        catch ( InterruptedException e) {}
    }

}
