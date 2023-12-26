package seven;

class Task {
    private String name;
    private String status;
    //
    public Task(String name) {
        this.name = name;
        this.status = "Не выполняется";
    }

    public String getName() {
        return name;
    }

    public void executeTask() {
        System.out.println("Выполнение задачи: " + name);
        this.status = "Выполняется";
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Task newTask = new Task(name);
        System.out.println(Main.taskList);
        System.out.println("Задача выполнена: " + name);
        this.status = "Выполнен";
    }

    public void viewTask()
    {
        System.out.println("Статус задачи " + name + ": " + status);
    }
}
