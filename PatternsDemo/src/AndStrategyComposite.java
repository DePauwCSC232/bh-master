import java.util.ArrayList;
import java.util.List;

public class AndStrategyComposite implements CheckStrategy {
	public void addStrategy(CheckStrategy s) {
		tests.add(s);
	}

	public boolean check(String s) {
		for (CheckStrategy strategy : tests) {
			if (!strategy.check(s))
				return false;
		}
		return true;
	}

	private List<CheckStrategy> tests = new ArrayList<>();
}