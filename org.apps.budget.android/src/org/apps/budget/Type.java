/**
 */
package org.apps.budget;

import java.math.BigDecimal;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "types")
public class Type extends NamedItem {

	@DatabaseField(foreign = true)
	protected Category category;

	@DatabaseField
	protected BigDecimal price = null;

	@DatabaseField
	protected String unit = null;

	public Type() {
		super();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category newCategory) {
		category = newCategory;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal newPrice) {
		newPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
		price = newPrice;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String newUnit) {
		unit = newUnit;
	}

	public String toString() {
		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (price: ");
		result.append(price);
		result.append(", unit: ");
		result.append(unit);
		result.append(')');
		return result.toString();
	}

} // TypeImpl
