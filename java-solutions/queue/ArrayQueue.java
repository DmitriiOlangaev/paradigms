package queue;

/*
    Model: a[1]...a[n]
    Invariant: for i=1...n: a[i] != null

    Let immutable(l, r): for i=l...r: a'[i] == a[i]

    Pre: element != null
    Post: n' = n + 1 && a[n'] == element && immutable(1, n)
        enqueue(element)

    Pre: n >= 1
    Post: n' == n && R = a[1] && immutable(1, n)
        element()

    Pre: n >= 1
    Post: n' == n - 1 && R = a[1] && for i=1...n' a'[i] = a[i + 1]
        dequeue()

    Pre: true
    Post: R = n && n' == n && immutable(1, n)
        size()

    Pre: true
    Post: R = (n == 0) && n' == n && immutable(1, n)
        isEmpty

    Pre: true
    Post: n' = 0
        clear

 */


public class ArrayQueue extends AbstractQueue {
    private Object[] elements;
    private int head, tail;

    public ArrayQueue() {
        elements = new Object[1];
        size = tail = head = 0;
    }

    public Queue createQueue() {
        return new ArrayQueue();
    }

    //    Pre: element != null
//    Post: n' = n + 1 && a[n'] == element && immutable(1, n)
//    enqueue(element)
    private int norm(int i) {
        return (i + elements.length) % elements.length;
    }

    protected void enqueueImpl(Object element) {
        ensureCapacity(size + 1);
        elements[tail] = element;
        tail = norm(tail + 1);
    }

    public void pushImpl(final Object element) {
        ensureCapacity(size + 1);
        elements[head] = element;
        head = norm(head - 1);
    }

    private void ensureCapacity(int size) {
        if (elements.length < size) {
            Object[] newQueue = new Object[elements.length * 2 + 2];
            for (int i = 0; i < size; ++i) {
                newQueue[i] = elements[norm(head + i + 1)];
            }
            elements = newQueue;
            head = newQueue.length - 1;
            tail = size - 1;
        }
    }

    protected Object elementImpl() {
        return elements[norm(head + 1)];
    }

    protected Object peekImpl() {
        return elements[norm(tail - 1)];
    }

    protected Object dequeueImpl() {
        int ni = norm(head + 1);
        Object result = elements[ni];
        elements[ni] = null;
        head = ni;
        return result;
    }

    protected Object removeImpl() {
        int ni = norm(tail - 1);
        Object result = elements[ni];
        elements[ni] = null;
        tail = ni;
        return result;
    }

    @Override
    public int count(Object element) {
        int ans = 0;
        for (int i = 0; i < size; ++i) {
            if (elements[norm(head + i + 1)].equals(element)) {
                ++ans;
            }
        }
        return ans;
    }

    protected void clearImpl() {
        elements = new Object[1];
        head = tail = 0;
    }

}
