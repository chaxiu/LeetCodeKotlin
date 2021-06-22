package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_110_BalancedBinaryTree {
    fun isBalanced(root: TreeNode?): Boolean {
        val depth = getDepth(root)
        return depth != -1
    }

    // use recursion post order
    // count every left depth and right depth
    // check if abs(left - right) > 1, if so return -1, means not balanced
    private fun getDepth(root: TreeNode?): Int {
        if (root == null) return 0

        val left = getDepth(root.left)
        val right = getDepth(root.right)

        if (left == -1 || right == -1) return -1

        if (Math.abs(left - right) > 1) return -1

        return Math.max(left, right) + 1
    }
}