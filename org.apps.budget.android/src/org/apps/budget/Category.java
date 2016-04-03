/**
 */
package org.apps.budget;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "categories")
public class Category extends NamedItem {

	@DatabaseField
	protected int order = 0;

	public Category() {
		super();
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int newOrder) {
		order = newOrder;
	}

	public String toString() {
		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (order: ");
		result.append(order);
		result.append(')');
		return result.toString();
	}

} // CategoryImpl
