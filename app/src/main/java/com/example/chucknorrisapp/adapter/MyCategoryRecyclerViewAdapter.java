package com.example.chucknorrisapp.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chucknorrisapp.R;

import java.util.List;


public class MyCategoryRecyclerViewAdapter extends RecyclerView.Adapter<MyCategoryRecyclerViewAdapter.ViewHolder> {

    private final List<String> mCategories;
    private onRowListener onRowListener;
    public MyCategoryRecyclerViewAdapter(List<String> items, onRowListener listener) {
        mCategories = items;
        onRowListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_category, parent, false);
        return new ViewHolder(view, onRowListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mCategoryName.setText(mCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final TextView mCategoryName;
        private onRowListener listener;
        public ViewHolder(View view, onRowListener listener) {
            super(view);
            mView = view;
           mCategoryName = (TextView) view.findViewById(R.id.categoryName);
           this.listener = listener;
           view.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mCategoryName.getText() + "'";
        }

        @Override
        public void onClick(View view) {
            listener.onClick(getAdapterPosition(), mCategoryName.getText().toString());
        }
    }

   public interface onRowListener{
        void onClick(int position, String category);
    }
}

