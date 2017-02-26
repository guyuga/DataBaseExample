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
import com.guyuga.databaseproject.Adapter.StudentRecyclerAdapter;
import com.guyuga.databaseproject.Models.Student;
import com.guyuga.databaseproject.Presentor.StudentsContract;
import com.guyuga.databaseproject.R;

import java.util.List;

/**
 * Created by guyug on 23/2/2017.
 */

public class StudentsFragment extends Fragment implements StudentsContract.View {

    private StudentsContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private StudentRecyclerAdapter mStudentAdapter;

    public StudentsFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static StudentsFragment newInstance() {
        StudentsFragment fragment = new StudentsFragment();
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
        mStudentAdapter = new StudentRecyclerAdapter();
        mRecyclerView.setAdapter(mStudentAdapter);

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
    public void updateRecycleView(List<Student> students) {
        mStudentAdapter.setStudents(students);
        mStudentAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(@NonNull StudentsContract.Presenter presenter) {
        mPresenter = presenter;
    }


}
