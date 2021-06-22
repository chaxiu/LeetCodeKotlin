package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_112_PathSum {
    fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
        if (root == null) return false
        return hasSum(root, targetSum)
    }

    private fun hasSum(root: TreeNode, targetSum: Int): Boolean {
        if (root.left == null && root.right == null) {
            return targetSum == root.value
        }

        var flag = false
        val target = targetSum - root.value
        if (root.left != null) {
            flag = flag or hasSum(root.left!!, target)
        }

        if (root.right != null) {
            flag = flag or hasSum(root.right!!, target)
        }
        return flag
    }
}