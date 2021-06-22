package com.boycoder.problems.bst

import com.boycoder.basis.datastructure.TreeNode
import java.util.*

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_538_BSTToGreaterTree {
    var pre: TreeNode? = null
    fun convertBST(root: TreeNode?): TreeNode? {
        return conver(root)
    }

    // loop, treat this tree as normal binary tree
    private fun conver(root: TreeNode?): TreeNode? {
        if (root == null) return null

        val stk = LinkedList<TreeNode?>()
        stk.addLast(root)

        while (!stk.isEmpty()) {
            val node = stk.getLast()

            if (node != null) {
                // reversed inorder traversal
                stk.removeLast()

                if (node.left != null) stk.addLast(node.left)

                stk.addLast(node)
                stk.addLast(null)

                if (node.right != null) stk.addLast(node.right)
            } else {
                stk.removeLast() // remove null

                // traversal element
                stk.removeLast()?.let {
                    if (pre != null) {
                        it.value = pre!!.value + it.value
                    }
                    pre = it
                }
            }
        }

        return root
    }


    // recursion
    private fun conver1(root: TreeNode?): TreeNode? {
        if (root == null) return root

        conver(root.right)
        if (pre != null) {
            root.value = pre!!.value + root.value
        }
        pre = root
        conver(root.left)

        return root
    }
}