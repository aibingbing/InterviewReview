package arithmetic.stack

import java.util.*

class StackQueue {
    private val outStack: Stack<Int> = Stack()
    private val inStack: Stack<Int> = Stack()

    private fun in2OutStack() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop())
        }
    }

    fun push(element: Int) {
        inStack.push(element)
    }

    fun pop(): Int {
        if (outStack.isEmpty()) {
            in2OutStack()
        }
        return outStack.pop()
    }

    fun top(): Int {
        if (outStack.isEmpty()) {
            in2OutStack()
        }
        return outStack.peek()
    }
}