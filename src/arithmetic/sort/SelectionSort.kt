package arithmetic.sort

import arithmetic.printSortResult
import arithmetic.swap

/**
 * 选择排序
 */
fun selectionSort(array: Array<Int>) {
    for (i in array.indices) {
        for (j in i + 1 until array.size) {
            if (array[j] < array[i]) {
                swap(array, i, j)
            }
        }
    }
}

fun main() {
    val testData = arrayOf(1000, 3, 6, 12, 0, 333, 222, 87, 6, 23, 2, -2)
    selectionSort(testData)
    printSortResult(testData)
}