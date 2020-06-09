package thread;

public class Runner implements Runnable {
    public String name;

    public Runner(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("--thread name: " + this.name);
    }
}
