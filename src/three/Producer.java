package three;

import java.util.Random;

class Producer implements Runnable {
    private final Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            while (true) {
                buffer.produce(random.nextInt(100));
                Thread.sleep(499);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
