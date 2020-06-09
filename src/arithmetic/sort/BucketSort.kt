package arithmetic.sort

import arithmetic.printSortResult

/**
 * 桶排序
 */
fun bucketSort(array: Array<Int>) {
    var max = Integer.MIN_VALUE
    var min = Integer.MAX_VALUE
    for (i in array) {
        max = kotlin.math.max(i, max)
        min = kotlin.math.min(i, min)
    }
    //桶数
    val bucketNum = (max - min) / array.size + 1
    val bucketArray = Array<MutableList<Int>>(bucketNum) { mutableListOf() }
    //将每个元素放入桶
    for (v in array) {
        val index = (v - min) / array.size
        bucketArray[index].add(v)
    }
    //对每个桶进行排序
    var index = 0
    for (i in bucketArray) {
        i.sort()
        for (j in i) {
            array[index++] = j
        }
    }
}

fun main() {
    val testData = arrayOf(1000, 3, 6, 12, 0, 333, 222, 87, 6, 23, 2, -2)
    bucketSort(testData)
    printSortResult(testData)
}