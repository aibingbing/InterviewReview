package arithmetic.stack

import java.util.*


/***
 * 实现一个栈, 额外支持一个操作：min() 返回栈中元素的最小值
 */
class MinStack {
    private var stack: Stack<Int>? = null
    private var minStack // 维护一个辅助栈，传入当前栈的最小值
            : Stack<Int>? = null

    constructor() {
        stack = Stack()
        minStack = Stack()
    }

    fun push(number: Int) {
        stack!!.push(number)
        if (minStack!!.isEmpty()) {
            minStack!!.push(number)
        } else {
            minStack!!.push(kotlin.math.min(number, minStack!!.peek()))
        }
    }

    fun pop(): Int {
        minStack!!.pop()
        return stack!!.pop()
    }

    fun min(): Int {
        return minStack!!.peek()
    }
}

fun main(args:Array<String>) {
    val minStack = MinStack()
    minStack.push(3)
    minStack.push(7)
    minStack.push(10)
    minStack.push(2)
    minStack.push(1)
    println("min value:"+minStack.min())
    minStack.pop()
    println("min value:"+minStack.min())
}