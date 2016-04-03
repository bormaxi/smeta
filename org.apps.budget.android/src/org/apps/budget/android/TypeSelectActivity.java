package org.apps.budget.android;

import java.sql.SQLException;
import java.util.List;

import org.apps.budget.Category;
import org.apps.budget.Type;
import org.apps.budget.data.DatabaseOpenHelper;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;

import com.j256.ormlite.android.apptools.OrmLiteBaseListActivity;
import com.j256.ormlite.dao.Dao;

public class TypeSelectActivity extends
		OrmLiteBaseListActivity<DatabaseOpenHelper> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getListView().setFastScrollEnabled(true);
		long categoryId = getIntent().getExtras().getLong("smeta.categoryId");
		try {
			Category cat = getHelper().getCategoryDao().queryForId(
					(int) categoryId);
			setTitle(cat.getName());
		} catch (SQLException e1) {
		}
		List<Type> types = null;
		try {
			Dao<Type, Integer> dao = getHelper().getTypeDao();
			types = dao.query(dao.queryBuilder().where()
					.eq("category_id", categoryId).prepare());
		} catch (SQLException e) {
			e.printStackTrace();
			if (types == null)
				return;
		}

		ListAdapter adapter = new TypeArrayAdapter(this, types);
		setListAdapter(adapter);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// switch (item.getItemId()) {
		// case aOndroid.R.id.home:
		// Intent intent = new Intent(this, MainActivity.class);
		// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		// startActivity(intent);
		// break;
		// }
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_type_select, menu);
		return true;
	}

}
