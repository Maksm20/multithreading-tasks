package seven;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

class ExecutorSchedulerService {
    private ExecutorService executorService;
    private List<Task> taskList = new ArrayList<>();
    private AtomicBoolean isRunning = new AtomicBoolean(false);

    public ExecutorSchedulerService(int threadPoolSize) {
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void start() {
        if (isRunning.compareAndSet(false, true)) {
            for (Task task : taskList) {
                executorService.submit(task::executeTask);
            }
        }
    }

    public void stop() {
        if (isRunning.compareAndSet(true, false)) {
            executorService.shutdown();
        }
    }
}
