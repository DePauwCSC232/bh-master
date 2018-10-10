public class LongerThanN implements CheckStrategy {
	public LongerThanN(int n) {
		this.n = n;
	}

	public boolean check(String s) {
		if (s == null)
			return false;
		return s.length() > n;
	}

	private int n;
}