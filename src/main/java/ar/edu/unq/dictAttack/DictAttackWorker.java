package ar.edu.unq.dictAttack;

public class DictAttackWorker extends Thread{

    private Buffer buffer ;

    public DictAttackWorker (Buffer buffer){
        this.buffer = buffer ;
    }

    @Override
    public void run () {
        try {
            //TODO: Los ar.edu.unq.dictAttack.DictAttackWorker deben consumir “unidades de trabajo”
            // Propongo como unidades de trabajo cada uno de los hashes a buscar
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
