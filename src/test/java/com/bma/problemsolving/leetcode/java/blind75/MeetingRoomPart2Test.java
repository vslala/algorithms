package com.bma.problemsolving.leetcode.java.blind75;

import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junitpioneer.jupiter.json.JsonSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 18/05/2025
 */
class MeetingRoomPart2Test {

    @Data
    static class MeetingRoomInput {
        int[][] intervals;
        int expected;
    }

    @ParameterizedTest
    @JsonSource(value = {
            """
                    {
                        "intervals": [[0,30],[5,10],[15,20]],
                        "expected": 2
                    }
                    """,
            """
                    {
                        "intervals": [[7,10],[2,4]],
                        "expected": 1
                    }
                    """,
            """
                    {
                        "intervals": [[13,15],[1,13]],
                        "expected": 1
                    }
                    """
    })
    void given_a_list_of_meetings_find_out_minimum_number_of_conference_rooms_required(MeetingRoomInput input) {
        var sol = new MeetingRoomPart2();
        int output = sol.minMeetingRooms(input.intervals);
        int output2 = sol.minMeetingRoomsApproach2(input.intervals);

        assert output == output2;
        assertEquals(input.expected, output);
    }

}