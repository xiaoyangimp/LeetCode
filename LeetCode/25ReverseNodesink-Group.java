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
    public ListNode reverseKGroup(ListNode head, int k) {
		// if there's no element in the head listnode or k = 1 (do not reverse any node), then just return the original one
        if (head == null || k <= 1) return head;
		
		ListNode test = isMovable(head, k);	// determine whether or not the listnode is movable, explained later
		ListNode p1=null;
		
		if( test != null) {	// if the listnode is reversable in the first k ListNode, then reverse it and get the new header
			p1 = head;		// the new tail for this sequence
			head = test;	// the new header for this sequence
		}
		
		while(test != null) {	// keeps reversing and detecting
			p1 = exchange(p1, k);
			test = isMovable(p1, k);
		}
		
		return head;
	}
	
	/* This function is called to determine whether or not a remaining listnode is reversable,
	 if there are still k ListNodes after the temp ListNode, then return a reference to the tail
	 element of this sequence because it will be the new header for this sequence. However, if this
	 number is smaller than k, the sequence is not reversable so a null will be returned to indicate that
	 this is the end and no more reverse is needed*/
	public ListNode isMovable (ListNode temp, int k){
		while( k != 1) {
			if(temp == null) return null;
			temp = temp.next;
			k--;
		}
		
		return temp;
	}
	
	/* This function is called to do the reverse after the ismovable detection.*/
	public ListNode exchange(ListNode start, int k) {
		ListNode t1, t2, t3, ttemp;
		int rem = k;
		t1 = start;
		t2 = start;
		t3 = t2.next;
		
		while(k >= 2) { 		// until there are still ListNode pairs to reverse
			ttemp = t3;			// first get a temp reference for the latter ListNode
			t3 = t3.next;		// move the original reference to the next ListNode (the latter ListNode in the next reverse pair)
			ttemp.next = t2;	// point the second ListNode's next reference to the former one (Reverse)
			t2 = ttemp;			// move the reference to the current temp ListNode ( the former ListNode in the next reverse pair )
			k--;
		}
		
		ttemp = isMovable(t3, rem); 		// determine whether or not the next sequence is movable
		if(ttemp == null) {		// if not movable the next reference in the tail element of current sequence will be the head element of next sequence
			t1.next = t3;
		}
		else {					// if movable the next reference in the tail element of current sequence will be the tail element of next sequence
			t1.next = ttemp;		
		}
		
		return t3;				// return a reference to the head of the next sequence
    }
}