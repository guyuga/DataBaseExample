package com.guyuga.databaseproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.guyuga.databaseproject.Models.Course;
import com.guyuga.databaseproject.Models.Student;
import com.guyuga.databaseproject.R;

import java.util.List;

/**
 * Created by guyug on 26/2/2017.
 */

public class LinkAdapter extends BaseAdapter {

    private List<Course> mCourses;
    private Student mStudent;
    private CheckBox.OnCheckedChangeListener listener;

    public LinkAdapter(List<Course> mCourses, Student mStudent, CompoundButton.OnCheckedChangeListener listener) {
        this.mCourses = mCourses;
        this.mStudent = mStudent;
        this.listener = listener;
    }

    public void setmStudent(Student mStudent) {
        this.mStudent = mStudent;
    }

    @Override
    public int getCount() {
        return mCourses.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.course_link_row, viewGroup, false);
            holder.credits = (TextView) view.findViewById(R.id.link_credits);
            holder.checkBox = (CheckBox) view.findViewById(R.id.link_checkBox);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.checkBox.setText(mCourses.get(i).getName());
        holder.checkBox.setTag(mCourses.get(i).getId());
        holder.credits.setText(mCourses.get(i).getCredits());

        if (mStudent.getCourses().contains(mCourses.get(i))){
            holder.checkBox.setChecked(true);
        }else
            holder.checkBox.setChecked(false);

        holder.checkBox.setOnCheckedChangeListener(listener);
        return view;
    }

    public static class ViewHolder{
        TextView credits;
        CheckBox checkBox;
    }
}
