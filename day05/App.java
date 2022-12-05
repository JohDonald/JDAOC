import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {
	public static void main(String[] args) throws IOException {
		List<List<String>> input = Files.lines(Paths.get("input.txt")).reduce(new ArrayList<List<String>>(), (list, s) -> {if (list.isEmpty()) list.add(new ArrayList<>());if (s.length() == 0) list.add(new ArrayList<>());else list.get(list.size() - 1).add(s);return list;}, (list1, list2) -> {list1.addAll(list2);return list1;});
		String cratePilesNo = input.get(0).get(input.get(0).size()-1);
		int cratesNo = Character.getNumericValue(cratePilesNo.charAt(cratePilesNo.length()-2));
		List<StringBuilder> cratePiles = new ArrayList<>();
		for (int i = 0; i < cratesNo; i++) cratePiles.add(new StringBuilder());
		for (int i = input.get(0).size()-2; i >= 0; i--){
			String[] crates = input.get(0).get(i).replace("    ", " ").split(" ");
			for (int j = 0; j < cratesNo; j++) if (!crates[j].equals("")) cratePiles.get(j).append(crates[j].charAt(1));
		}
		String part = System.getenv("part") == null ? "part1" : System.getenv("part");
		if (part.equals("part2")){
			input.get(1).stream().forEach(s -> {String[] splitInstruction = s.split(" "); int move = Integer.parseInt(splitInstruction[1]); int from = Integer.parseInt(splitInstruction[3]); int to = Integer.parseInt(splitInstruction[5]); String value = cratePiles.get(from - 1).substring(cratePiles.get(from - 1).length() - move, cratePiles.get(from - 1).length()); cratePiles.get(to - 1).append(value); cratePiles.get(from - 1).delete(cratePiles.get(from - 1).length() - move, cratePiles.get(from - 1).length());});
			StringBuilder topStack9001 = new StringBuilder();
			for (int i = 0; i < cratePiles.size(); i++) topStack9001.append(cratePiles.get(i).charAt(cratePiles.get(i).length()-1));
			System.out.println(topStack9001);
		}
		else{
			input.get(1).stream().forEach(s -> {String[] splitInstruction = s.split(" "); int move = Integer.parseInt(splitInstruction[1]); int from = Integer.parseInt(splitInstruction[3]); int to = Integer.parseInt(splitInstruction[5]); for (int i = move; i > 0; i--){ char value = cratePiles.get(from - 1).charAt(cratePiles.get(from - 1).length()-1); cratePiles.get(to - 1).append(value); cratePiles.get(from - 1).deleteCharAt(cratePiles.get(from - 1).length()-1);}});
			StringBuilder topStack = new StringBuilder();
			for (int i = 0; i < cratePiles.size(); i++) topStack.append(cratePiles.get(i).charAt(cratePiles.get(i).length()-1));
			System.out.println(topStack);
		}
	}
}