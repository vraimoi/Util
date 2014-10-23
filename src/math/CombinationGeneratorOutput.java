package math;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CombinationGeneratorOutput extends CombinationGenerator {
	private String filename;
	private PrintWriter pw;

	public CombinationGeneratorOutput(String pattern, int len, String filename) {
		super(pattern, len);
		this.filename = filename;
	}

	private void generate(int len, int startPosition) {
		if (0 == len) {
			pw.println(result);
			return;
		}
		for (int i = startPosition; i <= pattern.length() - len; i++) {
			result[result.length - len] = pattern.charAt(i);
			generate((len - 1), (i + 1));
		}
	}

	public void execute() {
		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
			this.pw = pw;
			generate(len, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// テスト
		String s1 = "ABCDE";
		String filename = "comb_output.txt";
		CombinationGeneratorOutput cg1 = new CombinationGeneratorOutput(s1, 3, filename);
		cg1.execute();
		System.out.print("Combinations for " + s1 + " are written in " + filename + "\n");
		System.out.print("\n");
	}
}
