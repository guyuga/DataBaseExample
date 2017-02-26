package com.guyuga.databaseproject.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.annotations.NonNull;
import com.guyuga.databaseproject.Adapter.CourseRecyclerAdapter;
import com.guyuga.databaseproject.DataBase.DataRepository;
import com.guyuga.databaseproject.Injection;
import com.guyuga.databaseproject.Models.Course;
import com.guyuga.databaseproject.Presentor.CourseContract;
import com.guyuga.databaseproject.R;

import java.util.List;

/**
 * Created by guyug on 25/2/2017.
 */

public class CourseFragment extends Fragment implements CourseContract.View {

    private CourseContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private CourseRecyclerAdapter mCourseAdapter;

    public CourseFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static CourseFragment newInstance() {
        CourseFragment fragment = new CourseFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recycler_fragment, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycle_view);
        setupRecyclerView();
        return root;
    }

    private void setupRecyclerView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(false);
        mCourseAdapter = new CourseRecyclerAdapter(Injection.provideDataRepository(getContext()));
        mRecyclerView.setAdapter(mCourseAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.stop();
    }

    @Override
    public void updateRecycleView(List<Course> courses) {
        mCourseAdapter.setCourses(courses);
        mCourseAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(@NonNull CourseContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
