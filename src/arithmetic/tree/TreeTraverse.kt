package arithmetic.tree

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max
import kotlin.math.min

/**
 * 先序遍历: 根->左->右
 */
private fun preOrder(root: TreeNode?) {
    root?.run {
        print("$value ")
        preOrder(left)
        preOrder(right)
    }
}

/**
 * 中序遍历: 左->根->右
 */
private fun middleOrder(root: TreeNode?) {
    root?.run {
        middleOrder(left)
        print("$value ")
        middleOrder(right)
    }
}

/**
 * 后序遍历: 左->右->根
 */
private fun postOrder(root: TreeNode?) {
    root?.run {
        postOrder(left)
        postOrder(right)
        print("$value ")
    }
}

/**
 * 层次遍历，DFS深度优先
 */
private fun levelOrderDFS(root: TreeNode) {
    val res = ArrayList<ArrayList<Int>>()
    dfs(root, res, 0)
    //打印结果
//    res.forEach {
//        it.forEach { it ->
//            print("$it ")
//        }
//    }
}

private fun dfs(root: TreeNode?, res: ArrayList<ArrayList<Int>>, level: Int) {
    root?.run {
        if (level == res.size) {
            res.add(ArrayList<Int>())
        }
        root.value?.also {
            res[level].add(it)
            print("$it ")
        }
        dfs(root.left, res, level + 1)
        dfs(root.right, res, level + 1)
    }
}

/**
 * 层次遍历，BFS广度优先
 */
private fun levelOrderBFS(root: TreeNode) {
    val res = ArrayList<ArrayList<Int>>()
    val queue = LinkedList<TreeNode?>()
    queue.offer(root)

    while (queue.isNotEmpty()) {
        val level = ArrayList<Int>()
        val size = queue.size
        for (i in 0..size) {
            val head = queue.poll()
            head?.value?.let {
                level.add(it)
                print("$it ")
            }
            head?.left?.let {
                queue.offer(it)
            }
            head?.right?.let {
                queue.offer(it)
            }
        }
        res.add(level)
    }

    //打印结果
//    res.forEach {
//        it.forEach { it ->
//            print("$it ")
//        }
//    }
}

/**
 * 遍历找出最大值
 */
private fun getMaxValue(root: TreeNode?): Int {
    if (root == null)
        return Integer.MIN_VALUE
    val left = getMaxValue(root.left)
    val right = getMaxValue(root.right)
    return max(max(left, right), root.value ?: 0)
}

/**
 * 遍历找出最大深度
 */
private fun getMaxDepth(root: TreeNode?): Int {
    if (root == null)
        return 0
    val left = getMaxDepth(root.left)
    val right = getMaxDepth(root.right)
    return max(left, right) + 1
}

/**
 * 遍历找出最小深度
 */
private fun getMinDepth(root: TreeNode?): Int {
    if (root == null)
        return 0
    val left = getMinDepth(root.left)
    val right = getMinDepth(root.right)
    return when {
        left == 0 -> {
            right + 1
        }
        right == 0 -> {
            left + 1
        }
        else -> {
            min(left, right) + 1
        }
    }
}

private fun buildNode(parent: TreeNode, value: LinkedList<Int>) {
    if (value.size < 2)
        return
    val leftNode = TreeNode(value.poll())
    val rightNode = TreeNode(value.poll())
    parent.left = leftNode
    parent.right = rightNode
    buildNode(parent.left!!, value)
    buildNode(parent.right!!, value)
}

fun main() {
    //构建root二叉树
    val valueQueue = LinkedList<Int>()
    for (i in 1..50) {
        valueQueue.offer(i)
    }
    val root = TreeNode(0)
    buildNode(root, valueQueue)
    //先序遍历
    println("先序遍历：")
    preOrder(root)
    println()
    //中序遍历
    println("中序遍历：")
    middleOrder(root)
    println()
    //后序遍历
    println("后序遍历：")
    postOrder(root)
    println()
    println("层次遍历(DFS深度优先)：")
    levelOrderDFS(root)
    println()
    println("层次遍历(BFS广度优先)：")
    levelOrderBFS(root)
    println()
    println("最大值：" + getMaxValue(root))
    println("最大深度：" + getMaxDepth(root))
    println("最小深度：" + getMinDepth(root))
}