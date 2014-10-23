package math;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PermutationGeneratorOutput extends PermutationGenerator {
	private String filename;
	private PrintWriter pw;

	public PermutationGeneratorOutput(String pattern, int len, String filename) {
		super(pattern, len);
		this.filename = filename;
	}

	private void generate(String str, int len, int startPosition) {
		if (0 == len) {
			pw.println(result);
			return;
		}
		for (int i = 0; i < str.length(); i++) {
			result[result.length - len] = str.charAt(i);
			String next = str.substring(0, i) + str.substring(i + 1);
			generate(next, (len - 1), (i + 1));
		}
	}

	public void execute() {
		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
			this.pw = pw;
			generate(pattern, len, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// テスト
		String p1 = "ABCD";
		String filename = "perm_output.txt";
		PermutationGeneratorOutput pg1 = new PermutationGeneratorOutput(p1, 3, filename);
		pg1.execute();
		System.out.print("Permutations for " + p1 + " are written in " + filename + "\n");
		System.out.print("\n");
	}
}
