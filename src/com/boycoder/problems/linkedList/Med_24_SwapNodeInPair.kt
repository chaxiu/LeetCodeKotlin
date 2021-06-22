package com.boycoder.problems.linkedList

import com.boycoder.basis.datastructure.ListNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc: https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 */
object Med_24_SwapNodeInPair {
    fun swapPairs(head: ListNode?): ListNode? {
        if (head == null || head.next == null) return head

        var dummy = ListNode(0, head)
        var pre = dummy
        var cur = head

        while (cur != null && cur.next != null) {

            val next = cur.next!!
            val nextCur = next.next

            pre.next = next
            next.next = cur
            cur.next = nextCur

            pre = cur
            cur = nextCur
        }

        return dummy.next
    }
}

