package arithmetic.sort

import arithmetic.printSortResult

/**
 * 计数排序（线性排序）,最小值不能有负数
 */
fun countingSort(array: Array<Int>) {
    var max = Integer.MIN_VALUE
    var min = Integer.MAX_VALUE
    for (i in array) {
        max = kotlin.math.max(max, i)
        min = kotlin.math.min(min, i)
    }

    val countArray = Array<Int>(max - min + 1) { 0 }
    for (v in array) {
        countArray[v - min]++
    }
    var index = 0
    for ((i, v) in countArray.withIndex()) {
        if (v > 0) {
            for (count in 1..v) {
                array[index++] = i + min
            }
        }
    }

}

fun main() {
    val testData = arrayOf(1000, 3, 6, 12, 0, 333, 222, 87, 6, 23, 2)
    countingSort(testData)
    printSortResult(testData)
}