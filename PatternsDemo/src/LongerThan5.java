public class LongerThan5 implements CheckStrategy {
	public boolean check(String s) {
		if (s == null)
			return false;
		return s.length() > 5;
	}
}