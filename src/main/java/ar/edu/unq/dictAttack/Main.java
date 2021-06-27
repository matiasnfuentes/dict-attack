package ar.edu.unq.dictAttack;

public class Main {
    //Recibe primero el buffer y despues la cantidad de trheads y por ultimo la cantidad de salt
    public static void main(int [] args) {
        int cantidadThreads = args[1];
        Buffer buffer = new Buffer(args[0]);
        ThreadPool threadPool = new ThreadPool(buffer,cantidadThreads);
        String pathDiccionario = "./src/main/resources/dictionary.txt";
        String pathHash = "./src/main/resources/passwd.txt";
        PasswordReader reader = new PasswordReader(pathHash);
        String hash = reader.getPassword();

        try{
            while(hash != null){
                buffer.write(new DecodeTask(hash,args[2],pathDiccionario));
                hash= reader.getPassword();
            }
            for(int i = 0; i<cantidadThreads; i++){
                buffer.write(new PoisonPill());
            }
        }
        catch ( InterruptedException e) {}
    }

}
