package thread.join;

import thread.Runner;

public class ExecuteInOrderTest {
    public static void main(String[] args) throws InterruptedException {
       useJoin();
    }

    private static void useJoin() throws InterruptedException{
      Thread t1 = new Thread(new Runner("A"));
      Thread t2 = new Thread(new Runner("B"));
      Thread t3 = new Thread(new Runner("C"));

      t1.start();
      t1.join();
      t2.start();
      t2.join();
      t3.start();
      t3.join();
    }
}
