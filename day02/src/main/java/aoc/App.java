package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App {

	private static Map<Integer,Integer> map = new HashMap<>();
	private static Map<Integer,Integer> map1 = new HashMap<>();

	static { map.put(1, 3); map.put(2, 1); map.put(3, 2);}
	static {map1.put(3, 1); map1.put(1, 2); map1.put(2, 3);}

	public static void main(String[] args) throws IOException {
		String part = System.getenv("part") == null ? "part1" : System.getenv("part");
		Stream<String> input = Files.lines(Paths.get("input.txt"));
		if (part.equals("part2")) System.out.println(input.mapToInt(s ->{int p1 = s.charAt(0) - 'A' + 1; int p2 = s.charAt(2) == 'X' ? map.get(p1) : (s.charAt(2) == 'Y' ? p1 : map1.get(p1)); int roundScore = (map.get(p2) == p1 ? 6 : (p2 == p1 ? 3 : 0)) + p2; return roundScore;}).sum());
		else System.out.println(input.mapToInt(s ->{ int p1 = s.charAt(0) - 'A' + 1; int p2 = s.charAt(2) - 'X' + 1; int roundScore = (map.get(p2) == p1 ? 6 : (p2 == p1 ? 3 : 0)) + p2; return roundScore;}).sum());
	}
}