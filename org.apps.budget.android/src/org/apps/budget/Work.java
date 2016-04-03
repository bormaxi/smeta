/**
 */
package org.apps.budget;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Work {

	protected BigInteger qty = null;

	protected Type type;

	protected Work() {
		super();
	}

	public BigInteger getQty() {
		return qty;
	}

	public void setQty(BigInteger newQty) {
		qty = newQty;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type newType) {
		type = newType;
	}

	public BigDecimal getAmount() {
		BigDecimal qty = new BigDecimal(getQty());
		BigDecimal amount = getType().getPrice();
		amount.multiply(qty);
		return amount;
	}

	public String toString() {
		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (qty: ");
		result.append(qty);
		result.append(')');
		return result.toString();
	}

} // WorkImpl
