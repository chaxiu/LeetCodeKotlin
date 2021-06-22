package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode
import java.util.*
import kotlin.collections.ArrayDeque

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_94_BinaryTreeInorderTraversal {
    fun inorderTraversal(root: TreeNode?): List<Int> {
        val list = mutableListOf<Int>()
        if (root == null) return list

        val stk = LinkedList<TreeNode?>()
        stk.addLast(root)
        var node: TreeNode? = null

        while (!stk.isEmpty()) {
            node = stk.getLast()

            // the same with the if else, but no more null check
            node?.let {
                // always remove first
                stk.removeLast()

                if (it.right != null) stk.addLast(it.right) // right

                stk.addLast(it) // root
                stk.addLast(null)

                if (it.left != null) stk.addLast(it.left) // left
            }?: run {
                stk.removeLast()
                stk.removeLast()?.apply {
                    list.add(value)
                }
            }
        }

        return list
    }


    fun inorderTraversal1(root: TreeNode?): List<Int> {
        val list = mutableListOf<Int>()
        val stk = ArrayDeque<TreeNode>()

        var node: TreeNode? = root

        while (node != null || !stk.isEmpty()) {
            if (node != null) {
                stk.addLast(node)
                node = node.left
            } else {
                // really traversal
                node = stk.removeLast()
                list.add(node.value)

                node = node.right
            }
        }
        return list
    }
}