package arithmetic.linkedlist

private fun buildListNode(): ListNode? {
    val root = ListNode(0)
    var parent: ListNode? = root

    for (i in 1 until 20) {
        val node = ListNode(i)
        parent?.next = node
        parent = node
    }
    return root
}

private fun listNodeTraverse(root: ListNode?) {
    if (root == null)
        return
    var parent = root
    while (parent != null) {
        print("${parent.value} ")
        parent = parent.next
    }
}

private fun searchNode(root: ListNode?, value: Int): ListNode? {
    if (root == null)
        return root
    var head = root
    while (head != null) {
        if (value == head.value)
            return head
        head = head.next
    }
    return null
}

private fun deleteNode(node: ListNode?) {
    var head = node
    if (head?.next == null) {
        head = null
        return
    }
    //取缔下一个节点
    head.value = head.next?.value
    head.next = head.next?.next
}

private fun reverseListNode(head: ListNode?): ListNode? {
    var prev: ListNode? = null
    var node = head
    while (node != null) {
        val temp = node.next
        node.next = prev
        prev = node
        node = temp
    }
    return prev
}

private fun findMiddleValue(head: ListNode?): ListNode? {
    if (head == null) {
        return head
    }
    //快慢指针
    var slow = head
    var fast = head

    //fast=null 表示是链表的最后一个节点
    //fast!=null && fast.next!=null
    while (fast?.next != null) {
        fast = fast.next?.next
        slow = slow?.next
    }
    return slow
}

private fun hasCycle(head: ListNode?): Boolean {
    if (head?.next == null) {
        return false
    }
    var slow = head
    var fast = head.next
    while (fast != slow) {
        if (fast?.next == null) {
            return false
        }
        fast = fast.next?.next
        slow = slow?.next
    }
    return true
}

fun main() {
    //构建链表
    var root = buildListNode()
    //遍历链表
    listNodeTraverse(root)
    println()
    //查找value==10的node
    val node10 = searchNode(root, 10)
    println("value==10的node:$node10")
    if (node10 != null) {
        //删除value==10的节点
        println("删除value==10的node:")
        deleteNode(node10)
        listNodeTraverse(root)
    }
    println()
    println("翻转链表：")
    root = reverseListNode(root)
    listNodeTraverse(root)
    println()
    println("中间元素：${findMiddleValue(root)?.value}")
    println("hasCycle:${hasCycle(root)}")
}