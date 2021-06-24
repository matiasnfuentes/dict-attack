package ar.edu.unq.dictAttack;

public class Buffer {

    private Runnable [] data;
    private int begin = 0, end = 0;

    public Buffer(int tamanio){
        data = new Runnable [tamanio +1];
    }

    synchronized void write ( Runnable o) throws InterruptedException {
        while ( isFull ()) { wait (); }
        data [ begin ] = o;
        begin = next ( begin );
        notifyAll ();
    }
    synchronized Runnable read () throws InterruptedException {
        while ( isEmpty ()) { wait (); }
        Runnable result = data [ end ];
        end = next ( end );
        notifyAll ();
        return result ;
    }
    private boolean isEmpty () { return begin == end; }
    private boolean isFull () { return next ( begin ) == end ; }
    private int next ( int i) { return (i+1) %(data.length +1); }
}
