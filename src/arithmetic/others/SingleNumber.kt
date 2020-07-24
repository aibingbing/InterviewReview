@file:JvmName("SingleNumberTest")

package arithmetic.others

/***
 * 测试在java中引用kotlin中的方法
 */
fun singleNumber(a: IntArray?): Int {
    if (a == null || a.isEmpty()) {
        return -1
    }
    var rst = 0
    for (i in a.indices) {
        rst = rst xor a[i]
    }
    return rst
}