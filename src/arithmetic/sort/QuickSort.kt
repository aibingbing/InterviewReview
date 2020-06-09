package arithmetic.sort

import arithmetic.printSortResult
import arithmetic.swap

/**
 * 快速排序
 */
fun quickSort(array: Array<Int>) {
    quickSort(array, 0, array.size - 1)
}

private fun quickSort(array: Array<Int>, low: Int, high: Int) {
    if (low >= high)
        return
    val pivot = partition(array, low, high)
    quickSort(array, low, pivot - 1)
    quickSort(array, pivot + 1, high)
}

private fun partition(array: Array<Int>, low: Int, high: Int): Int {
    val pivot = low
    var index = pivot + 1
    for (i in index..high) {
        if (array[i] < array[pivot]) {
            swap(array, i, index)
            index++
        }
    }
    swap(array, pivot, index - 1)
    return index - 1
}

fun main() {
    val testData = arrayOf(1000, 3, 6, 12, 0, 333, 222, 87, 6, 23, 2, -2)
    quickSort(testData)
    printSortResult(testData)
}