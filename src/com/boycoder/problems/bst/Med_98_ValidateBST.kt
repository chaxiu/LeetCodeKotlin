package com.boycoder.problems.bst

import com.boycoder.basis.datastructure.TreeNode
import java.util.*

/**
 * @Author: zhutao
 * @datetime: 2021/6/4
 * @desc:
 */
object Med_98_ValidateBST {
    fun isValidBST(root: TreeNode?): Boolean {
        return isValid(root)
    }

    // in order traversal and check if is sorted
    private fun isValid(root: TreeNode?): Boolean {
        if (root == null) return true

        val stk = LinkedList<TreeNode?>()
        stk.addLast(root)
        var node: TreeNode? = null
        val list = mutableListOf<Int>()
        var pre: TreeNode? = null

        while (!stk.isEmpty()) {
            node = stk.getLast() // peek

            if (node != null) {
                node = stk.removeLast()
                // in order

                node?.right?.let {
                    stk.addLast(it)
                }


                stk.addLast(node)
                stk.addLast(null)

                node?.left?.let {
                    stk.addLast(it)
                }
            } else {
                stk.removeLast()
                node = stk.removeLast()?:continue

                if (pre != null && pre.value >= node.value) {
                    return false
                }
                pre = node
            }
        }

        return true
    }

    // in order traversal and check if is sorted
    private fun isValid1(root: TreeNode?): Boolean {
        if (root == null) return true

        val stk = LinkedList<TreeNode?>()
        stk.addLast(root)
        var node: TreeNode? = null
        val list = mutableListOf<Int>()

        while (!stk.isEmpty()) {
            node = stk.getLast() // peek

            if (node != null) {
                node = stk.removeLast()
                // in order

                node?.right?.let {
                    stk.addLast(it)
                }


                stk.addLast(node)
                stk.addLast(null)

                node?.left?.let {
                    stk.addLast(it)
                }
            } else {
                stk.removeLast()
                node = stk.removeLast()

                node?.let {
                    list.add(it.value)
                }
            }
        }

        // traversal and check
        var pre = list[0]
        for (i in 1..(list.size - 1)) {
            if (pre >= list[i]) {
                return false
            }
            pre = list[i]
        }

        return true
    }
}