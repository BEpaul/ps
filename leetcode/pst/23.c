/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */

typedef struct ListNode node_t;

node_t *mergeKLists(node_t **lists, int listsSize) {
    node_t *head = 0, *ptr;

    while (1) {
        int min = 10001, min_index = -1;

        for (int i = 0; i < listsSize; i++) {
            if (lists[i] && lists[i]->val < min) {
                min = lists[i]->val;
                min_index = i;
            }
        }

        if (min_index < 0) {
            break;
        }

        if (!head) {
            head = ptr = lists[min_index];
        } else {
            ptr->next = lists[min_index];
            ptr = lists[min_index];
        }

        lists[min_index] = lists[min_index]->next;
    }

    return head;
}