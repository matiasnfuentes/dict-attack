package ar.edu.unq.dictAttack;

public class DictAttackWorker extends Thread{

    private Buffer buffer ;

    public DictAttackWorker (Buffer buffer){
        this.buffer = buffer ;
    }


    @Override
    public void run () {
        try {
            while (true){
                Runnable task = buffer.read();
                try {
                    task.run();
                }catch (PoisonException e){
                    System.out.println("Se terminó la decodificación de los password" +
                            "el thread se detiene.");
                }
            }
        }
        catch ( InterruptedException e) {}
    }
}
