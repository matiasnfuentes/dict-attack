package ar.edu.unq.dictAttack;

public class Main {

    //Recibe primero tamaño del buffer,  la cantidad de trheads y por ultimo la max de salt

    public static void main(String [] args) {

        int tamanio = Integer.parseInt(args[0]);
        int cantidadThreads = Integer.parseInt(args[1]);
        int maxSalt = Integer.parseInt(args[2]);
        if (maxSalt<0 || maxSalt>99){
            throw new RuntimeException("El salt pasado como parámetro es incorrecto");
        }
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
        catch ( InterruptedException e) {}
    }

}
