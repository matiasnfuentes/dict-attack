package ar.edu.unq.dictAttack;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Clock {
    private long tiempoQueComenzoLaEjecucion;
    private int threadsEjecutando;

    public Clock(int threads){
        this.tiempoQueComenzoLaEjecucion = System.currentTimeMillis();
        this.threadsEjecutando = threads;
    }

    public String tiempoTotal(){
        Date date = new Date(System.currentTimeMillis() - this.tiempoQueComenzoLaEjecucion);
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateFormatted = formatter.format(date);
        return (dateFormatted);
    }
    synchronized public void finalizoTarea(){
        this.threadsEjecutando --;
        if(this.threadsEjecutando == 0){
            System.out.println("El tiempo de ejecución es de " + this.tiempoTotal());
        }
    }
}
