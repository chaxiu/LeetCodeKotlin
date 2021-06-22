package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode
import java.util.*
import kotlin.collections.ArrayDeque

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_226_InvertBinaryTree {
    fun invertTree(root: TreeNode?): TreeNode? {
        invert(root)
        return root
    }

    // loop with level order
    private fun invert(root: TreeNode?) {
        if (root == null) return

        val queue = ArrayDeque<TreeNode>()
        queue.addLast(root)

        while (!queue.isEmpty()) {
            val node = queue.removeFirst()

            val temp = node.left
            node.left = node.right
            node.right = temp

            if (node.left != null) queue.addLast(node.left!!)
            if (node.right != null) queue.addLast(node.right!!)
        }
    }

    // loop with post order
    private fun invert5(root: TreeNode?) {
        if (root == null) return

        val stk = LinkedList<TreeNode?>()
        stk.addLast(root)
        var node: TreeNode? = null

        while (!stk.isEmpty()) {
            node = stk.getLast()

            node?.let {
                stk.removeLast()
                stk.addLast(it)
                stk.addLast(null)

                if (it.right != null) stk.addLast(it.right)
                if (it.left != null) stk.addLast(it.left)
            }?: run {
                stk.removeLast()
                stk.removeLast()?.let {
                    val temp = it.left
                    it.left = it.right
                    it.right = temp
                }
            }
        }
    }

    // loop with pre order
    private fun invert4(root: TreeNode?) {
        if (root == null) return

        val stk = LinkedList<TreeNode?>()
        stk.addLast(root)
        var node: TreeNode? = null

        while (!stk.isEmpty()) {
            node = stk.getLast()

            node?.let {
                stk.removeLast()

                if (it.right != null) stk.addLast(it.right)
                if (it.left != null) stk.addLast(it.left)

                stk.addLast(it)
                stk.addLast(null)
            }?: run {
                stk.removeLast()
                stk.removeLast()?.let {
                    val temp = it.left
                    it.left = it.right
                    it.right = temp
                }
            }
        }
    }


    // recursion post order
    private fun invert3(root: TreeNode?) {
        if (root == null) return

        invert(root.left)
        invert(root.right)

        val temp = root.left
        root.left = root.right
        root.right = temp
    }

    // recursion inorder
    private fun invert2(root: TreeNode?) {
        if (root == null) return

        invert(root.left)

        val temp = root.left
        root.left = root.right
        root.right = temp


        invert(root.left)
    }

    // recursion pre order
    private fun invert1(root: TreeNode?) {
        if (root == null) return

        val temp = root.left
        root.left = root.right
        root.right = temp

        invert(root.left)
        invert(root.right)
    }
}