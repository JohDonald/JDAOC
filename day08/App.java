import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class App{
	public static void main(String[] args) throws IOException {
		List<char[]> grid = Files.lines(Paths.get("input.txt")).map(s -> s.toCharArray()).collect(Collectors.toList());
		String part = System.getenv("part") == null ? "part1" : System.getenv("part");

		int[][][] upLeftDownRightSight = new int[grid.size()][grid.get(0).length][8];

		for (int i = 1, y = grid.size() - 2; i < grid.size() - 1 && y > 0; i++, y--){
			for (int j = 1, x = grid.get(0).length - 2; j < grid.get(0).length - 1 && x > 0; j++, x--){
				viewUpDP(i, j, grid, upLeftDownRightSight); viewLeftDP(i, j, grid, upLeftDownRightSight);
				viewDownDP(y, x, grid, upLeftDownRightSight); viewRightDP(y, x, grid, upLeftDownRightSight);
			}
		}
		if (part.equals("part2")){
			int max = Integer.MIN_VALUE;
			for (int i = 1; i < grid.size() - 1; i++){
				for (int j = 1; j < grid.get(0).length - 1; j++){
					max = Math.max(max, upLeftDownRightSight[i][j][0] * upLeftDownRightSight[i][j][1] * upLeftDownRightSight[i][j][2] * upLeftDownRightSight[i][j][3]);
				}
			}
			System.out.println(max);
		}else{
			int visibleTrees = grid.size() * grid.get(0).length;
			for (int i = 1; i < grid.size() - 1; i++){
				for (int j = 1; j < grid.get(0).length - 1; j++){
					if ((upLeftDownRightSight[i][j][4] + upLeftDownRightSight[i][j][5]
							+ upLeftDownRightSight[i][j][6] + upLeftDownRightSight[i][j][7]) == 4) visibleTrees--;
				}
			}
			System.out.println(visibleTrees);
		}
	}
	public static void viewUpDP(int i, int j, List<char[]> grid, int[][][] upLeftDownRightSight){
		int u = i - 1;
		while (u > 0 && (Character.getNumericValue(grid.get(u)[j]) < Character.getNumericValue(grid.get(i)[j]))) u -= upLeftDownRightSight[u][j][0];
		upLeftDownRightSight[i][j][0] = i - u;
		upLeftDownRightSight[i][j][4] = (i - u == i ? (grid.get(0)[j] >= grid.get(i)[j] ? 1 : 0) : 1);
	}
	public static void viewLeftDP(int i, int j, List<char[]> grid, int[][][] upLeftDownRightSight){
		int l = j - 1;
		while (l > 0 && (Character.getNumericValue(grid.get(i)[l]) < Character.getNumericValue(grid.get(i)[j]))) l -= upLeftDownRightSight[i][l][1];
		upLeftDownRightSight[i][j][1] = j - l;
		upLeftDownRightSight[i][j][5] = (j - l == j ? (grid.get(i)[0] >= grid.get(i)[j] ? 1 : 0) : 1);
	}
	public static void viewDownDP(int y, int x, List<char[]> grid, int[][][] upLeftDownRightSight){
		int d = y + 1;
		while( d < grid.size() - 1 && (Character.getNumericValue(grid.get(d)[x]) < Character.getNumericValue(grid.get(y)[x]))) d += upLeftDownRightSight[d][x][2];
		upLeftDownRightSight[y][x][2] = d - y;
		upLeftDownRightSight[y][x][6] = (d == grid.size() - 1 ? (grid.get(grid.size() - 1)[x] >= grid.get(y)[x] ? 1 : 0) : 1);
	}
	public static void viewRightDP(int y, int x, List<char[]> grid, int[][][] upLeftDownRightSight){
		int r = x + 1;
		while(r < grid.get(0).length - 1 && (Character.getNumericValue(grid.get(y)[r]) < Character.getNumericValue(grid.get(y)[x]))) r += upLeftDownRightSight[y][r][3];
		upLeftDownRightSight[y][x][3] = r - x;
		upLeftDownRightSight[y][x][7] = (r == grid.get(0).length - 1 ? (grid.get(y)[grid.get(0).length - 1] >= grid.get(y)[x] ? 1 : 0) : 1);
	}
}