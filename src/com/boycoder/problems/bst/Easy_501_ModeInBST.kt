package com.boycoder.problems.bst

import com.boycoder.basis.datastructure.TreeNode
import java.util.*
import kotlin.collections.ArrayList

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Easy_501_ModeInBST {
    var pre: TreeNode? = null
    var max: Int = 0
    var count: Int = 0
    val list = ArrayList<Int>()

    fun findMode(root: TreeNode?): IntArray {
        find1(root)
        return list.toIntArray()
    }

    /**
     * loop + stack
     *
     * in order traversal
     */
    private fun find1(root: TreeNode?) {
        if (root == null) return

        val stk = LinkedList<TreeNode?>()
        stk.add(root)

        while (!stk.isEmpty()) {
            val node = stk.getLast()

            if (node != null) {
                stk.removeLast()

                // right
                if (node.right != null) stk.addLast(node.right)
                // root
                stk.addLast(node)
                stk.addLast(null)
                // left
                if (node.left != null) stk.addLast(node.left)
            } else {
                // remove null
                stk.removeLast()

                val temp = stk.removeLast()?:return
                if (pre == null) {
                    count = 1
                } else if (pre?.value == temp.value){
                    count++
                } else {
                    count = 1
                }

                if (count == max) {
                    list.add(temp.value)
                }

                if (count > max) {
                    list.clear()
                    list.add(temp.value)
                }

                max = Math.max(max, count)
                pre = temp
            }
        }
    }

    // recursion with pointer
    private fun find(root: TreeNode?) {
        if (root == null) return

        find(root.left)

        if (pre == null) {
            count = 1
            list.add(root.value)
        } else if (pre!!.value == root.value) {
            count++
        } else {
            count = 1
        }

        if (count == max) {
            list.add(root.value)
        }

        if (count > max) {
            list.clear()

            list.add(root.value)
        }

        max = Math.max(count, max)
        pre = root

        find(root.right)
    }
}