package com.fei.timeaxis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView elv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elv_content = (ExpandableListView) findViewById(R.id.elv_content);
        ScheduleAdapter adapter = new ScheduleAdapter(this, getData());
        elv_content.setAdapter(adapter);
        elv_content.setDivider(null);// 去掉默认分隔线
        elv_content.setGroupIndicator(null); // 去掉默认指示器
        // 遍历所有group,将所有项设置成默认展开
        int groupCount = elv_content.getCount();
        for (int i = 0; i < groupCount; i++) {
            elv_content.expandGroup(i);
        }
        // 不拦截点击事件，使其不能伸缩
        elv_content.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    private List<Schedule> getData() {
        String date1 = "2015-12-9";
        String date2 = "2015-12-10";
        String date3 = "2015-12-15";

        ScheduleDetail detail10 = new ScheduleDetail("1", true, 1, "111111111111111111111111111111111111111111111111111", "9:00");
        ScheduleDetail detail11 = new ScheduleDetail("1", true, 2, "22222222222222222222222222222", "9:00");
        ScheduleDetail detail12 = new ScheduleDetail("2", false, 3, "2222", "13:00");
        ScheduleDetail detail13 = new ScheduleDetail("3", true, 4, "3333", "13:00");
        ScheduleDetail detail14 = new ScheduleDetail("4", true, 2, "55555", "16:00");

        ScheduleDetail detail20 = new ScheduleDetail("5", true, 2, "44444", "4:00");
        ScheduleDetail detail21 = new ScheduleDetail("5", false, 3, "44444", "5:00");
        ScheduleDetail detail22 = new ScheduleDetail("5", true, 4, "44444", "12:00");
        ScheduleDetail detail23 = new ScheduleDetail("5", false, 1, "44444", "12:00");

        ScheduleDetail detail30 = new ScheduleDetail("5", true, 4, "44444", "9:00");
        ScheduleDetail detail31 = new ScheduleDetail("5", false, 3, "44444", "11:00");
        ScheduleDetail detail32 = new ScheduleDetail("5", true, 2, "44444", "11:00");
        ScheduleDetail detail33 = new ScheduleDetail("5", false, 1, "44444", "11:00");
        ScheduleDetail detail34 = new ScheduleDetail("5", true, 4, "44444", "19:00");

        List<ScheduleDetail> list1 = new ArrayList<ScheduleDetail>();
        list1.add(detail10);
        list1.add(detail11);
        list1.add(detail12);
        list1.add(detail13);
        list1.add(detail14);

        List<ScheduleDetail> list2 = new ArrayList<ScheduleDetail>();
        list2.add(detail20);
        list2.add(detail21);
        list2.add(detail22);
        list2.add(detail23);

        List<ScheduleDetail> list3 = new ArrayList<ScheduleDetail>();
        list3.add(detail30);
        list3.add(detail31);
        list3.add(detail32);
        list3.add(detail33);
        list3.add(detail34);

        List<Schedule> schedule = new ArrayList<Schedule>();
        schedule.add(new Schedule(date1, list1));
        schedule.add(new Schedule(date2, list2));
        schedule.add(new Schedule(date3, list3));

        return schedule;
    }
}
