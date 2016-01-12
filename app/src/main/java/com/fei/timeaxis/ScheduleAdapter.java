package com.fei.timeaxis;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class ScheduleAdapter extends BaseExpandableListAdapter {

	private Context context;
	private List<Schedule> list;

	public ScheduleAdapter(Context context, List<Schedule> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getGroupCount() {
		return list.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return list.get(groupPosition).getList() == null ? 0 : list.get(groupPosition).getList().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return list.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return list.get(groupPosition).getList().get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		GroupViewHolder groupViewHolder = null;
		if (groupViewHolder == null) {
			groupViewHolder = new GroupViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_group, parent, false);
			groupViewHolder.tv_icon = (TextView) convertView.findViewById(R.id.tv_icon);
			groupViewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);

			convertView.setTag(groupViewHolder);
		} else {
			groupViewHolder = (GroupViewHolder) convertView.getTag();
		}

		Schedule schedule = list.get(groupPosition);

		groupViewHolder.tv_icon.setText(String.valueOf(groupPosition));
		groupViewHolder.tv_date.setText(schedule.getDate());

		return convertView;
	}

	private String lastTime = "";

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		ChildViewHolder childViewHolder = null;
		if (childViewHolder == null) {
			childViewHolder = new ChildViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_child, parent, false);
			childViewHolder.cb_level = (CheckBox) convertView.findViewById(R.id.cb_level);
			childViewHolder.tv_subject = (TextView) convertView.findViewById(R.id.tv_subject);
			childViewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);

			convertView.setTag(childViewHolder);
		} else {
			childViewHolder = (ChildViewHolder) convertView.getTag();
		}

		ScheduleDetail scheduleDetail = (ScheduleDetail) getChild(groupPosition, childPosition);

		String time = scheduleDetail.getTime();
		if (!lastTime.equals(scheduleDetail.getTime())) {
			lastTime = time;
			childViewHolder.tv_time.setText(lastTime);
			childViewHolder.tv_time.setVisibility(View.VISIBLE);
		}

		if (scheduleDetail.isAvailable()) {
			switch (scheduleDetail.getLevel()) {
			case 1:
				childViewHolder.cb_level.setBackgroundResource(R.mipmap.icon5);
				childViewHolder.tv_subject.setBackgroundResource(R.drawable.i2);
				break;
			case 2:
				childViewHolder.cb_level.setBackgroundResource(R.mipmap.icon6);
				childViewHolder.tv_subject.setBackgroundResource(R.drawable.i8);
				break;
			case 3:
				childViewHolder.cb_level.setBackgroundResource(R.mipmap.icon7);
				childViewHolder.tv_subject.setBackgroundResource(R.drawable.i6);
				break;
			case 4:
				childViewHolder.cb_level.setBackgroundResource(R.mipmap.icon9);
				childViewHolder.tv_subject.setBackgroundResource(R.drawable.i0);
				break;
			}
		} else {
			childViewHolder.cb_level.setBackgroundResource(R.mipmap.icon8);
			childViewHolder.tv_subject.setBackgroundResource(R.drawable.i4);
		}

		childViewHolder.cb_level.setOnCheckedChangeListener(new CheckedChangeListener(scheduleDetail, childViewHolder));
		childViewHolder.tv_subject.setText(scheduleDetail.getSubject());

		return convertView;
	}

	private class CheckedChangeListener implements OnCheckedChangeListener {

		private ScheduleDetail scheduleDetail;
		private ChildViewHolder childViewHolder;

		public CheckedChangeListener(ScheduleDetail scheduleDetail, ChildViewHolder childViewHolder) {
			super();
			this.scheduleDetail = scheduleDetail;
			this.childViewHolder = childViewHolder;
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			if (isChecked) {
				scheduleDetail.setAvailable(true);
				switch (scheduleDetail.getLevel()) {
				case 1:
					buttonView.setBackgroundResource(R.mipmap.icon5);
					childViewHolder.tv_subject.setBackgroundResource(R.drawable.i2);
					break;
				case 2:
					buttonView.setBackgroundResource(R.mipmap.icon6);
					childViewHolder.tv_subject.setBackgroundResource(R.drawable.i8);
					break;
				case 3:
					buttonView.setBackgroundResource(R.mipmap.icon7);
					childViewHolder.tv_subject.setBackgroundResource(R.drawable.i6);
					break;
				case 4:
					buttonView.setBackgroundResource(R.mipmap.icon9);
					childViewHolder.tv_subject.setBackgroundResource(R.drawable.i0);
					break;
				}
			} else {
				scheduleDetail.setAvailable(false);
				buttonView.setBackgroundResource(R.mipmap.icon8);
				childViewHolder.tv_subject.setBackgroundResource(R.drawable.i4);
			}
		}
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

	private class GroupViewHolder {
		public TextView tv_icon;
		public TextView tv_date;
	}

	private class ChildViewHolder {
		public TextView tv_time;
		public CheckBox cb_level;
		public TextView tv_subject;
	}

}
