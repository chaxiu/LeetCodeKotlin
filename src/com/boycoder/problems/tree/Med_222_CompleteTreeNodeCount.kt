package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_222_CompleteTreeNodeCount {
    fun countNodes(root: TreeNode?): Int {
        return count(root)
    }

    private fun count(root: TreeNode?): Int {
        if (root == null) return 0

        val left = count(root.left)
        val right = count(root.right)

        return left + right + 1
    }
}