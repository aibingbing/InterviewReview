package arithmetic

fun main() {
    println("Hello,World.")
}

fun printSortResult(array: Array<Int>) {
    for (i in array)
        print("$i ")
}

fun swap(array: Array<Int>, i: Int, j: Int) {
    val temp = array[i]
    array[i] = array[j]
    array[j] = temp
}