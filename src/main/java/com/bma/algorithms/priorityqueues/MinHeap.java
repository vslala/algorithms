package com.bma.algorithms.priorityqueues;

import com.bma.algorithms.sort.elementary.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinHeap<I extends Comparable> {

    private List<I> queue = new ArrayList<>();

    public MinHeap() {
        queue.add(null);
    }

    public void add(I item) {
        queue.add(item);
        swim(queue.size() - 1);
    }

    private void swim(int index) {
        int parent;
        while (index > 0) {
            parent = index / 2;

            if (parent == 0) break;
            if (queue.get(parent).compareTo(queue.get(index)) < 0) break;

            if (queue.get(parent).compareTo(queue.get(index)) > 0) {
                Collections.swap(queue, parent, index);
                index = parent;
            }
        }
    }

    public int size() {
        return queue.size() - 1;
    }

    public void print() {
        Util.println(queue, ",");
    }

    public I remove() {
        Collections.swap(queue, 1, size());
        I item = queue.get(size());
        queue.remove(size());
        sink(1);
        return item;
    }

    private void sink(int index) {
        int child = 0;
        while ((child = index * 2) < queue.size()) {
            if (child < size() && queue.get(child).compareTo(queue.get(child + 1)) > 0) child++;
            if (queue.get(child).compareTo(queue.get(index)) > 0) break;
            Collections.swap(queue, child, index);
            index = child;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
