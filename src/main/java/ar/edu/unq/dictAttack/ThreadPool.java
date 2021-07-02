package ar.edu.unq.dictAttack;

public class ThreadPool {

    private Buffer buffer;
    private int threadNumber;

    public ThreadPool(Buffer buffer, int threadNumber,Clock reloj){
        this.buffer = buffer;
        this.threadNumber = threadNumber;
        for (int i =0;i<threadNumber;i++){
            DictAttackWorker worker = new DictAttackWorker(this.buffer,reloj);
            worker.start();
        }
    }

    public void lauch(Runnable task){
        try{
            this.buffer.write(task);
        } catch (InterruptedException e) {}
    }

    public void stop(){
        for (int i = 0; i < threadNumber; i++) {
            this.lauch(new PoisonPill());
        }
    }
}
