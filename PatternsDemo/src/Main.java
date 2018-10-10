import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		List<String> words = collectWords();
		
		printWhen("Palindromes", words, new Palindrome());
		printWhen("Longer than 5", words, new LongerThan5());
		printWhen("Longer than 6", words, new LongerThanN(6));
		
		CounterDecorator counter = new CounterDecorator(new StartWithT());
		printWhen("Starts with T", words, counter);
		System.out.println("Found " + counter.count());
		
		AndStrategyComposite longWordsThatStartWithT = new AndStrategyComposite();
		longWordsThatStartWithT.addStrategy(new LongerThanN(5));
		longWordsThatStartWithT.addStrategy(new StartWithT());
		printWhen("Longer than 5 and starting with T", words, longWordsThatStartWithT);
	}

	private static void printWhen(String header, List<String> words, CheckStrategy checker) {
		System.out.println(header);
		for (String word : words) {
			if (checker.check(word)) {
				System.out.println(word);
			}
		}
		System.out.println();
	}

	private static List<String> collectWords() throws IOException {
		List<String> words = new ArrayList<>();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = in.readLine();
		while (line != null) {
			StringTokenizer st = new StringTokenizer(line);
			while (st.hasMoreTokens()) {
				String word = st.nextToken();
				words.add(word);
			}
		}
		
		return words;
	}
}
