package com.carrefour.kata;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Yatzy {

	protected int[] dice;

	public Yatzy(int d1, int d2, int d3, int d4, int d5) {
		dice = new int[] { d1, d2, d3, d4, d5 };
	}

	public static int chance(int d1, int d2, int d3, int d4, int d5) {

		return d1 + d2 + d3 + d4 + d5;
	}

	public static int yatzy(int... dice) {

		return Arrays.stream(dice).distinct().count() == 1 ? 50 : 0;
	}

	public static int ones(int d1, int d2, int d3, int d4, int d5) {

		int[] onesIn = new int[] { d1, d2, d3, d4, d5 };
		return (int) (Arrays.stream(onesIn).filter(s -> s == 1).count());

	}

	public static int twos(int d1, int d2, int d3, int d4, int d5) {

		int[] twosIn = new int[] { d1, d2, d3, d4, d5 };
		return (int) (Arrays.stream(twosIn).filter(s -> s == 2).count() * 2);

	}

	public static int threes(int d1, int d2, int d3, int d4, int d5) {

		int[] threesIn = new int[] { d1, d2, d3, d4, d5 };
		return (int) (Arrays.stream(threesIn).filter(s -> s == 3).count() * 3);
	}

	public int fours() {
		return (int) (Arrays.stream(dice).filter(s -> s == 4).count() * 4);

	}

	public int fives() {
		return (int) (Arrays.stream(dice).filter(s -> s == 5).count() * 5);
	}

	public int sixes() {
		return (int) (Arrays.stream(dice).filter(s -> s == 6).count() * 6);
	}

	public static int scorePair(int d1, int d2, int d3, int d4, int d5) {
		Integer max = 0;
		Map<Integer, Long> map = Arrays.stream(new Integer[] { d1, d2, d3, d4, d5 })
				.collect(Collectors.groupingBy(s -> s, Collectors.counting()));

		for (Map.Entry<Integer, Long> entry : map.entrySet()) {
			if (entry.getValue() >= 2 && entry.getKey() > max) {
				max = entry.getKey();
			}
		}
		return max * 2;

	}

	public static int twoPair(int d1, int d2, int d3, int d4, int d5) {
		Integer[] intsIn = new Integer[] { d1, d2, d3, d4, d5 };
		int firstMaxPaire = Yatzy.scorePair(d1, d2, d3, d4, d5);

		List<Integer> listOut = Arrays.stream(intsIn).map(s -> s == firstMaxPaire / 2 ? 0 : s)
				.collect(Collectors.toList());

		int secondMaxPaire = Yatzy.scorePair(listOut.get(0), listOut.get(1), listOut.get(2), listOut.get(3),
				listOut.get(4));
		return firstMaxPaire + secondMaxPaire;
	}

	public static int fourOfaKind(int d1, int d2, int d3, int d4, int d5) {
		Map<Integer, Long> map = Arrays.stream(new Integer[] { d1, d2, d3, d4, d5 })
				.collect(Collectors.groupingBy(s -> s, Collectors.counting()));

		for (Map.Entry<Integer, Long> entry : map.entrySet()) {
			if (entry.getValue() >= 4) {
				return entry.getKey() * 4;
			}
		}

		return 0;
	}

	public static int threeOfaKind(int d1, int d2, int d3, int d4, int d5) {
		Map<Integer, Long> map = Arrays.stream(new Integer[] { d1, d2, d3, d4, d5 })
				.collect(Collectors.groupingBy(s -> s, Collectors.counting()));

		for (Map.Entry<Integer, Long> entry : map.entrySet()) {
			if (entry.getValue() >= 3) {
				return entry.getKey() * 3;
			}
		}

		return 0;
	}

	public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
		int[] intsIn = new int[] { d1, d2, d3, d4, d5 };
		Arrays.sort(intsIn);
		for (int i = 0; i < 5; i++) {
			if (i + 1 != intsIn[i])
				return 0;
		}

		return 15;
	}

	public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
		int[] intsIn = new int[] { d1, d2, d3, d4, d5 };
		Arrays.sort(intsIn);
		int indexDeux = Arrays.binarySearch(intsIn, 2);
		if (indexDeux > 1 || Arrays.stream(intsIn).distinct().count() < 4)
			return 0;

		int[] intsOut = Arrays.copyOfRange(intsIn, indexDeux, intsIn.length);
		for (int i = 0; i < intsOut.length; i++) {
			if (i + 1 != intsIn[i] - 1)
				return 0;
		}

		return 20;
	}

	public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {
		int threeOfaKindValue = threeOfaKind(d1, d2, d3, d4, d5);
		int scorePairValue = scorePair(d1, d2, d3, d4, d5);
		return threeOfaKindValue != 0 && scorePairValue != 0 ? threeOfaKindValue + scorePairValue : 0;
	}
}
