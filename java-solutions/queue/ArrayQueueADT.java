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
    Post: n' == n - 1 && R = a[1] && for i=1...n' a[i] = a[i + 1]
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


import java.util.Objects;

public class ArrayQueueADT {
    private Object[] elements;
    private int size;
    private int head, tail;

    public ArrayQueueADT() {
        elements = new Object[1];
        head = tail = size = 0;
    }

    //    Pre: element != null
//    Post: n' = n + 1 && a[n'] == element && immutable(1, n)
//    enqueue(element)
    private static int norm(final ArrayQueueADT queue, int i) {
        return (i + queue.elements.length) % queue.elements.length;
    }

    public static void enqueue(final ArrayQueueADT queue, final Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(queue, queue.size + 1);
        queue.elements[queue.tail] = element;
        queue.tail = norm(queue, queue.tail + 1);
        ++queue.size;
    }

//  Pre: element != null
//  Post: n' = n + 1 && for i=1...n a[i + 1] = a[i] && a[1] = element
//  push(element)

    public static void push(final ArrayQueueADT queue, final Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(queue, queue.size + 1);
        queue.elements[queue.head] = element;
        queue.head = norm(queue, queue.head - 1);
        ++queue.size;
    }

    private static void ensureCapacity(final ArrayQueueADT queue, int size) {
        if (queue.elements.length < size) {
            Object[] newQueue = new Object[queue.elements.length * 2 + 2];
            for (int i = 0; i < size; ++i) {
                newQueue[i] = queue.elements[norm(queue, queue.head + i + 1)];
            }
            queue.elements = newQueue;
            queue.head = newQueue.length - 1;
            queue.tail = size - 1;
        }
    }

    //
//    Pre: n >= 1
//    Post: n' == n && R = a[1] && immutable(1, n)
//    element()
    public static Object element(final ArrayQueueADT queue) {
        assert queue.size >= 1;
        return queue.elements[norm(queue, queue.head + 1)];
    }

// Pre: n >= 1
// Post: n' = n && R = a[n] && immutable(1, n)
// peek()

    public static Object peek(final ArrayQueueADT queue) {
        assert queue.size >= 1;
        return queue.elements[norm(queue, queue.tail - 1)];
    }

    //Pre: true
//Post: n' == n && immutable(1, n) && R = count y in a : x.equals(y)
//Count(x)
    public static int count(final ArrayQueueADT queue, Object obj) {
        Objects.requireNonNull(obj);
        int ans = 0;
        for (int i = queue.head + 1; i <= queue.head + queue.size; ++i) {
            if (queue.elements[norm(queue, i)].equals(obj)) {
                ++ans;
            }
        }
        return ans;

    }


//
//    Pre: n >= 1
//    Post: n' == n - 1 && R = a[1] && for i=1...n' a[i] = a[i + 1]
//    dequeue()

    public static Object dequeue(final ArrayQueueADT queue) {
        int ni = norm(queue, queue.head + 1);
        Object result = queue.elements[ni];
        queue.elements[ni] = null;
        queue.head = ni;
        --queue.size;
        return result;
    }

    //  Pre: n >= 1
//  Post: n' == n - 1 && R = a[n] && immutable(1, n')
//  remove()
    public static Object remove(final ArrayQueueADT queue) {
        int ni = norm(queue, queue.tail - 1);
        Object result = queue.elements[ni];
        queue.elements[ni] = null;
        queue.tail = ni;
        --queue.size;
        return result;
    }

    //
//    Pre: true
//    Post: R = n && n' == n && immutable(1, n)
//    size()
    public static int size(final ArrayQueueADT queue) {
        return queue.size;
    }

    //
//    Pre: true
//    Post: R = (n == 0) && n' == n && immutable(1, n)
//    isEmpty
    public static boolean isEmpty(final ArrayQueueADT queue) {
        return queue.size == 0;
    }

    //
//    Pre: true
//    Post: n' = 0
//    clear
    public static void clear(final ArrayQueueADT queue) {
        queue.elements = new Object[1];
        queue.head = queue.tail = queue.size = 0;
    }

}
