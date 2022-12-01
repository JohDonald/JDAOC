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
		List<Integer> test = Files.lines(Paths.get("input.txt")).reduce(new ArrayList<List<Integer>>(), (list, cals) -> {if (list.isEmpty()) {list.add(new ArrayList<>());}if (cals.length() == 0) {list.add(new ArrayList<>());} else {list.get(list.size() - 1).add(Integer.parseInt(cals));}return list;}, (list1, list2) -> {list1.addAll(list2);return list1;}).stream().reduce(new ArrayList<Integer>(), (list, calsList) -> {list.add(calsList.stream().collect(Collectors.summingInt(Integer::intValue)));return list;}).stream().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());
		System.out.println("Part 1: " + test.get(0));
		Integer test1 = test.stream().collect(Collectors.summingInt(Integer::intValue));
		System.out.println("Part 2: " + test1);
	}

	private static void part2() throws IOException {
		List<Integer> maxs = new ArrayList<>();
		List<String> input = parseInput("input.txt");
		int sum = 0;
		for (int i = 0; i < input.size(); i++){
			if(input.get(i).equals("")){
				maxs.add(sum);
				sum = 0; continue;
			}
			sum += Integer.parseInt(input.get(i));
		}

		Integer top3 = maxs.stream().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.summingInt(Integer::intValue));
		System.out.println("Top3: " + top3);
	}

	private static void part1() throws IOException {
		List<String> input = parseInput("input.txt");
		int maxCals = 0, elf = 0, sum = 0, currElf = 0;
		for (int i = 0; i < input.size(); i++){
			if(input.get(i).equals("")){
				if(sum > maxCals) { elf = currElf; maxCals = sum; }
				sum = 0; currElf++; continue;
			}
			sum += Integer.parseInt(input.get(i));
		}
		System.out.println("Elf: " + elf + " Calories: " + maxCals);
	}
	private static List<String> parseInput(String fileName) throws IOException {
		return Files.lines(Paths.get(fileName)).collect(Collectors.toList());
	}
}
