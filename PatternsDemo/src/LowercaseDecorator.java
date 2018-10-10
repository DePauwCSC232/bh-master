
public class LowercaseDecorator implements CheckStrategy {
	public LowercaseDecorator(CheckStrategy checker) {
		this.checker = checker;
	}

	public boolean check(String s) {
		return checker.check(s.toLowerCase());
	}

	private CheckStrategy checker;
}
