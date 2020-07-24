package generic;

import java.util.ArrayList;
import java.util.List;

public class GenericTest {
    public static void main(String[] args) {
        List<String> stringArrayList = new ArrayList<String>();
        List<Integer> integerArrayList = new ArrayList<Integer>();

        Class classStringArrayList = stringArrayList.getClass();
        Class classIntegerArrayList = integerArrayList.getClass();

        System.out.println(stringArrayList.getClass().toGenericString());
        System.out.println(classIntegerArrayList.getTypeName());
        if (classStringArrayList.equals(classIntegerArrayList)) {
            System.out.println("泛型测试:" + "类型相同");
        }

        printMsg("111",222,"aaaa","2323.4",55.55);
    }

    public static <T> void printMsg(T... args) {
        for (T t : args) {
            System.out.println("泛型测试" + "t is " + t);
        }
    }
}
