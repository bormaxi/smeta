package org.apps.budget;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BudgetRegistry {

	private Map<Date, Budget> map;

	protected BudgetRegistry() {
		map = new HashMap<Date, Budget>();
	}

	public Budget get(Date modified) {
		if (!map.containsKey(modified)) {
			Budget budget = new Budget(modified);
			map.put(modified, budget);
		}
		return map.get(modified);
	}

	public Set<Date> list() {
		return Collections.unmodifiableSet(map.keySet());
	}
}