package org.apps.budget.android;

import java.util.List;

import org.apps.budget.Category;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CategoryArrayAdapter extends ArrayAdapter<Category> {

	private final List<Category> objects;
	private final Activity context;

	public CategoryArrayAdapter(Activity context, List<Category> objects) {
		super(context, android.R.layout.simple_list_item_1, objects);
		this.context = context;
		this.objects = objects;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.textlayout, null);
		} else {
			view = convertView;
		}
		TextView textView = (TextView) view.findViewById(R.id.label);
		textView.setText(objects.get(position).getName());
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context.getApplicationContext(),
						TypeSelectActivity.class);
				intent.putExtra("smeta.categoryId", objects.get(position).getId());
				context.startActivity(intent);
			}
		});
		return view;

	}
}
