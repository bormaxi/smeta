package org.apps.budget.android;

import java.sql.SQLException;
import java.util.List;

import org.apps.budget.Category;
import org.apps.budget.data.DatabaseOpenHelper;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ListAdapter;

import com.j256.ormlite.android.apptools.OrmLiteBaseListActivity;
import com.j256.ormlite.dao.Dao;

public class MainActivity extends OrmLiteBaseListActivity<DatabaseOpenHelper> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getListView().setFastScrollEnabled(true);
		List<Category> categories = null;
		try {
			Dao<Category, Integer> dao = getHelper().getDao(Category.class);
			categories = dao.query(dao.queryBuilder().distinct().orderBy("order", true)
					.prepare());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ListAdapter adapter = new CategoryArrayAdapter(this, categories);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
