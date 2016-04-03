package org.apps.budget.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.sql.SQLException;

import org.apps.budget.Category;
import org.apps.budget.Type;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseOpenHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATA_FILE = "app_smeta_pro.tsv";

	private static final String DATABASE_NAME = "smeta.db";

	private static final int DATABASE_VERSION = 1;

	private Dao<Category, Integer> categoryDao;

	private Dao<Type, Integer> typeDao;

	private final Context context;

	public DatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase database,
			ConnectionSource connectionSource) {
		try {
			loadData();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void loadData() throws SQLException {
		String text = null;
		String[] parts = null;
		BufferedReader reader = null;
		Category category = null;
		String categoryName = null;
		String prevCategory = null;
		int order = 0;

		TableUtils.createTable(connectionSource, Category.class);
		TableUtils.createTable(connectionSource, Type.class);
		try {
			InputStream in = context.getAssets().open(DATA_FILE);
			reader = new BufferedReader(new InputStreamReader(in,
					Charset.defaultCharset()));
			while (((text = reader.readLine()) != null)) {
				parts = text.split("\t");
				categoryName = parts[0];
				if (categoryName != null) {
					categoryName = categoryName.trim();
					if (!categoryName.equals(prevCategory)) {
						category = new Category();
						category.setName(categoryName);
						order = order + 100;
						category.setOrder(order);
						getCategoryDao().create(category);
						prevCategory = categoryName;
					}
				}

				Type type = new Type();
				type.setCategory(category);
				type.setName(parts[1]);
				type.setPrice(new BigDecimal(parts[2]));
				type.setUnit(parts[3]);
				getTypeDao().create(type);
			}
		} catch (IOException e) {
			Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				Toast.makeText(context, e.getLocalizedMessage(),
						Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}
		}
	}

	public void saveData() {
		BufferedWriter writer = null;
		try {
			OutputStream out = context.getAssets().openFd(DATA_FILE)
					.createOutputStream();
			writer = new BufferedWriter(new OutputStreamWriter(out,
					Charset.defaultCharset()));
			for (Type type : getTypeDao().query(
					getTypeDao()
							.queryBuilder()
							.leftJoin(
									(QueryBuilder<?, ?>) getCategoryDao()
											.queryBuilder()
											.orderBy("order", true).prepare())
							.prepare())) {
				String line = type.getCategory().getName() + "/t"
						+ type.getName() + "/t" + type.getPrice() + "/t"
						+ type.getUnit();
				writer.write(line);
				writer.newLine();
			}

		} catch (IOException e) {
			Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		} catch (SQLException e) {
			Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Dao<Category, Integer> getCategoryDao() throws SQLException {
		if (categoryDao == null)
			categoryDao = DaoManager
					.createDao(connectionSource, Category.class);

		return categoryDao;
	}

	public Dao<Type, Integer> getTypeDao() throws SQLException {
		if (typeDao == null)
			typeDao = DaoManager.createDao(connectionSource, Type.class);

		return typeDao;

	}

	@Override
	public void onUpgrade(SQLiteDatabase database,
			ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			saveData();
			TableUtils.dropTable(connectionSource, Type.class, true);
			TableUtils.dropTable(connectionSource, Category.class, true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		onCreate(database, connectionSource);
	}

}
