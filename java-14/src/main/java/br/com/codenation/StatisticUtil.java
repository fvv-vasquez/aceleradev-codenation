package br.com.codenation;

import java.util.Arrays;
import java.util.HashMap;

public class StatisticUtil {

	public static int average(int[] elements) {
		int sum = 0;
		for (int elemento : elements) {
			sum += elemento;
		}
		return (sum/elements.length);
	}

	public static int mode(int[] elements) {
		HashMap<Integer, Integer> modeElements = new HashMap<Integer, Integer>();
		int mode = 0;
		int currentNumber = 0;
		int previousNumber = 0;

		for (int element : elements) {
			Integer checkElements = modeElements.get(element);

			if (checkElements == null) {
				modeElements.put(element, 1);
			} else {
				modeElements.put(element, checkElements + 1);
				currentNumber = checkElements + 1;

				if (currentNumber > previousNumber) {
					previousNumber = currentNumber;
					mode = element;
				}
			}
		}
		return mode;
	}

	public static int median(int[] elements) {
		Arrays.sort(elements);

		int middle = elements.length / 2;

		if (elements.length % 2 == 0)
			return (elements[middle-1] + elements[middle]) / 2;
		else
			return elements[middle];
	}
}