public class Palindrome implements CheckStrategy {
	public boolean check(String s) {
		if (s == null)
			return false;
		int length = s.length();
		if (length < 2)
			return true;
		int half = length / 2;
		for (int i = 0; i < half; ++i)
			if (s.charAt(i) != s.charAt(length - 1 - i))
				return false;
		return true;
	}
}
