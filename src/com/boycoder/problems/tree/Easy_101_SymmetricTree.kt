package com.boycoder.problems.tree

import com.boycoder.basis.datastructure.TreeNode
import java.util.*

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_101_SymmetricTree {
    fun isSymmetric(root: TreeNode?): Boolean {
        return isSym(root?.left, root?.right)
    }

    private fun isSym(node1: TreeNode?, node2: TreeNode?): Boolean {
        // case that need return
        if (node1 == null && node2 == null) {
            return true
        } else if (node1 != null && node2 == null) {
            return false
        } else if (node1 == null && node2 != null) {
            return false
        } else if (node1!!.value != node2!!.value) {
            return false
        } else {
            // node1 node2 not null then we need to compare with recursion
            val outSide = isSym(node1.left, node2.right)
            val inSide = isSym(node1.right, node2.left)
            return outSide && inSide
        }
    }

    private fun isSym1(root: TreeNode?): Boolean {
        if (root == null) return true

        val queue = LinkedList<TreeNode?>()
        queue.addLast(root)

        while (!queue.isEmpty()) {
            val size = queue.size

            val temps = mutableListOf<TreeNode?>()
            for (i in 0 until size) {
                val node = queue.removeFirst()
                if (node != null) {
                    temps.add(node.left)
                    temps.add(node.right)

                    queue.addLast(node.left)
                    queue.addLast(node.right)
                }
            }

            // for every level, we check the level Symmetric
            if (!isListSym(temps)) {
                return false
            }
        }

        return true
    }

    private fun isSame(node1: TreeNode?, node2: TreeNode?): Boolean {
        if (node1 == null && node2 == null) {
            return true
        } else if (node1 != null && node2 != null && node1.value == node2.value) {
            return true
        } else {
            return false
        }
    }

    private fun isListSym(list: List<TreeNode?>): Boolean {
        if (list.isEmpty()) return true

        var left = 0
        val size = list.size
        var right = size - 1

        while (left <= right) {
            if (!isSame(list[left], list[right])) {
                return false
            }
            left++
            right--
        }

        return true
    }
}