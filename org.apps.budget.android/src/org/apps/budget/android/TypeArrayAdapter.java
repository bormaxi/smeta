package org.apps.budget.android;

import java.util.List;

import org.apps.budget.Type;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class TypeArrayAdapter extends ArrayAdapter<Type> {

	private final List<Type> objects;
	private final Activity context;

	public TypeArrayAdapter(Activity context, List<Type> objects) {
		super(context, R.layout.typelayout, objects);
		this.context = context;
		this.objects = objects;
	}

	static class ViewHolder {
		protected TextView text;
		protected CheckBox checkbox;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.typelayout, null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.text = (TextView) view.findViewById(R.id.label);
			viewHolder.checkbox = (CheckBox) view.findViewById(R.id.checkbox);
			viewHolder.checkbox
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
//							buttonView.setChecked(!isChecked);
							// Type element = (Type) viewHolder.checkbox
							// .getTag();
							// element.setSelected(buttonView.isChecked());

						}
					});
			view.setTag(viewHolder);
			viewHolder.checkbox.setTag(objects.get(position));
		} else {
			view = convertView;
			((ViewHolder) view.getTag()).checkbox.setTag(objects.get(position));
		}
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.text.setText(objects.get(position).getName());
		// holder.checkbox.setChecked(list.get(position).isSelected());
		return view;
	}
}