package queue;

public class LinkedQueue extends AbstractQueue {
    private final Node head, tail;

    public LinkedQueue() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public Queue createQueue() {
        return new LinkedQueue();
    }

    protected void enqueueImpl(final Object element) {
        final Node newElement = new Node(element, tail.prev, tail);
        tail.prev.next = newElement;
        tail.prev = newElement;
    }

    protected void pushImpl(final Object element) {
        final Node newElement = new Node(element, head.next, head);
        head.next.prev = newElement;
        head.next = newElement;
    }

    protected Object elementImpl() {
        return head.next.element;
    }

    protected Object peekImpl() {
        return tail.prev.element;
    }

    protected Object dequeueImpl() {
        final Object result = head.next.element;
        head.next = head.next.next;
        head.next.prev = head;
        return result;
    }

    protected Object removeImpl() {
        final Object result = tail.prev.element;
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        return result;
    }

    @Override
    public int count(final Object element) {
        int ans = 0;
        Node cur = head;
        do {
            if (cur.next.element.equals(element)) {
                ++ans;
            }
            cur = cur.next;
        } while (cur.next != null);
        return ans;
    }

    protected void clearImpl() {
        head.next = tail;
        tail.prev = head;
    }


    private static class Node {
        private final Object element;
        // :NOTE: Двусвязный
        private Node prev;
        private Node next;

        private Node(final Object element, final Node prev, final Node next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        private Node() {
            element = prev = next = null;
        }
    }
}
