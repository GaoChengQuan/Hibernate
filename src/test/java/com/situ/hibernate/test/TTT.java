package com.situ.hibernate.test;

import java.util.Arrays;

import org.junit.Test;

public class TTT {
	@Test
	public void bubbleSort() {
		int[] scores={0,0,1,2,3,5,4,5,2,8,7,6,9,5,4,8,3,1,0,2,4,8,7,9,5,2,1,2,3,9};
		for (int i = 1; i <= scores.length - 1; i++) {
			for (int j = 0; j < scores.length - i; j++) {
				if (scores[j] > scores[j + 1]) {
					int temp = scores[j];
					scores[j] = scores[j + 1];
					scores[j + 1] = temp;
				}
			}
		}
		
		System.out.println(Arrays.toString(scores));
	}
	
	@Test
	public void search() {
		 int[] array = new int[] { 8, 17, 19, 37, 40, 73, 79, 82, 87, 95, 97, 98 };
		 int search = 95;
		 int index = binarySearch(array, search);
		 System.out.println(index);
	}

	/**
	 * 返回-1，表示没有
	 * >=0 返回下标
	 * @param array
	 * @param target
	 * @return
	 */
	private int binarySearch(int[] array, int search) {
		int low = 0;
		int high = array.length - 1;
		int mid = (low + high) / 2;
		
		while (low <= high) {
			if (array[mid] == search) {
				return mid;//找到情况
			} else if (array[mid] < search) {//说明要查找的值在右半部分
				low = mid + 1;
			} else if (array[mid] > search) {//说明要查找的值在左半部分
				high = mid - 1;
			}
			mid = (low + high) / 2;
		}
		
		return -1;//找不到情况
	}
	
	
	
	
}
