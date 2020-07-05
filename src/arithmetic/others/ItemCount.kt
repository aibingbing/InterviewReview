package arithmetic.others

/**
一个长度大小为N的数组，数组中的每个元素的取值范围在[1，N]，且为正整数。
问：如何在时间复杂度为O(N)，空间复杂度为O(1)的条件下，统计数组中不同元素出现的次数。

思路一：排序 -- 时间复杂度O(nlogn)
思路二：哈希表 -- 时间复杂度O(1) 空间复杂度O(n)
思路三：值为下标 -- 时间复杂度O(n) 空间复杂度O(1)
 **/

fun work(arr: IntArray, n: Int): Unit {
    var index = 0
    while (index < n) {
        //因为数组都是从0开始的，所以arr[index]得减1才可以找到对应的元素，否则会数组越界
        val temp = arr[index] - 1
        if (temp < 0) {
            index++
            continue
        }
        if (arr[temp] > 0) {
            arr[index] = arr[temp]
            arr[temp] = -1
        } else {
            arr[temp]--
            arr[index] = 0
        }
    }
}


fun main(args: Array<String>) {
    val arr = intArrayOf(2, 5, 5, 2, 3)
    work(arr, arr.size)
    arr.forEachIndexed { index, i ->
        print(i)
        if (index != arr.size - 1) print(",")
    }
    var index = 1
    for (countResult in arr) {
        println(index++.toString() + "出现了" + -1 * countResult + "次")
    }
}
