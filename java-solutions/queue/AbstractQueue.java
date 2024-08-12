package queue;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractQueue implements Queue, Dequeue {
    protected int size;

    //
//    Pre: true
//    Post: R = n && n' == n && immutable(1, n)
//    size()

    public abstract Queue createQueue();

    @Override
    public Queue filter(final Predicate<Object> predicate) {
        final Queue result = createQueue();
        final int sz = size;
        for (int i = 0; i < sz; ++i) {
            final Object temp = dequeue();
            enqueue(temp);
            if (predicate.test(temp)) {
                result.enqueue(temp);
            }
        }
        return result;
    }

    // :NOTE: Сложные взаимозависимости
    @Override
    public Queue map(final Function<Object, Object> function) {
        final Queue result = createQueue();
        final int sz = size;
        for (int i = 0; i < sz; ++i) {
            final Object temp = dequeue();
            enqueue(temp);
            result.enqueue(function.apply(temp));
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    //
//    Pre: true
//    Post: R = (n == 0) && n' == n && immutable(1, n)
//    isEmpty
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    protected abstract void enqueueImpl(Object element);


    //    Pre: element != null
//    Post: n' = n + 1 && a[n'] == element && immutable(1, n)
//    enqueue(element)
    @Override
    public void enqueue(final Object element) {
        Objects.requireNonNull(element);
        enqueueImpl(element);
        ++size;
    }

    protected abstract void pushImpl(Object element);

    //  Pre: element != null
//  Post: n' = n + 1 && for i=1...n a[i + 1] = a[i] && a[1] = element
//  push(element)
    @Override
    public void push(final Object element) {
        Objects.requireNonNull(element);
        pushImpl(element);
        ++size;
    }

    //    Pre: n >= 1
//    Post: n' == n && R = a[1] && immutable(1, n)
//    element()
    protected abstract Object elementImpl();

    @Override
    public Object element() {
        assert size >= 1;
        return elementImpl();
    }

// Pre: n >= 1
// Post: n' = n && R = a[n] && immutable(1, n)
// peek()

    protected abstract Object peekImpl();

    @Override
    public Object peek() {
        assert size >= 1;
        return peekImpl();
    }

    //
//    Pre: n >= 1
//    Post: n' == n - 1 && R = a[1] && for i=1...n' a[i] = a[i + 1]
//    dequeue()

    protected abstract Object dequeueImpl();

    @Override
    public Object dequeue() {
        assert size >= 1;
        final Object result = dequeueImpl();
        --size;
        return result;
    }

//
//  Pre: n >= 1
//  Post: n' == n - 1 && R = a[n] && immutable(1, n')
//  remove()

    protected abstract Object removeImpl();

    @Override
    public Object remove() {
        assert size >= 1;
        final Object result = removeImpl();
        --size;
        return result;
    }

    //Pre: true
//Post: n' == n && immutable(1, n) && R = count y in a : x.equals(y)
//Count(x)

    public abstract int count(Object element);


//
//    Pre: true
//    Post: n' = 0
//    clear

    protected abstract void clearImpl();

    public void clear() {
        clearImpl();
        size = 0;
    }

}
