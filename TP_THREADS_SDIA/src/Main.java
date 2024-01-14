public class Main extends Thread {
    public static void main(String[] args) {
       Thread t1=new Thread(new Talkative(10,"T1"));
       Thread t2=new Thread(new Talkative(20,"T2"));
       Thread t3=new Thread(new Talkative(30,"T3"));
       Thread t4=new Thread(new Talkative(40,"T4"));
       Thread t5=new Thread(new Talkative(50,"T5"));
       Thread t6=new Thread(new Talkative(60,"T6"));
       t1.start();
       t2.start();
       t3.start();
       t4.start();
       t5.start();
       t6.start();
    }
}