public class CounterDecorator implements CheckStrategy {
	public CounterDecorator(CheckStrategy checker) {
		this.checker = checker;
	}

	public boolean check(String s) {
		boolean result = checker.check(s);
		if (result)
			count++;
		return result;
	}

	public int count() {
		return count;
	}

	public void reset() {
		count = 0;
	}

	private int count = 0;
	private CheckStrategy checker;
}