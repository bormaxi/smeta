/**
 */
package org.apps.budget;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Budget {

	protected Client contact;

	protected List<Work> items;

	protected Date modified = null;

	protected Date sent = null;

	protected Budget() {
		super();
	}

	public Budget(Date modified) {
		this();
		this.modified = modified;
	}

	public Client getContact() {
		return contact;
	}

	public void setContact(Client newContact) {
		contact = newContact;
	}

	public List<Work> getItems() {
		if (items == null) {
			items = new ArrayList<Work>();
		}
		return items;
	}

	public BigDecimal getTotal() {
		BigDecimal total = new BigDecimal(0);
		for (Work work : getItems())
			total = total.add(work.getAmount());

		return total;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date newModified) {
		modified = newModified;
	}

	public Date getSent() {
		return sent;
	}

	public void setSent(Date newSent) {
		sent = newSent;
	}

	public String toString() {
		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (modified: ");
		result.append(modified);
		result.append(", sent: ");
		result.append(sent);
		result.append(')');
		return result.toString();
	}

} // BudgetImpl
