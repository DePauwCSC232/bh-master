public class StartWithT implements CheckStrategy {
	public boolean check(String s) {
		if (s == null || s.length() == 0)
			return false;
		return s.charAt(0) == 't';
	}
}
