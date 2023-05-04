import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
//		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt();
			int[] f = fs.readArray(n);
			boolean[] hasInDeg = new boolean[n], hasOutDeg = new boolean[n];
			Arrays.fill(hasInDeg, false);
			Arrays.fill(hasOutDeg, false);
			Integer[] indices = new Integer[n];
			for (int i = 0; i < n; i++) {
				if (f[i] > 0) {
					hasOutDeg[i] = hasInDeg[f[i]-1] = true;  
				}
				indices[i] = i;
			}
			Arrays.sort(indices, new Comparator<Integer>() {
				@Override
				public int compare(Integer i, Integer j) {
					if (!hasInDeg[i] && !hasInDeg[j]) {
						return Boolean.compare(hasOutDeg[i], hasOutDeg[j]);
					}
					return Boolean.compare(hasInDeg[i], hasInDeg[j]);
				}
			});
//			for (int x : indices) {
//				System.out.print((x + 1) + " ");
//			}
//			System.out.println();
			ArrayDeque<Integer> q = new ArrayDeque<>();
			for (int i = 0; i < n; i++) {
				if (!hasInDeg[indices[i]]) {
					q.addLast(indices[i]);
				}
			}
			for (int i = 0; i < n; i++) {
				if (f[i] == 0 && !q.isEmpty()) {
					int front = q.pollFirst();
					if (i == front && !q.isEmpty()) {
						int newFront = q.pollFirst();
						q.addLast(front);
						front = newFront;
					}
					System.out.print((front + 1) + " ");
				} else {
					System.out.print(f[i] + " ");
				}
			}
			System.out.println();
		}
		out.close();
	}
	
	static final Random rnd = new Random();
	static void shuffleSort(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newInd = rnd.nextInt(n);
			int temp = a[newInd]; //change this
			a[newInd] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		double[] readDoubleArray(int n) {
			double[] a = new double[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextDouble();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
