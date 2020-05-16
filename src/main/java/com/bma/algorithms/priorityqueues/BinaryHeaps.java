package com.bma.algorithms.priorityqueues;

import com.bma.algorithms.sort.elementary.Util;

import java.util.*;

public class BinaryHeaps<T> {


    private List<T> heap = new ArrayList<>();

    public BinaryHeaps() {heap.add(null);}

    public void insert(T item) {
        heap.add(item);
        swim(size() - 1);
    }

    private void swim(int currentIndex) {
        for (; currentIndex > 1;) {
            int childIndex = currentIndex;
            int parentIndex = currentIndex/2;

            if (Util.less(heap, childIndex, parentIndex, StringComparator.newInstance())) break;

            Collections.swap(heap, childIndex, parentIndex);
            currentIndex = parentIndex;
        }
    }

    public int size() {
        return heap.size();
    }

    public List<T> get() {
        return heap;
    }

    public void deleteMax() {
        int lastNode = size() - 1;
        Collections.swap(heap, 1, lastNode);
        heap.remove(lastNode);

        StringComparator comparator = StringComparator.newInstance();
        int currentNode = 1;
        while (currentNode * 2 < size()) {
            int firstChild = currentNode * 2;
            int secondChild = firstChild + 1;
            if (secondChild < lastNode && Util.less(heap, firstChild, secondChild, comparator)) {
                if (Util.less(heap, currentNode, secondChild, comparator)) {
                    Collections.swap(heap, currentNode, secondChild);
                    currentNode = secondChild;
                }

            } else {
                Collections.swap(heap, currentNode, firstChild);
                currentNode = firstChild;
            }
        }
    }

    public static class StringComparator implements Comparator<String> {

        public static StringComparator newInstance() {
            return new StringComparator();
        }

        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }
}
