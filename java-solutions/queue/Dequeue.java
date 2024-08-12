package queue;

public interface Dequeue extends Queue {

    //  Pre: element != null
    //  Post: n' = n + 1 && for i=1...n a[i + 1] = a[i] && a[1] = element
    //  push(element)
    void push(Object element);

    Object peek();

    Object remove();
}
