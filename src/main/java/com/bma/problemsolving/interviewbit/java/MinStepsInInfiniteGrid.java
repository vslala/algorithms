package com.bma.problemsolving.interviewbit.java;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.pow;

@ToString
@Getter
@RequiredArgsConstructor
class Coordinate {
    private final int x;
    private final int y;
}

class Grid {
    private List<Coordinate> coordinates = new ArrayList<>();

    public Grid() {
        coordinates.add(new Coordinate(0,0));
    }

    public void plot(Coordinate coordinate) {
        coordinates.add(coordinate);
    }

    public void sortAsc() {
        coordinates.sort(Comparator.comparingDouble(this::a2b2));
    }

    public void print() {
        System.out.println(coordinates.stream().map(Object::toString).collect(Collectors.joining("\n")));
    }

    private double a2b2(Coordinate a) {
        return pow(a.getX(), 2) + pow(a.getY(), 2);
    }

    /**
     * sq rt of [{(x2 - x1)} squared + {(y2 - y1) squared}]
     * @return
     */
    public int visitAllCoordInMinSteps() {
        int steps = 0;
        for (int i=0; i < coordinates.size() - 1; i++) {
            steps += Math.sqrt(
                    pow(coordinates.get(i + 1).getX() - coordinates.get(i).getX(), 2) +
                            pow(coordinates.get(i + 1).getY() - coordinates.get(i).getY(), 2)
            );
        }
        return steps;
    }
}

public class MinStepsInInfiniteGrid {

    public int coverPoints(List<Integer> a, List<Integer> b) {
        Grid grid  = new Grid();
        IntStream.range(0, a.size()).forEach(index -> grid.plot(new Coordinate(a.get(index), b.get(index))));
        grid.sortAsc();
        grid.print();

        return grid.visitAllCoordInMinSteps();
    }
}
