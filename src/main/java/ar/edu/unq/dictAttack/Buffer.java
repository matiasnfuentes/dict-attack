package ar.edu.unq.dictAttack;

public class Buffer {

    private Object [] data;
    private int begin = 0, end = 0;

    public Buffer(int tamanio){
        data = new Object [tamanio +1];
    }

    synchronized void write ( Object o) throws InterruptedException {
        while ( isFull ()) { wait (); }
        data [ begin ] = o;
        begin = next ( begin );
        notifyAll ();
    }
    synchronized Object read () throws InterruptedException {
        while ( isEmpty ()) { wait (); }
        Object result = data [ end ];
        end = next ( end );
        notifyAll ();
        return result ;
    }
    private boolean isEmpty () { return begin == end; }
    private boolean isFull () { return next ( begin ) == end ; }
    private int next ( int i) { return (i+1) %(data.length +1); }
}
