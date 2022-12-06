import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class App {
	public static void main(String[] args) throws IOException {
		String input = Files.readString(Paths.get("input.txt"));
		String part = System.getenv("part") == null ? "part1" : System.getenv("part");
		if (part.equals("part2")) {for (int i = 0; i < input.length() - 5; i++) if(allDistinctMessage(input, i)) {System.out.println(i + 14); break;}}
		else {for (int i = 0; i < input.length() - 5; i++) if(allDistinctPacket(input, i)) {System.out.println(i + 4); break;}}
	}
	private static boolean allDistinctPacket(String input, int i){
		if( input.charAt(i) != input.charAt(i+1) && input.charAt(i) != input.charAt(i+2) && input.charAt(i) != input.charAt(i+3) && input.charAt(i+1) != input.charAt(i+2) && input.charAt(i+1) != input.charAt(i+3) && input.charAt(i+2) != input.charAt(i+3)) return true;
		return false;
	}
	private static boolean allDistinctMessage(String input, int i){
		int checker = 0;
		for (int j = i; j < i + 14; j++) {
			int val = input.charAt(j) - 'a';
			if ((checker & (1 << val)) > 0) return false;
			checker |= (1 << val);
		}
		return true;
	}
}