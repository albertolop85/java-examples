package examples;

import java.util.Arrays;

public class MergeIntervals {
	
	public static void main(String[] args) {
		
		Interval arr[] = new Interval[8];
		arr[0] = new Interval(6, 8);
		arr[1] = new Interval(1, 9);
		arr[2] = new Interval(2, 4);
		arr[3] = new Interval(4, 7);
		arr[4] = new Interval(10, 17);
		arr[5] = new Interval(20, 25);
		arr[6] = new Interval(24, 28);
		arr[7] = new Interval(30, 30);
		
        mergeIntervals(arr);
	}

	private static void mergeIntervals(Interval[] arr) {
		
		Arrays.sort(arr, (a,b) -> (a.start - b.start));
		
		int currentIndex = 0;
		
		for (int i = currentIndex; i < arr.length; i++, currentIndex++) {
			
			if (arr[currentIndex] == null || arr[currentIndex + 1] == null) {
				continue;
			}
			
			if (arr[currentIndex].end < arr[currentIndex + 1].start) {
				
				continue;
				
			} else {
				
				if (arr[currentIndex].end < arr[currentIndex + 1].end) {
					arr[currentIndex].end = arr[currentIndex + 1].end;
				}
				
				for (int idx = currentIndex + 1; idx < arr.length - 1; idx++) {
					arr[idx] = arr[idx + 1];
					arr[idx + 1] = null;
				}
				
				currentIndex--;
			}
		}
		
		Interval[] result = Arrays.copyOfRange(arr, 0, currentIndex);
		for (Interval interval : result) {
			System.out.println(interval);
		}
	}
}

class Interval {

	int start, end;

	Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public String toString() {
		return String.format("(%d,%d) ", start, end);
	}
}