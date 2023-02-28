import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 2차원 배열을 탐색하며 구역을 구분하는 문제
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		char[][] pixcell = new char[n][n];
		for (int i = 0; i < pixcell.length; i++) {
			String st = br.readLine();
			for (int j = 0; j < pixcell.length; j++) {
				pixcell[i][j] = st.charAt(j);
			}
		}
		// main
		isVisited1 = new boolean[n][n];
		isVisited2 = new boolean[n][n];
		int res1 = 0;
		int res2 = 0;
		for (int i = 0; i < pixcell.length; i++) {
			for (int j = 0; j < pixcell.length; j++) {
				if (!isVisited1[i][j]) {
					isVisited1[i][j] = true;
					dfs1(pixcell, i, j, pixcell[i][j]);
					res1++;
				}
			}
		}
		for (int i = 0; i < pixcell.length; i++) {
			for (int j = 0; j < pixcell.length; j++) {
				if (!isVisited2[i][j]) {
					isVisited2[i][j] = true;
					dfs2(pixcell, i, j, pixcell[i][j]);
					res2++;
				}
			}
		}
		// out
		System.out.println(res1);
		System.out.println(res2);
	}

	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };
	static boolean isVisited1[][];
	static boolean isVisited2[][];

	private static void dfs1(char[][] p, int x, int y, char rgb) {
		for (int i = 0; i < 4; i++) {
			int nextx = x + dx[i];
			int nexty = y + dy[i];
			if (nextx >= 0 && nexty >= 0 && nextx < n && nexty < n && p[nextx][nexty] == rgb
					&& !isVisited1[nextx][nexty]) {
				isVisited1[nextx][nexty] = true;
				dfs1(p, nextx, nexty, rgb);
			}
		}
	}

	private static void dfs2(char[][] p, int x, int y, char rgb) {
		for (int i = 0; i < 4; i++) {
			int nextx = x + dx[i];
			int nexty = y + dy[i];
			if (nextx >= 0 && nexty >= 0 && nextx < n && nexty < n && !isVisited2[nextx][nexty]) {
				if ((rgb == 'G' || rgb == 'R') && (p[nextx][nexty] == 'G' || p[nextx][nexty] == 'R')) {
					isVisited2[nextx][nexty] = true;
					dfs2(p, nextx, nexty, rgb);
				}
				else if(rgb == p[nextx][nexty]) {
					isVisited2[nextx][nexty] = true;
					dfs2(p, nextx, nexty, rgb);
				}
				
			}
		}
	}
}