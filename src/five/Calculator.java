package five;

import java.util.concurrent.CountDownLatch;

public class Calculator extends Thread {
    private static int[][] result = new int[3][3];
    private final int i;
    private final int j;
    private final CountDownLatch countDownLatch;
    private final int[] row;
    private final int[] column;

    public Calculator(int[] row, int i, int[] column, int j, CountDownLatch size) {
        this.row = row;
        this.i = i;
        this.column = column;
        this.j = j;
        this.countDownLatch = size;
    }

    public static void printResultMatrix() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void run() {
        int value = 0;
        for (int k = 0; k < 3; k++) {
            value += row[k] * column[k];
        }
        Calculator.result[i][j] = value;
        countDownLatch.countDown();
    }
}
