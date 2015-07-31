/**
 * Definition for singly-linked list with a random pointer.
 * public class RandomListNode {
 *     public int label;
 *     public RandomListNode next, random;
 *     public RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode CopyRandomList(RandomListNode head)
        {
			
            RandomListNode result = new RandomListNode(-1);

            RandomListNode temp = result;
            RandomListNode headcache = head;

            Hashtable oldmap = new Hashtable();		// the hashtable with mapping pair ( key = original node, value = index of that node)
            Hashtable newmap = new Hashtable();		// the hashtable with mapping pair ( key = index of the original node, value = the new deep copied node)

            result.next = null;						// initialization
            result.random = null;					// initialization

            if (head == null) return result.next;
            
            int count = 1;							// counter for the index of the node

			/* The while loop that performs deep copy with next reference only*/
            while (head != null)					
            {

                RandomListNode tnode = new RandomListNode(head.label);

                tnode.next = null;
                tnode.random = null;

                temp.next = tnode;

                oldmap.Add(head, count);	// add the mapping pair ( key = original node, value = index of that node)
                newmap.Add(count, tnode);	// add the mapping pair ( key = index of the original node, value = the new deep copied node)

                head = head.next;
                temp = temp.next;

                count++;
            }

            temp = result.next;				// back to the new nodes' head
            head = headcache;				// back to the pld nodes' head

            while (temp != null)
            {

                if (head.random == null)
                {
                    temp.random = null;
                }
                else
                {

                    temp.random = newmap[oldmap[head.random]] as RandomListNode;	// get the desired node from the hashtable

                }

                temp = temp.next;
                head = head.next;
            }

            return result.next;
        }
    
}