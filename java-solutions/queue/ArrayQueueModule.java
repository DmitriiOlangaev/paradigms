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

public class ArrayQueueModule {
    private static Object[] elements = new Object[1];
    private static int size = 0;
    private static int head = 0, tail = 0;

//    Pre: element != null
//    Post: n' = n + 1 && a[n'] == element && immutable(1, n)
//    enqueue(element)

    private static int norm(int i) {
        return (i + elements.length) % elements.length;
    }

    public static void enqueue(final Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(size + 1);
        elements[tail] = element;
        tail = norm(tail + 1);
        ++size;
    }

//  Pre: element != null
//  Post: n' = n + 1 && for i=1...n a[i + 1] = a[i] && a[1] = element
//  push(element)

    public static void push(final Object element) {
        Objects.requireNonNull(element);
        ensureCapacity(size + 1);
        elements[head] = element;
        head = norm(head - 1);
        ++size;
    }

    private static void ensureCapacity(int size) {
        if (elements.length < size) {
            Object[] newQueue = new Object[elements.length * 2 + 2];
            for (int i = 0; i < elements.length; ++i) {
                newQueue[i] = elements[norm(head + i + 1)];
            }
            elements = newQueue;
            head = newQueue.length - 1;
            tail = size - 1;
        }
    }

    //
//    Pre: n >= 1
//    Post: n' == n && R = a[1] && immutable(1, n)
//    element()
    public static Object element() {
        assert size >= 1;
        return elements[norm(head + 1)];
    }

// Pre: n >= 1
// Post: n' = n && R = a[n] && immutable(1, n)
// peek()

    public static Object peek() {
        assert size >= 1;
        return elements[norm(tail - 1)];
    }
//
//    Pre: n >= 1
//    Post: n' == n - 1 && R = a[1] && for i=1...n' a[i] = a[i + 1]
//    dequeue()

    public static Object dequeue() {
        int ni = norm(head + 1);
        Object result = elements[ni];
        elements[ni] = null;
        head = ni;
        --size;
        return result;
    }

    //  Pre: n >= 1
//  Post: n' == n - 1 && R = a[n] && immutable(1, n')
//  remove()
    public static Object remove() {
        int ni = norm(tail - 1);
        Object result = elements[ni];
        elements[ni] = null;
        tail = ni;
        --size;
        return result;
    }

//
//    Pre: true
//    Post: R = n && n' == n && immutable(1, n)
//    size()


    public static int size() {
        return size;
    }

    //Pre: true
//Post: n' == n && immutable(1, n) && R = count y in a : x.equals(y)
//Count(x)
    public static int count(Object obj) {
        Objects.requireNonNull(obj);
        int ans = 0;
        for (int i = head + 1; i <= head + size; ++i) {
            if (elements[norm(i)].equals(obj)) {
                ++ans;
            }
        }
        return ans;

    }

    //
//    Pre: true
//    Post: R = (n == 0) && n' == n && immutable(1, n)
//    isEmpty
    public static boolean isEmpty() {
        return size == 0;
    }

    //
//    Pre: true
//    Post: n' = 0
//    clear
    public static void clear() {
        elements = new Object[1];
        size = head = tail = 0;
    }

}
