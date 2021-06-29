package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode
import java.util.*

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_222_CompleteTreeNodeCount {
    fun countNodes(root: TreeNode?): Int {
        return count1(root)
    }

    private fun count1(root: TreeNode?): Int {
        if (root == null) return 0

        var count = 0
        val queue = LinkedList<TreeNode>()
        queue.addLast(root)

        while (!queue.isEmpty()) {
            val node = queue.removeFirst()
            count++

            node.left?.let {
                queue.addLast(it)
            }

            node.right?.let {
                queue.addLast(it)
            }
        }

        return count
    }

    /**
     * post order
     */
    private fun count(root: TreeNode?): Int {
        if (root == null) return 0

        val left = count(root.left)
        val right = count(root.right)

        return left + right + 1
    }
}