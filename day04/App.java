public class App{ public static void main(String[] args) throws java.io.IOException { String part = System.getenv("part") == null ? "part1" : System.getenv("part"); if (part.equals("part2")) System.out.println(java.nio.file.Files.lines(java.nio.file.Paths.get("input.txt")).mapToInt(s -> {String[] sArr = s.split(",");int e1Lower = Integer.parseInt(sArr[0].split("-")[0]); int e1Upper = Integer.parseInt(sArr[0].split("-")[1]);int e2Lower = Integer.parseInt(sArr[1].split("-")[0]); int e2Upper = Integer.parseInt(sArr[1].split("-")[1]);if (e1Upper >= e2Lower && e1Upper <= e2Upper || e2Upper >= e1Lower && e2Upper <= e1Upper) return 1;else return 0;}).sum()); else System.out.println(java.nio.file.Files.lines(java.nio.file.Paths.get("input.txt")).mapToInt(s -> {String[] sArr = s.split(",");int e1Lower = Integer.parseInt(sArr[0].split("-")[0]); int e1Upper = Integer.parseInt(sArr[0].split("-")[1]);int e2Lower = Integer.parseInt(sArr[1].split("-")[0]); int e2Upper = Integer.parseInt(sArr[1].split("-")[1]);if (e1Lower >= e2Lower && e1Upper <= e2Upper || e1Lower <= e2Lower && e1Upper >= e2Upper) return 1;else return 0;}).sum());}}