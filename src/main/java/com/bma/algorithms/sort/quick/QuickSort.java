package com.bma.algorithms.sort.quick;

import com.bma.algorithms.sort.Sort;
import com.bma.algorithms.sort.elementary.Util;

import java.util.Random;

import static com.bma.algorithms.sort.elementary.Util.println;
import static com.bma.algorithms.sort.elementary.Util.swap;
import static java.lang.String.format;

public class QuickSort implements Sort {

    private final int[] input;
    private long totalTime;
    private final Random rand = new Random();

    public QuickSort(int[] input) {
        this.input = input;
    }

    @Override
    public void print() {
        println(this.input);
    }

    @Override
    public long time() {
        println("<QuickSort> Total Execution Time: " + totalTime + "ms");
        return totalTime;
    }

    @Override
    public void sort() {
        long startMillis = System.currentTimeMillis();
        int start = 0;
        int end = input.length;
        sort(input, start, end);
        totalTime = System.currentTimeMillis() - startMillis;
    }

    private void sort(int[] input, int start, int end) {
        if (start == end) return ;

        int randomNumber = rand.nextInt(end - start);
        int randomPivot = randomNumber + start;

        // move pivot at the end
        swap(input, randomPivot, end-1);

        int pivot = end - 1;
        int i = start; int j = i;
        while (j < pivot) {
            if (input[j] < input[pivot]) {
                swap(input, i, j);
                i++;
            }
            j++;
        }

        // put the pivot in its correct position
        swap(input, i, pivot);
        pivot = i;

        sort(input, start, pivot);
        sort(input, pivot + 1, end);

    }

    private int sortWithConsoleLogs(int[] input, int start, int end) {

        if (start == end) return 0;

        Util.print("Input Array = ");
        println(input);
        println("First Iteration...");
        println(format("Start Index = %d, End Index = %d", start, end));

        println("Randomly selecting an index from the array as pivot.");
        int randomNumber = rand.nextInt(end - start);

        int randomPivot = randomNumber + start;
        println(format("Let's say we select index = %d as pivot", randomPivot));
        println("Pivot Index = " + randomPivot);
        println("Value at Pivot Index = " + input[randomPivot]);


        // move pivot at the end
        println("Let's put the selected pivot at the end of the array");
        swap(input, randomPivot, end-1);

        println("So the array becomes this,");
        println(input);

        println("So far so good... we haven't began with the iteration yet.");

        int pivot = end - 1;
        int i = start; int j = i;
        println(format("Now, let's take two variables(say, i & j) and assign the starting index to them."));
        println(format("Such that the value of i=%d and j=%d", i, j));

        println("Now, lets start the iteration.");
        println(format("Iterate until j=%d reaches the last index (i.e %d)", j, pivot));
        println(format("In the while loop, we will compare the value of arr[j] with the selected pivot (e.g %d).", input[pivot]));
        println(format("Let's see how the value of j and i changes over the iteration."));
        while (j < pivot) {
            println(format("i=%d, j=%d", i, j));
            if (input[j] < input[pivot]) {
                println(format("value at j=%d (%d) is smaller than the pivot(%d)", j, input[j], input[pivot]));
                println(format("so we swap the values at index %d with %d", i, j));
                println(format("and increment i by one"));
                swap(input, i, j);
                i++;
            }

            println("Increment j by 1");
            j++;

            println(format("new value of i is %d and j is %d", i, j));
        }

        println("j has reached the pivot, which means i is the correct index for the pivot.");
        println("So let's swap them.");
        // put the pivot in its correct position
        swap(input, i, pivot);
        pivot = i;

        println(format("Now the pivot has reached its correct position that is index %d", pivot));

        System.exit(0);
        println(format("i=%d j=%d", i, j));
        println(input);
        println("nth position=" + pivot);
        println();

        int left = sortWithConsoleLogs(input, start, pivot);
        int right = sortWithConsoleLogs(input, pivot + 1, end);

        println("-----------------------------------------");

        return pivot;
    }

    public static void main(String[] args) {
        int input[] = {10,9,8,7,6,5,4,3,2,1}; //Util.generateUnsortedArray(1000000);
        QuickSort quickSort = new QuickSort(input);
        quickSort.sort();
//        quickSort.print();
        quickSort.time();
    }
}
