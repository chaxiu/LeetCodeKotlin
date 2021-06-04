package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode
import java.util.*
import kotlin.collections.ArrayDeque

/**
 * @Author: zhutao
 * @datetime: 2021/6/4
 * @desc:
 */
object Easy_144_BinaryTreePreorder {
    fun preorderTraversal(root: TreeNode?): List<Int> {
        val list = mutableListOf<Int>()

        // doTraversal(root, list)
        // doTraversalLoop(root, list)
        traversal(root, list)  // recommended!!!
        return list
    }


    private fun traversal(root: TreeNode?, list: MutableList<Int>) {
        if (root == null) return

        val stk = LinkedList<TreeNode?>()
        stk.addLast(root)

        var node: TreeNode? = null

        while (!stk.isEmpty()) {
            node = stk.getLast() // peek

            // the same with if else, but no more null check
            node?.let {
                // always remove first
                stk.removeLast()

                if (it.right != null) stk.addLast(it.right) // right
                if (it.left != null) stk.addLast(it.left) // left

                stk.addLast(it) // root
                stk.addLast(null)
            }?: run {
                stk.removeLast()
                stk.removeLast()?.apply{
                    list.add(value)
                }
            }
        }
    }

    private fun doTraversalLoop(root: TreeNode?, list: MutableList<Int>) {
        if (root == null) return
        val stk = ArrayDeque<TreeNode>()

        var node: TreeNode? = null
        stk.addLast(root)

        while (!stk.isEmpty()) {
            node = stk.removeLast()
            list.add(node.value)

            // here is the key, add right node then left node
            node.right?.let {
                stk.addLast(it)
            }


            node.left?.let {
                stk.addLast(it)
            }
        }

        return
    }

    private fun doTraversal(root: TreeNode?, list: MutableList<Int>) {
        if (root == null) return

        list.add(root.value)
        doTraversal(root.left, list)
        doTraversal(root.right, list)
    }
}