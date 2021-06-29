package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode
import java.util.*

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_617_MergeTwoBinaryTree {
    fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
        return doMerge1(root1, root2)
    }

    /**
     * loop + stack
     */
    private fun doMerge1(root1: TreeNode?, root2: TreeNode?): TreeNode? {
        if (root1 == null) {
            return root2
        }

        if (root2 == null) {
            return root1
        }

        val stk = LinkedList<TreeNode>()
        stk.addLast(root2)
        stk.addLast(root1)

        while (!stk.isEmpty()) {
            val node1 = stk.removeLast()
            val node2 = stk.removeLast()
            node1.value = node1.value + node2.value

            if (node1.left != null && node2.left != null) {
                stk.addLast(node2.left)
                stk.addLast(node1.left)
            }

            if (node1.right != null && node2.right != null) {
                stk.addLast(node2.right)
                stk.addLast(node1.right)
            }

            if (node1.left == null && node2.left != null) {
                node1.left = node2.left
            }

            if (node1.right == null && node2.right != null) {
                node1.right = node2.right
            }
        }

        return root1
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