package com.example.chucknorrisapp.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chucknorrisapp.R;
import com.example.chucknorrisapp.adapter.MyCategoryRecyclerViewAdapter;
import com.example.chucknorrisapp.controller.Controller;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class CategoryFragment extends Fragment implements MyCategoryRecyclerViewAdapter.onRowListener {
    private static final String TAG = "CategoryFragment";
    private AVLoadingIndicatorView avi;
    private RecyclerView recyclerView;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CategoryFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CategoryFragment newInstance(int columnCount) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        avi = (AVLoadingIndicatorView)getActivity().findViewById(R.id.indicator);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

//        categories.add("animal");
//        categories.add("career");
//        categories.add("religion");
//        categories.add("travel");
//        categories.add("fashion");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            fetchCategories();
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

        }
        return view;
    }

    void fetchCategories(){
        avi.smoothToShow();
        Controller.getInstance().getCategories().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                recyclerView.setAdapter(new MyCategoryRecyclerViewAdapter(response.body(), CategoryFragment.this::onClick));
                avi.smoothToHide();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });

    }

    @Override
    public void onClick(int position, String category) {

        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        NavHostFragment.findNavController(CategoryFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
    }
}