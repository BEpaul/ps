/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */

typedef struct ListNode node_t;

node_t *removeNthFromEnd(node_t *head, int n) {
    node_t *left, *right;
    left = right = head;

    while (n--) {
        right = right->next;
    }

    if (!right) {
        node_t *next = head->next;
        free(head);
        return next;
    }

    while (right->next) {
        left = left->next, right = right->next;
    }

    node_t *next = left->next->next;
    free(left->next);
    left->next = next;

    return head;
}