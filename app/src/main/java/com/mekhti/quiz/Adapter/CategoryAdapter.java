package com.mekhti.quiz.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mekhti.quiz.Listener.Listener;
import com.mekhti.quiz.R;

import java.util.List;

import static android.content.ContentValues.TAG;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    String TAG = "4n4n";
    private List<String> categories;
    private Listener listener;

    public CategoryAdapter(List<String> categories, Listener listener) {

        this.categories = categories;
        Log.d(TAG, "CategoryAdapter: "+categories.size());
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_category,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.txtAdi.setText(categories.get(position).toString());
        Log.d(TAG, "onBindViewHolder: "+categories.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+categories.size());
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout llContent;
        public TextView txtAdi;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            llContent = itemView.findViewById(R.id.llContent);
            txtAdi = itemView.findViewById(R.id.txtAdi);

            llContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v,getAdapterPosition());
                }
            });
        }
    }
}
