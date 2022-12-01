package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class App {
	public static void main(String[] args) throws IOException{
		List<Integer> topThree = Files.lines(Paths.get("input.txt")).reduce(new ArrayList<List<Integer>>(), (list, cals) -> {if (list.isEmpty()) {list.add(new ArrayList<>());}if (cals.length() == 0) {list.add(new ArrayList<>());} else {list.get(list.size() - 1).add(Integer.parseInt(cals));}return list;}, (list1, list2) -> {list1.addAll(list2);return list1;}).stream().reduce(new ArrayList<Integer>(), (list, calsList) -> {list.add(calsList.stream().collect(Collectors.summingInt(Integer::intValue)));return list;}).stream().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());
		String part = System.getenv("part") == null ? "part1" : System.getenv("part");
		if (part.equals("part2")) System.out.println(topThree.stream().collect(Collectors.summingInt(Integer::intValue)));
		else System.out.println(topThree.get(0));
	}
}
