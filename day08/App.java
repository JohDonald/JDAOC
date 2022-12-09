import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class App{
	public static void main(String[] args) throws IOException {
		List<char[]> grid = Files.lines(Paths.get("input.txt")).map(s -> s.toCharArray()).collect(Collectors.toList());
		String part = System.getenv("part") == null ? "part1" : System.getenv("part");
		if (part.equals("part2")) System.out.println(highestScenicScore(grid));
		else{
			boolean[][][] hiddenAndVisited = new boolean[grid.size()][grid.get(0).length][2];
			for (int i = 1; i < grid.size() - 1; i++){
				for (int j = 1; j < grid.get(0).length - 1; j++){
					if (!hiddenAndVisited[i][j][1] && hiddenHorizontal(i,j,grid) && hiddenVertical(i,j,grid)) dfs(i, j, grid, hiddenAndVisited);
				}
			}
			int sumHidden = 0;
			for (int i = 1; i < grid.size() - 1; i++) for (int j = 1; j < grid.get(0).length - 1; j++) if(hiddenAndVisited[i][j][0]) sumHidden++;
			System.out.println(grid.size()*grid.get(0).length-sumHidden);
		}
	}

	public static void dfs(int i, int j, List<char[]> grid, boolean[][][] hiddenAndVisited){
		if ((i >= grid.size() - 1) || (i <= 0) || (j >= grid.get(0).length - 1) || (j <= 0) || hiddenAndVisited[i][j][1]
				|| !hiddenVertical(i,j,grid) || !hiddenHorizontal(i,j,grid)) return;
		//visit
		hiddenAndVisited[i][j][0] = hiddenAndVisited[i][j][1] = true;
		//recurse into up down left right
		dfs(i - 1, j, grid, hiddenAndVisited);
		dfs(i + 1, j, grid, hiddenAndVisited);
		dfs(i, j - 1, grid, hiddenAndVisited);
		dfs(i, j + 1, grid, hiddenAndVisited);
	}

	public static boolean hiddenVertical(int i, int j, List<char[]> grid){
		boolean up = false;
		boolean down = false;
		for (int v = 0; v < i; v++) if(Character.getNumericValue(grid.get(v)[j]) >= Character.getNumericValue(grid.get(i)[j])){up = true; break;}
		for (int v = i + 1; v < grid.size(); v++) if(Character.getNumericValue(grid.get(v)[j]) >= Character.getNumericValue(grid.get(i)[j])){down = true & up; break;}
		return down;
	}

	public static boolean hiddenHorizontal(int i, int j, List<char[]> grid){
		boolean left = false;
		boolean right = false;
		for (int h = 0; h < j; h++) if(Character.getNumericValue(grid.get(i)[h]) >= Character.getNumericValue(grid.get(i)[j])) { left = true; break;}
		for (int h = j + 1; h < grid.get(0).length ; h++) if(Character.getNumericValue(grid.get(i)[h]) >= Character.getNumericValue(grid.get(i)[j])){ right = true & left; break;}
		return right;
	}

	public static int highestScenicScore(List<char[]> grid){
		int[][][] upLeftDownRightSight = new int[grid.size()][grid.get(0).length][4];
		for (int i = 1, y = grid.size() - 2; i < grid.size() - 1 && y > 0; i++, y--){
			for (int j = 1, x = grid.get(0).length - 2; j < grid.get(0).length - 1 && x > 0; j++, x--){
				viewUpDP(i, j, grid, upLeftDownRightSight); viewLeftDP(i, j, grid, upLeftDownRightSight);
				viewDownDP(y, x, grid, upLeftDownRightSight); viewRightDP(y, x, grid, upLeftDownRightSight);
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = 1; i < grid.size() - 1; i++){
			for (int j = 1; j < grid.get(0).length - 1; j++){
				max = Math.max(max, upLeftDownRightSight[i][j][0] * upLeftDownRightSight[i][j][1] * upLeftDownRightSight[i][j][2] * upLeftDownRightSight[i][j][3]);
			}
		}
		return max;
	}

	public static int viewUpDP(int i, int j, List<char[]> grid, int[][][] upLeftDownRightSight){
		int u = i - 1;
		while (u > 0 && (Character.getNumericValue(grid.get(u)[j]) < Character.getNumericValue(grid.get(i)[j]))) u -= upLeftDownRightSight[u][j][0];
		upLeftDownRightSight[i][j][0] = i - u;
		//if full length and last coordinate >= current height
		return upLeftDownRightSight[i][j][0];
	}

	public static int viewLeftDP(int i, int j, List<char[]> grid, int[][][] upLeftDownRightSight){
		int l = j - 1;
		while (l > 0 && (Character.getNumericValue(grid.get(i)[l]) < Character.getNumericValue(grid.get(i)[j]))) l -= upLeftDownRightSight[i][l][1];
		upLeftDownRightSight[i][j][1] = j - l;
		return upLeftDownRightSight[i][j][1];
	}

	public static int viewDownDP(int y, int x, List<char[]> grid, int[][][] upLeftDownRightSight){
		int d = y + 1;
		while( d < grid.size() - 1 && (Character.getNumericValue(grid.get(d)[x]) < Character.getNumericValue(grid.get(y)[x]))) d += upLeftDownRightSight[d][x][2];
		upLeftDownRightSight[y][x][2] = d - y;
		return upLeftDownRightSight[y][x][2];
	}

	public static int viewRightDP(int y, int x, List<char[]> grid, int[][][] upLeftDownRightSight){
		int r = x + 1;
		while(r < grid.get(0).length - 1 && (Character.getNumericValue(grid.get(y)[r]) < Character.getNumericValue(grid.get(y)[x]))) r += upLeftDownRightSight[y][r][3];
		upLeftDownRightSight[y][x][3] = r - x;
		return upLeftDownRightSight[y][x][3];
	}
}