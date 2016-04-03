/**
 */
package org.apps.budget;

import com.j256.ormlite.field.DatabaseField;

public abstract class NamedItem extends DomainObject {

	@DatabaseField(columnName = "name")
	protected String name = null;

	protected NamedItem() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}

	public String toString() {
		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} // NamedItemImpl
