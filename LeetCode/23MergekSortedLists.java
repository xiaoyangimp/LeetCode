/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists.size() == 0) return null; // return null if there's nothing in the lists
	  
	  /* Here, we create a PreiorityQueue q to save all the listnode, and use a new comparator that
		compare the first element of listnodes so that all listnodes are sorted according to the value
		that saved in the head ListNode Object*/
	  PriorityQueue<ListNode> q = new PriorityQueue<ListNode>(lists.size(),
			  new Comparator<ListNode>() {
		         public int compare(ListNode a, ListNode b) {
		        	 if (a.val > b.val) return 1;
		        	 else if(a.val == b.val) return 0;
		        	 else return -1;
		         }
	  });
	  
	  // add all nodes into the queue
	  for( ListNode list: lists) {
		  if ( list != null)
			  q.add(list);
	  }
	  
	  // create a new listnode as the header of result listnodes
	  ListNode head = new ListNode(0);
	  ListNode p = head;
	  
	  // continuously obtaining the smallest ListNode from the PriorityQueue
	  while(q.size() > 0) {
		  ListNode temp;
		  temp = q.poll(); 	// pull a listnode out
		  p.next = temp;	// append to the tail of the result listnode
		  
		  if(temp.next != null) { // if there's no more node in a listnode, then discard the listnode. Otherwise add it into the queue
			  q.add(temp.next);
		  }
		  
		  p = p.next;
	  }
	  
	  return head.next;
    }
}