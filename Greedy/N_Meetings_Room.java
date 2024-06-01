import java.util.*;

/*
https://takeuforward.org/data-structure/n-meetings-in-one-room/

Problem Statement: There is one meeting room in a firm. You are given two arrays, start and end each of size N.For an index ‘i’, start[i] denotes the starting time of the ith meeting while end[i]  will denote the ending time of the ith meeting. Find the maximum number of meetings that can be accommodated if only one meeting can happen in the room at a  particular time. Print the order in which these meetings will be performed.

Example:

Input:  N = 6,  start[] = {1,3,0,5,8,5}, end[] =  {2,4,5,7,9,9}

Output: 1 2 4 5
*/

public class MeetingRoom {

    public static void main(String[] args) {
        int N = 6;
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 5, 7, 9, 9};

        List<Integer> result = maxMeetings(N, start, end);
        for (int meeting : result) {
            System.out.print(meeting + " ");
        }
    }

    public static List<Integer> maxMeetings(int N, int[] start, int[] end) {
        List<Meeting> meetings = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            meetings.add(new Meeting(start[i], end[i], i + 1));
        }

        // Sort meetings by end time, and by start time if end times are the same
        meetings.sort((o1, o2) -> {
            int endComparison = Integer.compare(o1.end, o2.end);
            if (endComparison != 0) {
                return endComparison;
            } else {
                return Integer.compare(o1.pos, o2.pos);
            }
        });

        List<Integer> selectedMeetings = new ArrayList<>();
        int lastEndTime = -1;

        for (Meeting meeting : meetings) {
            if (meeting.start > lastEndTime) {
                selectedMeetings.add(meeting.index);
                lastEndTime = meeting.end;
            }
        }

        return selectedMeetings;
    }
}

class Meeting {
    int start;
    int end;
    int index;
    int pos;

    Meeting(int start, int end, int index) {
        this.start = start;
        this.end = end;
        this.index = index;
        this.pos = index;  // Assuming position is the same as index for simplification
    }
}
