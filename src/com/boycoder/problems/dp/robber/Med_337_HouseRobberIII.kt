package com.boycoder.problems.dp.robber

import com.boycoder.basis.datastructure.TreeNode
import com.boycoder.utils.asserts

/**
 * @Author: zhutao
 * @datetime: 2021/6/16
 * @desc: https://leetcode-cn.com/problems/house-robber-iii/
 */
object Med_337_HouseRobberIII {
    private val memo = hashMapOf<TreeNode, Int>()

    fun rob(root: TreeNode?): Int {
        memo.clear()
        val res = traversal3(root)
        return Math.max(res[0], res[1])
    }

    /**
     * dp + optimize
     */
    private fun traversal3(root: TreeNode?): IntArray {
        if (root == null) return intArrayOf(0, 0)

        if (root.left == null && root.right == null) {
            return intArrayOf(0, root.value)
        }

        // choose root
        val left = traversal3(root.left)
        val right = traversal3(root.right)
        // choose root, not choose left, right
        val choose = root.value + left[0] + right[0]

        // not choose root
        val noChoose = Math.max(left[0], left[1]) + Math.max(right[0], right[1])

        return intArrayOf(noChoose, choose)
    }

    /**
     * dp
     *
     * This problems can also use dp, dp on the tree.
     * Unlike normal dp by using loop, dp on the tree still use recursion.
     * For every TreeNode, we use dp[0] dp[1] to represent max value that
     * not choose or choose the TreeNode.
     */
    private fun traversal2(root: TreeNode?): IntArray {
        if (root == null) return intArrayOf(0, 0)

        if (root.left == null && root.right == null) {
            return intArrayOf(0, root.value)
        }

        // choose root
        var choose = root.value
        if (root.left != null) {
            val res1 = traversal2(root.left?.left)
            val res2 = traversal2(root.left?.right)
            choose = choose + Math.max(res1[0], res1[1]) + Math.max(res2[0], res2[1])
        }

        if (root.right != null) {
            val res3 = traversal2(root.right?.left)
            val res4 = traversal2(root.right?.right)
            choose = choose + Math.max(res3[0], res3[1]) + Math.max(res4[0], res4[1])
        }

        // not choose root
        val res5 = traversal2(root.left)
        val res6 = traversal2(root.right)
        val noChoose = Math.max(res5[0], res5[1]) + Math.max(res6[0], res6[1])

        return intArrayOf(noChoose, choose)
    }

    /**
     * Recursion + cache
     */
    private fun traversal1(root: TreeNode?): Int {
        if (root == null) return 0

        // leaf node
        if (root.left == null && root.right == null) return root.value

        if (memo.containsKey(root)) {
            return memo.getOrDefault(root, 0)
        }

        // rob the child
        var child = 0
        child = traversal(root.left) + traversal(root.right)

        // rob the parent
        var parent = root.value
        if (root.left != null) {
            parent = parent + rob(root.left?.left) + rob(root.left?.right)
        }

        if (root.right != null) {
            parent = parent + rob(root.right?.left) + rob(root.right?.right)
        }

        val res = Math.max(parent, child)
        memo[root] = res
        return res
    }

    /**
     * Recursion + post-order traversal
     */
    private fun traversal(root: TreeNode?): Int {
        if (root == null) return 0

        // leaf node
        if (root.left == null && root.right == null) return root.value

        // rob the child
        var child = 0
        child = traversal(root.left) + traversal(root.right)

        // rob the parent
        var parent = root.value
        if (root.left != null) {
            parent = parent + rob(root.left?.left) + rob(root.left?.right)
        }

        if (root.right != null) {
            parent = parent + rob(root.right?.left) + rob(root.right?.right)
        }

        return Math.max(parent, child)
    }
}

fun main() {
    val node1 = TreeNode(3)
    val node2 = TreeNode(1)
    val node3 = TreeNode(2, right = node1)
    val node4 = TreeNode(3, right = node2)
    val root = TreeNode(3, left = node3, right = node4)

    val res = Med_337_HouseRobberIII.rob(root)
    asserts(res, 7)
}