/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */
typedef struct ListNode node_t;

node_t* new_digit(int digit) {
    node_t *n;
    n = (node_t *)malloc(sizeof(node_t));
    n->val = digit;
    n->next = 0;
    return n;
}

node_t* addTwoNumbers(struct ListNode* l1, struct ListNode* l2){
    node_t *start, *p;
    int carry = 0;

    start = p = 0;
    while (l1 || l2) {
        int sum = (l1 ? l1->val : 0) + (l2 ? l2->val : 0) + carry;

        if (!start) {
            start = p = new_digit(sum % 10);
        } else {
            p->next = new_digit(sum % 10);
            p = p->next;
        }

        carry = sum / 10;

        if (l1) l1 = l1->next;
        if (l2) l2 = l2->next;
    }

    if (carry) p->next = new_digit(carry);
    
    return start;
}