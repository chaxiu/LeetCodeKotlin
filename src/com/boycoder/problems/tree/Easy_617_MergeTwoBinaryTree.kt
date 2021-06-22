package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_617_MergeTwoBinaryTree {
    fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
        return doMerge(root1, root2)
    }

    private fun doMerge(root1: TreeNode?, root2: TreeNode?): TreeNode? {
        if (root1 == null && root2 == null) {
            return null
        } else if (root1 == null && root2 != null) {
            return root2
        } else if (root1 != null && root2 == null) {
            return root1
        } else if (root1 != null && root2 != null) {
            root1.value = root1.value + root2.value
            root1.left = doMerge(root1.left, root2.left)
            root1.right = doMerge(root1.right, root2.right)
            return root1
        }

        // can not reach here
        return null
    }
}