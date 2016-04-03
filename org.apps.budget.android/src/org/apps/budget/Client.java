/**
 */
package org.apps.budget;


public class Client {

	protected String name = null;

	protected String phone = null;

	protected String email = null;

	protected Client() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String newPhone) {
		phone = newPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String newEmail) {
		email = newEmail;
	}

	public void send(Budget budget) {
	}

	public String toString() {
		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", phone: ");
		result.append(phone);
		result.append(", email: ");
		result.append(email);
		result.append(')');
		return result.toString();
	}

} // ClientImpl
