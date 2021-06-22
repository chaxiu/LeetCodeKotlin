package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode
import java.util.*

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_145_BinaryTreePostorderTraversal {
    fun postorderTraversal(root: TreeNode?): List<Int> {
        val list = mutableListOf<Int>()
        if (root == null) return list

        // Here we can not use ArrayDeque, it dose allow null element
        val stk: Deque<TreeNode?> = LinkedList<TreeNode?>()
        stk.addLast(root)
        var node: TreeNode? = null

        while (!stk.isEmpty()) {
            node = stk.getLast()

            if (node != null) {
                // always remove first
                node = stk.removeLast()

                stk.addLast(node)
                stk.addLast(null)

                if (node?.right != null) stk.addLast(node!!.right)
                if (node?.left != null) stk.addLast(node!!.left)
            } else {
                stk.removeLast()
                node = stk.removeLast()
                list.add(node!!.value)
            }
        }
        return list
    }
}