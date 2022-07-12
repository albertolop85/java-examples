package examples;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinarySearch {
	
   public static void main(String[] args) {
	   
	   // Initial unsorted array
	   Integer array[] = { 56, 33, 88, 34, 67, 89, 98, 38, 12, 17, 84, 67, 8, 79, 39 }; // Arbitrary values
	   
	   List<Integer> list = Arrays.asList(array);
	   Collections.sort(list);
	   
	   System.out.println(list);
	   
	   // Value to find
	   int number = 33; 
	   
	   int index = BinarySearchAlgorithm(list, number);
	   
	   System.out.println("Index for number: " + index);
   }
   
   public static int BinarySearchAlgorithm(List<Integer> list, int number) {
	   
	   int startIndex = 0;
	   int endIndex = list.size() - 1;
	   
	   int index = (startIndex + endIndex) / 2;
	   
	   do {
		   
		   System.out.println("Current index: " + index);
		   
		   if (list.get(index) < number) {
			   startIndex = index + 1;
		   } else if (list.get(index) > number) {
			   endIndex = index - 1;
		   } else {
			   return index;
		   }
		   
		   index = (startIndex + endIndex) / 2;
		   
	   } while (index > startIndex && index < endIndex);
	   
	   return index;
   }
}