package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode
import java.util.*

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_404_SumOfLeftLeaves {
    var sum = 0

    fun sumOfLeftLeaves(root: TreeNode?): Int {
        sum(root)
        return sum
    }

    // loop pre order
    private fun sum(root: TreeNode?) {
        if (root == null) return

        val stk = LinkedList<TreeNode?>()
        stk.addLast(root)
        var node: TreeNode? = null

        while (!stk.isEmpty()) {
            node = stk.getLast() // peek

            if (node != null) {
                stk.removeLast()

                if (node.left != null) stk.addLast(node.left)
                if (node.right != null) stk.addLast(node.right)

                stk.addLast(node)
                stk.addLast(null)
            } else {
                stk.removeLast()
                stk.removeLast()?.let {
                    if (it.left != null && it.left!!.left == null && it.left!!.right == null) {
                        sum = sum + it.left!!.value
                    }
                }
            }
        }
    }

    private fun sum1(root: TreeNode?) {
        if (root == null) return

        if (root.left != null && root.left!!.left == null && root.left!!.right == null) {
            sum = sum + root.left!!.value
        }

        if (root.left != null) sum(root.left)
        if (root.right != null) sum(root.right)
    }
}