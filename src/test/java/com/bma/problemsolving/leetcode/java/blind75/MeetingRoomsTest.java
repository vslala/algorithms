package com.bma.problemsolving.leetcode.java.blind75;

import lombok.Data;
import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junitpioneer.jupiter.json.JsonSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 18/05/2025
 */
class MeetingRoomsTest {

    @Data
    static class MeetingRoomInput {
        int[][] intervals;
        boolean expected;
    }

    @ParameterizedTest
    @JsonSource(value = {
            """
                    {
                        "intervals": [[0,30],[5,10],[15,20]],
                        "expected": false
                    }
                    """,
            """
                    {
                        "intervals": [[7,10],[2,4]],
                        "expected": true
                    }
                    """
    })
    void given_a_list_of_meeting_intervals_compute_if_a_person_would_be_able_attend_all_meetings(MeetingRoomInput input) {
        var sol = new MeetingRooms();
        boolean output = sol.canAttendMeetings(input.intervals);
        assertEquals(input.expected, output);
    }
}