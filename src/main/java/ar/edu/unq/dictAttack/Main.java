package ar.edu.unq.dictAttack;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(2);
        ThreadPool threadPool = new ThreadPool(buffer,8);
    }
}
