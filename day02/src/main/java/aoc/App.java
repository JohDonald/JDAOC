package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {

	private static Map<Integer,Integer> map = new HashMap<>();

	static { map.put(1, 3); map.put(2, 1); map.put(3, 2);}

	public static void main(String[] args) throws IOException {
		Integer score = Files.lines(Paths.get("input.txt")).mapToInt(s ->{ int p1 = s.charAt(0) - 'A' + 1; int p2 = s.charAt(2) - 'X' + 1; int roundScore = (map.get(p2) == p1 ? 6 : (p2 == p1 ? 3 : 0)) + p2; return roundScore;}).sum();

		System.out.println(score);
	}
}