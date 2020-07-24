package arithmetic.others;


import static arithmetic.others.SingleNumberTest.singleNumber;

/***
 * 给出 2 * n + 1个数字，除其中一个数字之外其他每个数字均出现两次，找到这个数字。
 *
 * 异或运算具有很好的性质，相同数字异或运算后为0，并且具有交换律和结合律，故将所有数字异或运算后即可得到只出现一次的数字。
 */

public class BinaryOperation {
    public static void main(String[] args) {
        int[] testData = new int[]{2, 3, 4, 5, 3, 4, 2};
        System.out.println("单独的数字为：" + singleNumber(testData));
    }
}
