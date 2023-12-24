package three;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ProducerConsumer {
    private static Queue<Integer> buffer = new LinkedList<>();
    private static final int LIMIT = 20;

    public synchronized void produce() {
        while (buffer.size() == LIMIT) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Random random = new Random();
        int a = random.nextInt(5);
        buffer.add(a);
        System.out.println("Буфер после производства: " + buffer.size() + " " + a);
        notify();
    }

    public synchronized void consumer() {
        while (buffer.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Буфер после потребления: " + buffer.size() + " " + buffer.peek());
        buffer.poll();
        notify();
    }
}
