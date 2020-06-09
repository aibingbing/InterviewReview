package arithmetic.sort

import arithmetic.printSortResult
import arithmetic.swap

/**
 * 冒泡排序
 */
fun bubbleSort(array: Array<Int>) {
    for (i in array.size - 1 downTo 1) {
        for (j in 0 until i) {
            if (array[j] > array[j + 1]) {
                swap(array, j, j + 1)
            }
        }
    }
}

fun main() {
    val testData = arrayOf(1000, 3, 6, 12, 0, 333, 222, 87, 6, 23, 2, -2)
    bubbleSort(testData)
    printSortResult(testData)
}