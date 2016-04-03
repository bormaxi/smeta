/**
 */
package org.apps.budget;

import java.util.ArrayList;
import java.util.List;

public class Archieve {

	protected List<Budget> archieved;

	protected Archieve() {
		super();
	}

	public List<Budget> getArchieved() {
		if (archieved == null) {
			archieved = new ArrayList<Budget>();
		}
		return archieved;
	}

} // ArchieveImpl
