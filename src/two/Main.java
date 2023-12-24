package two;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static volatile int time = 0;
    public static void main(String[] args) {
        EverySecond everySecond = new EverySecond();
        FiveMessage fiveMessage = new FiveMessage();
        SevenMessage sevenMessage = new SevenMessage();

        everySecond.start();
        fiveMessage.start();
        sevenMessage.start();
    }

    static class EverySecond extends Thread {
        @Override
        public void run() {
            while (true) {
                time++;
                try {
                    System.out.println(time + " сек");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    static class FiveMessage extends Thread {
        @Override
        public void run(){
            while (true){
                if(time % 5 == 0 && time != 0) {
                    System.out.println("Сообщение повторяющееся каждые 5 секунд!" + time % 5);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }


        }
    }

    static class SevenMessage extends Thread {
        @Override
        public void run(){
            while (true){
                if(time % 7 == 0 && time != 0) {
                    System.out.println("Сообщение повторяющееся каждые 7 секунд!" + time % 7);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
