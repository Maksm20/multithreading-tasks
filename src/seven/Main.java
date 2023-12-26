package seven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static List<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Scanner scanner = new Scanner(System.in);
        int counter;
        boolean running = true;
        while(running) {
            System.out.println("Выберите пункт меню:\n1. Добавить задачу\n2. Удалить задачу\n3. Вывести задачи и их статус\n4. Завершение работы");
            counter = scanner.nextInt();
            switch (counter) {
                case (1): {
                    System.out.print("Введите название задачи: ");
                    String scan = scanner.next();
                    Task task = new Task(scan);
                    taskList.add(task);
                    executorService.submit(task :: executeTask);
                    break;
                }
                case(2): {
                    System.out.print("Введите название задачи: ");
                    String scan = scanner.next();
                    String taskName = new String(scan);
                    Task newTask = new Task(taskName);
                    if(Arrays.asList(taskList).contains(newTask)) {
                        taskList.remove(newTask);
                    }
                    //for(Task task : taskList) {
                    //    if(taskName.equals(task.getName())) {
                    //        taskList.remove(task);
                    //    }
                    //}
                    break;
                }
                case (3): {
                    for(int i = 0; i < taskList.size(); i++)
                    {
                        taskList.get(i).viewTask();
                    }
                    break;
                }
                case (4): {
                    running = false;
                }
            }
        }
    }
}


