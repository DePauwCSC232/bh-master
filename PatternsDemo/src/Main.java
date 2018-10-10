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
		printWhen("Starts with t", words, counter);
		System.out.println("Found " + counter.count());
		System.out.println();
		
		AndStrategyComposite longWordsThatStartWithT = new AndStrategyComposite();
		longWordsThatStartWithT.addStrategy(new LongerThanN(5));
		longWordsThatStartWithT.addStrategy(new StartWithT());
		printWhen("Longer than 5 and starting with t", words, longWordsThatStartWithT);

		CheckStrategy startsWithEitherT = new LowercaseDecorator(new StartWithT());
		printWhen("Starts with T or t", words, startsWithEitherT);
		
		AndStrategyComposite TPalindromesLongerThan3 = new AndStrategyComposite();
		TPalindromesLongerThan3.addStrategy(new LongerThanN(3));
		TPalindromesLongerThan3.addStrategy(new Palindrome());
		TPalindromesLongerThan3.addStrategy(startsWithEitherT);
		printWhen("Longer than 3 palindromes and starting with T or t", words, TPalindromesLongerThan3);
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
			line = in.readLine();
		}
		
		return words;
	}
}
