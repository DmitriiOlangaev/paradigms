package queue;

import java.util.function.Function;
import java.util.function.Predicate;

//Model: a[1]...a[n]
//Invariant: for i=1...n: a[i] != null
//Let immutable(l, r): for i=l...r: a'[i] == a[i]

public interface Queue {
    //
    //    Pre: true
    //    Post: R = n && n' == n && immutable(1, n)
    //    size()
    int size();

    //
    //    Pre: true
    //    Post: R = (n == 0) && n' == n && immutable(1, n)
    //    isEmpty
    boolean isEmpty();

    //    Pre: element != null
    //    Post: n' = n + 1 && a[n'] == element && immutable(1, n)
    //    enqueue(element)
    void enqueue(Object element);

    Object element();

    Object dequeue();

    //
//    Pre: true
//    Post: n' = 0
//    clear
    void clear();

    //Pre true
    //Post immutable(1, n)
    //R = queue = subsequence of this.queue with max len : for each element of subsequence predicate.test(e) = true;
    Queue filter(Predicate<Object> predicate);

    //Pre true
    //Post immutable(1, n)
    //R = queue with size n && for each i in [1, n] R[i] = function.apply(this.queue[i])
    Queue map(Function<Object, Object> function);
}
