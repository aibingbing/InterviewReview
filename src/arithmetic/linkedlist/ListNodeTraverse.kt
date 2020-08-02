package arithmetic.linkedlist


/***
 * 构建无环链表
 */
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

/***
 * 遍历链表
 */
private fun listNodeTraverse(root: ListNode?) {
    if (root == null)
        return
    var parent = root
    while (parent != null) {
        print("${parent.value} ")
        parent = parent.next
    }
}

/***
 * 查询节点
 */
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

/***
 * 找出倒数第K个节点-递归法
 */
var num: Int = 0
private fun findKthTail(head: ListNode?, k: Int): ListNode? {
    num = k
    if (head == null) {
        return null
    }
    //递归调用
    val currentNode = findKthTail(head.next, k)
    if (currentNode != null) {
        return currentNode
    } else {
        num--
        if (num == 0) {
            return head
        }
        return null
    }
}

/***
 * 删除节点
 */
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

/***
 * 链表逆序
 */
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

/***
 * 寻找中间元素-快慢指针
 */
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

/***
 * 是否有环
 */
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

/***
 * 定位环入口-找到环中相遇的节点
 */
private fun getMeetingNode(head: ListNode?): ListNode? {
    if (head?.next == null)
        return null
    //慢指针，每次前进一个节点
    var fast: ListNode?
    //快指针，每次前进2个节点
    var slow: ListNode?
    //两个指针均指向链表头节点
    fast = head
    slow = head
    while (slow != null && fast?.next != null) {
        //慢指针前进一个节点
        slow = slow.next
        //快指针前进两个节点
        fast = fast.next?.next
        //若两个指针相遇，且均不为NULL则存在环
        if (fast == slow) {
            return slow
        }
    }
    return null
}

/***
 * 定位环入口-找出环的入口节点
 */
private fun getEntryNodeOfLoop(head: ListNode?): ListNode? {
    val meetingNode = getMeetingNode(head) ?: return null
    var p1 = meetingNode
    var p2 = head
    while (p1 != p2) {
        p1 = p1.next!!
        p2 = p2?.next
    }
    return p1
}

/***
 * 定位环入口-构建环
 */
private fun buildCycleList(): ListNode? {
    val root = ListNode(0)
    var parent: ListNode? = root
    val entryNodeIndex = 8
    var entryNode: ListNode? = null

    for (i in 1 until 20) {
        val node = ListNode(i)
        parent?.next = node
        parent = node
        if (i == entryNodeIndex) {
            entryNode = node
        }
    }

    val cycleNode = ListNode(21)
    var child: ListNode? = cycleNode
    for (i in 22 until 30) {
        val node = ListNode(i)
        child?.next = node
        child = node
    }
    //构建环
    parent?.next = cycleNode
    child?.next = entryNode
    return root
}

/***
 * 计算环的长度
 */
private fun getLoopLength(head: ListNode?): Int {
    if (head == null) return 0
    var slow: ListNode? = head
    var fast: ListNode? = head
    while (fast?.next != null) {
        slow = slow?.next
        fast = fast?.next?.next
        //第一次相遇
        if (slow == fast) {
            break
        }
    }
    //slow与fast继续前进
    slow = slow?.next
    fast = fast?.next?.next
    var lenth = 1
    while (slow != fast) {//再次相遇
        slow = slow?.next
        fast = fast?.next?.next
        lenth++
    }
    //当slow与fast再次相遇，得到环长度
    return lenth
}

/***
 * 合并2个有序的链表-递归方式
 */
private fun mergeTwoOrderedLists(head1: ListNode?, head2: ListNode?): ListNode? {
    var newHead: ListNode? = null
    when {
        null == head1 -> {
            return head2
        }
        null == head2 -> {
            return head1
        }
        else -> {
            if (head1.value ?: 0 < head2.value ?: 0) {
                newHead = head1
                newHead.next = mergeTwoOrderedLists(head1.next, head2)
            } else {
                newHead = head2
                newHead.next = mergeTwoOrderedLists(head1, head2.next)
            }
            return newHead
        }
    }
}

/***
 * 从尾部开始打印-递归法
 */
private fun printNodeListFromTailToHead(head: ListNode?): ListNode? {
    if (head == null) {
        return null
    }
    //递归调用
    val currentNode = printNodeListFromTailToHead(head.next)
    return if (currentNode != null) {
        currentNode
    } else {
        print("${head.value} ")
        null
    }
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

    val cycleRoot = buildCycleList()
    println("hasCycle:${hasCycle(cycleRoot)}")
    val entryNodeList = getEntryNodeOfLoop(cycleRoot)
    println("环的入口节点:${entryNodeList?.value}")
    val cycleLength = getLoopLength(cycleRoot)
    println("环的长度：${cycleLength}")

    val kthNode = findKthTail(root, 6)
    println("倒数第K个节点：" + kthNode?.value)

    println("从尾部开始打印，先进后出(递归法)：")
    printNodeListFromTailToHead(buildListNode())
}