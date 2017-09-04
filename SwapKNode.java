package Leetcode;
public class SwapKNode {

/**
 * Definition for singly-linked list.
 *
 */

    public ListNode swapeNodes(ListNode head,int k){
        ListNode temp1 = head;
        ListNode temp2 = head;
        for(int i =0;i<k;i++){
            temp1 = temp1.next;
        }
        ListNode kthVal = temp1;

        while(temp1.next !=null){
            temp2 = temp2.next;
            temp1 = temp1.next;
        }
        int tmp  = temp2.val;
        temp2.val = kthVal.val;
        kthVal.val  = tmp;

        return head;
    }
}
