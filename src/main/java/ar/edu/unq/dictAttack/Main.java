package ar.edu.unq.dictAttack;

public class Main {
    //Recibe primero el buffer y despues la cantidad de trheads y por ultimo la cantidad de salt
    public static void main(String [] args) {
        int tamanio =1; //Integer.parseInt(args[0]);
        int cantidadThreads =1; //Integer.parseInt(args[1]);
        int maxSalt =99; //Integer.parseInt(args[2]);
        Clock reloj = new Clock(cantidadThreads);
        Buffer buffer = new Buffer(tamanio);
        ThreadPool threadPool = new ThreadPool(buffer,cantidadThreads,reloj);
        String pathDiccionario = "./src/main/resources/dictionary.txt";
        String pathHash = "./src/main/resources/passwd.txt";
        Reader reader = new Reader(pathHash);
        String hash = reader.getPassword();

        try{
            while(hash != null){
                buffer.write(new DecodeTask(hash,maxSalt,pathDiccionario));
                hash= reader.getPassword();
            }

            threadPool.stop();
        }
        catch ( InterruptedException e) {

        }
    }

}
