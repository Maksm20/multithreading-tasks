package five;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    static int x1 = 3;
    static int y1 = 3;
    static int x2 = 3;
    static int y2 = 3;
    static int[][] matrix1 = new int[x1][y1];
    static int[][] matrix2 = new int[x2][y2];
    static int[][] matrix2T = new int[y2][x2];
    public static void main(String[] args) throws InterruptedException {
        int counter = 0;
        for(int i = 0; i < x1; i++) {
            for(int j = 0; j < y1; j++) {
                matrix1[i][j] = counter;
                counter++;
            }
        }
        for(int i = 0; i < x2; i++) {
            for(int j = 0; j < y2; j++) {
                matrix2[i][j] = counter;
                counter++;
            }
        }
        for(int i = 0; i < x2; i++) {
            for(int j = 0; j < y2; j++) {
                matrix2T[i][j] = matrix2[j][i];
            }
        }

        int size = x1 * y2; //Кол-во потоков
        CountDownLatch countDownLatch = new CountDownLatch(size);
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        for (int i = 0; i < x1; i++) {
            for (int j = 0; j < y2; j++) {
                executorService.submit(new Calculator(matrix1[i], i, matrix2T[j], j, countDownLatch));
            }
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        countDownLatch.await();
        Calculator.printResultMatrix();
    }
}




