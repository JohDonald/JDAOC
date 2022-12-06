import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class App {
	public static void main(String[] args) throws IOException {
		String input = Files.readString(Paths.get("input.txt"));
		String part = System.getenv("part") == null ? "part1" : System.getenv("part");
		if (part.equals("part2")) {for (int i = 0; i < input.length() - 5; i++) if(allDistinct(input, i, 14)) {System.out.println(i + 14); break;}}
		else {for (int i = 0; i < input.length() - 5; i++) if(allDistinct(input, i, 4)) {System.out.println(i + 4); break;}}
	}
	private static boolean allDistinct(String input, int i, int pOrM){
		boolean[] chars = new boolean['z' - 'A' + 1];
		for (int j = i; j < i + pOrM; j++) {
			if (!chars[input.charAt(j) - 'A']) chars[input.charAt(j) - 'A'] = true;
			else return false;
		}
		return true;
	}
}