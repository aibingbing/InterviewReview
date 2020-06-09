package arithmetic.sort

import arithmetic.printSortResult

/**
 * 归并排序
 */
fun mergeSort(array: Array<Int>) {
    val temp = Array<Int>(array.size) { 0 }
    internalMergeSort(array, temp, 0, array.size - 1)
}

private fun internalMergeSort(arr: Array<Int>, temp: Array<Int>, left: Int, right: Int) {
    // 当left == right时，不需要再划分
    if (left < right) {
        val mid = (left + right) / 2
        internalMergeSort(arr, temp, left, mid)
        internalMergeSort(arr, temp, mid + 1, right)
        mergeSortedArray(arr, temp, left, mid, right)
    }
}

private fun mergeSortedArray(array: Array<Int>, temp: Array<Int>, left: Int, mid: Int, right: Int) {
    var i = left
    var j = mid + 1
    var k = 0
    while (i <= mid && j <= right) {
        temp[k++] = if (array[i] < array[j]) array[i++] else array[j++]
    }
    while (i <= mid) {
        temp[k++] = array[i++]
    }
    while (j <= right) {
        temp[k++] = array[j++]
    }
    for (m in 0 until k) {
        array[left + m] = temp[m]
    }
}

fun main() {
    val testData = arrayOf(1000, 3, 6, 12, 0, 333, 222, 87, 6, 23, 2, -2)
    mergeSort(testData)
    printSortResult(testData)
}