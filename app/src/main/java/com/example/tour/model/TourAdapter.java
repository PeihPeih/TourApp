package com.example.tour.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tour.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder>{
    private Context context;
    private List<Tour> mList;
    private List<Tour> backup;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Tour> getBackup() {
        return backup;
    }

    public void setBackup(List<Tour> backup) {
        this.backup = backup;
    }

    public TourItemListener getmTourItem() {
        return mTourItem;
    }

    public void setmTourItem(TourItemListener mTourItem) {
        this.mTourItem = mTourItem;
    }

    private TourItemListener mTourItem;

    public TourAdapter(Context context) {
        this.context = context;
        this.mList = new ArrayList<>();
        this.backup = new ArrayList<>();
    }

    public void setClickListener(TourItemListener mTourItem){
        this.mTourItem = mTourItem;
    }

    public List<Tour> getmList(){
        return this.mList;
    }

    public void setmList(List<Tour> tours){
        this.mList = tours;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new TourViewHolder(view);
    }

    public Tour getItem(int position){
        return mList.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {
        Tour tour = mList.get(position);
        if (tour == null){
            return;
        }
        holder.img.setImageResource(tour.getImg());
        holder.tvDuration.setText(tour.getDuration());
        holder.tvName.setText(tour.getName());
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.remove(position);
                backup.remove(position);
                notifyDataSetChanged();;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList != null){
            return mList.size();
        }
        return 0;
    }

    public void add (Tour t){
        mList.add(t);
        backup.add(t);
        notifyDataSetChanged();
    }

    public void update(int position, Tour tour){
        mList.set(position, tour);
        backup.set(position, tour);
        notifyDataSetChanged();
    }

    public class TourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView img;
        private TextView tvName, tvDuration;
        private Button btnRemove;
        public TourViewHolder(@NotNull View view){
            super(view);
            img = view.findViewById(R.id.img);
            tvName = view.findViewById(R.id.txtName);
            tvDuration = view.findViewById(R.id.txtDuration);
            btnRemove = view.findViewById(R.id.btnRemove);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            if (mTourItem != null){
                mTourItem.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface TourItemListener{
        void onItemClick(View view, int position);
    }
}
