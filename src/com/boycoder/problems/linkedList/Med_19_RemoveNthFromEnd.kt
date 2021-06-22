package com.boycoder.problems.linkedList

import com.boycoder.basis.datastructure.ListNode

/**
 * @Author: zhutao
 * @datetime: 2021/6/22
 * @desc:
 */
object Med_19_RemoveNthFromEnd {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var dummy = ListNode(0, head)

        var right = dummy
        var left = dummy

        // let right pointer go faster
        var step = n
        while (step > 0) {
            right = right.next!!
            step--
        }

        // then let right pointer go to the end
        while (right.next != null) {
            right = right.next!!
            left = left.next!!
        }

        // delete the Nth node
        left.next = left.next!!.next

        return dummy.next
    }
}