package ohm.softa.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable<Object> {
    private ListElement head;
    private int size = 0;
    public SimpleListImpl() {
        this.head = null;
        this.size = 0;
    }
    @Override
    public void add(Object o) {
        if(this.head == null) {
            this.head = new ListElement(o);
        } else {
            ListElement current = this.head;
            while(current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new ListElement(o));
        }
        size++;
    }
    public Iterator<Object> iterator() {
        return new SimpleIterator();
    }

    private class SimpleIterator implements Iterator<Object>{
        private ListElement current = head;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Object tmp = current.item;
            this.current = current.getNext();
            return tmp;
        }
    }

    private static class ListElement {

        ListElement(Object item) {
            this.item = item;
            this.next = null;
        }
        private Object item;
        private ListElement next;

        public void setNext(ListElement next) {
            this.next = next;
        }

        public ListElement getNext() {
            return next;
        }

        public Object getItem(){
            return item;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleList result = new SimpleListImpl();
        for (Object o: this) {
            if (filter.include(o)) {
                result.add(o);
            }
        }
        return result;
    }


    private class IteratorImpl implements Iterator {
        private ListElement current = head;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Object tmp = current.getItem();
            current = current.getNext();
            return tmp;
        }
    }
	// TODO: Implement the required methods.

}
