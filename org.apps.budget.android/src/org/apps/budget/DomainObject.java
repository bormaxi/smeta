package org.apps.budget;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

public abstract class DomainObject {

	@DatabaseField(generatedId = true, dataType = DataType.LONG)
	private long id;

	public long getId() {
		return id;
	}

}
