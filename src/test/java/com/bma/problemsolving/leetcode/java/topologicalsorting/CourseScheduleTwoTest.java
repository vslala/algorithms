package com.bma.problemsolving.leetcode.java.topologicalsorting;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CourseScheduleTwoTest {

    private CourseScheduleTwo sol = new CourseScheduleTwo();

    @ParameterizedTest
    @CsvSource(value = {
            "2 [[1,0]] [[0,1]]",
            "4 [[1,0],[2,0],[3,1],[3,2]] [[0,2,1,3]]",
            "1 [] [[0]]",
            "5 [[1,0],[1,2],[1,3],[1,4],[4,0],[0,1]] [[]]", // creates cycle
    }, delimiter = ' ')
    void shouldReturnTheCourseArrayInOrderOfTheirPreRequisites(int numOfCourses, String courseDependencyExpr, String outputExpr) {
        int[][] prerequisites = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(courseDependencyExpr, Integer.class));
        int[][] expected = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(outputExpr, Integer.class));

        var result = sol.findOrder(numOfCourses, prerequisites);

        Fixtures.assertArrayEquals(expected[0], expected[0], result);
    }

}