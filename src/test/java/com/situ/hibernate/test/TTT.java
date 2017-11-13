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
}
