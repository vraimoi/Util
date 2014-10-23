package math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 与えられた文字列（重複可能）の組合せを求める
 * @author S.Yamashita
 *
 */
public class PermutationGenerator {
	protected String pattern;
	protected int len;
	protected char[] result;
	protected Set<String> resultSet;

	public PermutationGenerator(String pattern, int len) {
		this.pattern = pattern;
		this.len = len;
		this.result = new char[len];
		this.resultSet = new HashSet<String>();
	}

	public String getPattern() {
		return pattern;
	}
	public int getLen() {
		return len;
	}
	public char[] getResult() {
		return result;
	}

	private void generate(String str, int len, int startPosition) {
		if (0 == len) {
			resultSet.add(String.valueOf(result));
			return;
		}
		for (int i = 0; i < str.length(); i++) {
			result[result.length - len] = str.charAt(i);
			String next = str.substring(0, i) + str.substring(i + 1);
			generate(next, (len - 1), (i + 1));
		}
	}

	public void execute() {
		generate(pattern, len, 0);
	}

	public Set<String> executeAndGetResultSet() {
		execute();
		return resultSet;
	}

	public static void main(String[] args) {
		// テスト
		String p1 = "ABCD";
		Set<String> permSet;
		String filename = "perm.txt";
		File file = new File(filename);
		if (! file.exists()) {
			PermutationGenerator pg1 = new PermutationGenerator(p1, 3);
			permSet = pg1.executeAndGetResultSet();
			try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
				for (String target : permSet) {
					pw.println(target);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.print("Exists last result of Permutations for " + p1 + "\n");

			permSet = new HashSet<String>();
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String str = br.readLine();
				while (null != str) {
					permSet.add(str);
					str = br.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.print("Sorted Permutations for " + p1 + " are: \n");
		SortedSet<String> sortedSet = new TreeSet<String>(permSet);
		for (String str : sortedSet) {
			System.out.print(str + " ");
		}
		System.out.print("\n");
	}
}
