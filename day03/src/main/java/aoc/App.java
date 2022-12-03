package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class App {
	public static void main(String[] args) throws IOException {
		String part = System.getenv("part") == null ? "part1" : System.getenv("part");
		if (part.equals("part2")) {
			AtomicInteger counter = new AtomicInteger();
			System.out.println(Files.lines(Paths.get("input.txt")).collect(Collectors.groupingBy(it -> counter.getAndIncrement() / 3)).values().stream().mapToInt(l -> {Set<Character> charSet1 = new HashSet<>();Set<Character> charSet2 = new HashSet<>();Set<Character> charSet3 = new HashSet<>();for (Character c: l.get(0).toCharArray()){ charSet1.add(c);}for (Character c: l.get(1).toCharArray()){ charSet2.add(c);}for (Character c: l.get(2).toCharArray()){ charSet3.add(c);}charSet1.retainAll(charSet2);charSet1.retainAll(charSet3);Character badge = charSet1.toArray(new Character[0])[0];return Character.isLowerCase(badge) ? (badge - 'a') + 1 : (badge - 'A') + 27;}).sum());
		} else System.out.println(Files.lines(Paths.get("input.txt")).mapToInt(s -> {int p = 0; int mid = s.length()/2; Set<Character> charSet = new HashSet<>(); for (int i = 0; i < mid; i++) charSet.add(s.charAt(i)); for (int i = mid; i < s.length(); i++){ if (charSet.contains(s.charAt(i))){charSet.remove(s.charAt(i)); p += Character.isLowerCase(s.charAt(i)) ? (s.charAt(i) - 'a') + 1 : (s.charAt(i) - 'A') + 27;}} return p;}).sum());
	}
}