package thread.sync;

public class SyncTest {
    public static void main(String[] args) {
        ClassA a = new ClassA();
        Thread threadA = new Thread(){
            @Override
            public void run() {
                a.objectMethod();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread threadB = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ClassA.staticMethod();
            }
        };
        threadB.start();
        threadA.start();
    }
}
