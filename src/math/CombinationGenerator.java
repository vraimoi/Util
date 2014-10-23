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
 * 与えられた文字列（重複不可）の組合せを求める
 * @author S.Yamashita
 *
 */
public class CombinationGenerator {
	protected String pattern;
	protected int len;
	protected char[] result;
	protected Set<String> resultSet;

	public CombinationGenerator(String pattern, int len) {
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

	private void generate(int len, int startPosition) {
		if (0 == len) {
			resultSet.add(String.valueOf(result));
			return;
		}
		for (int i = startPosition; i <= pattern.length() - len; i++) {
			result[result.length - len] = pattern.charAt(i);
			generate((len - 1), (i + 1));
		}
	}

	public void execute() {
		generate(len, 0);
	}

	public Set<String> executeAndGetResultSet() {
		execute();
		return resultSet;
	}

	public static void main(String[] args) {
		// テスト
		String s1 = "ABCDE";
		Set<String> combSet;
		String filename = "comb.txt";
		File file = new File(filename);
		if (! file.exists()) {
			CombinationGenerator cg1 = new CombinationGenerator(s1, 3);
			combSet = cg1.executeAndGetResultSet();
			try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
				for (String target : combSet) {
					pw.println(target);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.print("Exists last result of Combinations for " + s1 + "\n");

			combSet = new HashSet<String>();
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String str = br.readLine();
				while (null != str) {
					combSet.add(str);
					str = br.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.print("Sorted Combinations for " + s1 + " are: \n");
		SortedSet<String> sortedSet = new TreeSet<String>(combSet);
		for (String str : sortedSet) {
			System.out.print(str + " ");
		}
		System.out.print("\n");
	}
}
