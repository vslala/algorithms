package com.bma.parsers;

import com.bma.problemsolving.leetcode.GroupAnagrams;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestClass {

    @Test
    public void printTriangle() {
        int mid = 5;
        for (int height = 0; height < 6; height++) {
            for (int width = 0; width < 12; width++) {
                if (width == mid && height == 0) {
                    star();
                } else {
                    if (width == mid-height || width == mid+height) star();
                    else if (height == 5) star();
                    else space();
                }
            }
            nextLine();
        }

    }

    @Test
    public void totalSum() {
        int total = 0;
        int[] input = {33583,11192,3728,1240,411,135,43, 12, 2};
        for (int num : input) {
            int fuel = num;
            total += fuel;
        }
        System.out.println(total);
    }

    @Test
    public void factorial() {
        System.out.println(findFactorial(5));
    }

    private int findFactorial(int i) {
        if (i == 0) return 1;
        return i * (findFactorial(i-1));
    }

    @Test
    public void testThread() throws InterruptedException {
        Thread t1 = new Thread(){
            @SneakyThrows
            public void run() {
                for (int i=0; i<10; i++) {
                    System.out.println("Thread is running...");
                    Thread.sleep(1000);
                    System.out.println(this.getState());
                }
            }
        };
        t1.setName("MyThread-" + t1.getId());
        t1.start();

        Thread.sleep(10000);
        System.out.println("Thread2 is sleeping...");
        System.out.println("EXecution is finished!");
    }

    @Test
    public void getThread() {
        Thread.getAllStackTraces().keySet().forEach(thread -> {
            if (thread.getName() == "MyThread-16") {
                System.out.println("Thread found!");
            }
        });
    }

    private void nextLine() {
        System.out.println();
    }

    private void space() {
        System.out.print(" ");
    }

    private void star() {
        System.out.print("*");
    }

    private void print(Object str) {
        System.out.print(str);
    }

    private void println(Object str) {
        System.out.println(str);
    }

    @Test
    public void ascii() {
        String[] strs = {"cab","tin","pew","duh","may","ill","buy","bar","max","doc"};
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        List<List<String>> output = groupAnagrams.groupAnagrams(strs);
        println(output);
    }

    @Test
    public void it_should_find_largest_and_second_largest_number() {
        int arr[] = new int[] {1,2,5,6,6,4};
        int largest = arr[0];
        int secondLargest = arr[0];

        for (int num : arr) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else {
                if (num != largest && num > secondLargest) {
                    secondLargest = num;
                }
            }
        }

        System.out.println("Second Largest  = " + secondLargest);
    }

}
