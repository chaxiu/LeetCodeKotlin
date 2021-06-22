package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_104_MaxDepthBinaryTree {
    fun maxDepth(root: TreeNode?): Int {
        return getDepth(root)
    }

    // level traversal
    private fun getDepth(root: TreeNode?): Int {
        if (root == null) return 0

        val queue = ArrayDeque<TreeNode>()
        queue.addLast(root)
        var node: TreeNode? = null
        var depth = 0

        while (!queue.isEmpty()) {
            depth++
            val size = queue.size
            for (i in 0 until size) {
                node = queue.removeFirst()
                node.left?.let {
                    queue.addLast(it)
                }

                node.right?.let {
                    queue.addLast(it)
                }
            }
        }
        return depth
    }

    // recursion post order is preferred, pre order is not
    private fun getDepth1(root: TreeNode?): Int {
        if (root == null) return 0

        val left = getDepth(root.left)
        val right = getDepth(root.right)

        return 1 + Math.max(left, right)
    }
}