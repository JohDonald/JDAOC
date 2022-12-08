import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class App {
	public static void main(String[] args) throws IOException {
		List<String> input = Files.lines(Paths.get("input.txt")).collect(Collectors.toList());
		Node root = new Node("/", 0,null, false);
		List<Node> nodes = addElements(root, input);
		String part = System.getenv("part") == null ? "part1" : System.getenv("part");
		if (part.equals("part2")) System.out.println(nodes.stream().filter(n -> n.value + ((70000000 - 30000000) - root.value) >= 0).min((n1, n2) -> n1.value - n2.value).orElseThrow().value);
		else System.out.println(nodes.stream().filter(n -> n.value <= 100000).mapToInt(n -> n.value).sum());
	}

	public static List<Node> addElements(Node root, List<String> input){
		Node current = root;
		for (int i = 1; i < input.size(); i++){
			if (input.get(i).startsWith("$ cd")) {
				current = changeNode(current, input.get(i));
			}
			else if (!input.get(i).startsWith("$ ls")) {
				addNode(current, input.get(i));
			}
		}
		dfsSizes(root);
		return collectNodes(root);
	}
	public static Node changeNode(Node current, String line){
		String name = line.split(" ")[2];
		if (name.equals("..")) return current.parent;
		else return current.children.stream().filter(n -> n.name.equals(name)).findAny().orElseThrow();
	}

	public static void addNode(Node current, String line){
		String[] nameOrSize = line.split(" ");
		if (nameOrSize[0].equals("dir")) current.children.add(new Node(nameOrSize[1],0, current, false));
		else {
			current.children.add(new Node(nameOrSize[1], Integer.parseInt(nameOrSize[0]), current, true));
			current.value += Integer.parseInt(nameOrSize[0]);
		}
	}

	public static void dfsSizes(Node root){
		for (Node n : root.children) dfsSizes(n);
		if (!root.file) root.value = root.children.stream().mapToInt(no -> no.value).sum();
	}

	public static List<Node> collectNodes(Node root){
		List<Node> result = new ArrayList<>();
		for (Node n : root.children){
			if (!n.file){
				result.add(n);
				result.addAll(collectNodes(n));
			}
		}
		return result;
	}

	static class Node {
		String name;
		int value;
		boolean file;
		Node parent;
		List<Node> children = new ArrayList<>();

		public Node(String name, int value, Node parent, boolean file){
			this.name = name;
			this.value = value;
			this.parent = parent;
			this.file = file;
		}
	}
}
