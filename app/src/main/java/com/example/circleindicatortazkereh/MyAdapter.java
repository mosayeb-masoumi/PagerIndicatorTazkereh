package com.example.circleindicatortazkereh;

import android.app.VoiceInteractor;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    List<String> list;
    Context context;

    public MyAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

        String model = list.get(position);
        holder.row_txt.setText(model);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView row_txt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            row_txt = itemView.findViewById(R.id.row_txt);
        }
    }
}
