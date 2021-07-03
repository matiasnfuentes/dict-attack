package ar.edu.unq.dictAttack;

public class DictAttackWorker extends Thread{

    private Buffer buffer ;
    private boolean runinng = true;
    private Clock reloj;

    public DictAttackWorker (Buffer buffer,Clock reloj){
        this.buffer = buffer ; this.reloj = reloj;
    }


    @Override
    public void run () {
        try {
            while (runinng){
                Runnable task = buffer.read();
                try {
                    task.run();
                }catch (PoisonException e){
                    runinng=false;
                    System.out.println("Se terminó la decodificación de los password" +
                            "el thread se detiene.");
                    reloj.finalizoTarea();
                }
            }
        }
        catch (InterruptedException e) {}
    }
}
