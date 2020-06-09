package thread.sync;

public class ClassA {
    public static synchronized void staticMethod() {
        System.out.println("static method");
    }

    public void objectMethod() {
        synchronized (this) {
            System.out.println("object method");
        }
    }
}
