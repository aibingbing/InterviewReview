package arithmetic.stack

import java.util.*


/***
 * 给定一个字符串所表示的括号序列，包含以下字符： '(', ')', '{', '}', '[' and ']'，
 * 判定是否是有效的括号序列。括号必须依照 "()" 顺序表示，
 * "()[]{}" 是有效的括号，但 "([)]" 则是无效的括号。
 */
fun isValidParentheses(s: String): Boolean {
    val stack = Stack<Char>()
    for (c in s.toCharArray()) {
        if ("({[".contains(c.toString())) {
            stack.push(c)
        } else {
            if (!stack.isEmpty() && isValid(stack.peek(), c)) {
                stack.pop()
            } else {
                return false
            }
        }
    }
    return stack.isEmpty()
}

fun isValid(c1: Char, c2: Char): Boolean {
    return (c1 == '(' && c2 == ')' || c1 == '{' && c2 == '}'
            || c1 == '[' && c2 == ']')
}

fun main(args: Array<String>) {
    println(isValidParentheses("{{[()]}}"))
    println(isValidParentheses("{{[()]}"))
    println(isValidParentheses("{{([)]}}"))
}