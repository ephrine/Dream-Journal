package devesh.ephrine.apps.dreamjournal.pro.Adapters;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import devesh.ephrine.apps.dreamjournal.pro.Data.Dream;
import devesh.ephrine.apps.dreamjournal.pro.Database.AppDatabase;
import devesh.ephrine.apps.dreamjournal.pro.R;

public class DreamAdapter extends RecyclerView.Adapter<DreamAdapter.DreamViewHolder> {

    private List<Dream> dreamList;
    private Context context;
    private AppDatabase appDatabase;

    public DreamAdapter(List<Dream> dreamList, Context context) {
        this.dreamList = dreamList;
        this.context = context;
    }

    @NonNull
    @Override
    public DreamAdapter.DreamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dream_card, parent, false);

        //Database
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "dream_database")
                .allowMainThreadQueries()
                .build();

        return new DreamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DreamAdapter.DreamViewHolder holder, final int position) {
        holder.titleTextView.setText(dreamList.get(position).getTitle());
        holder.dateTextView.setText(dreamList.get(position).getDate());
        holder.dreamTextView.setText(dreamList.get(position).getDream());

        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dream dream = dreamList.get(position);
                appDatabase.dreamDao().deleteDream(dream);
                dreamList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dreamList.size();
    }

    public static class DreamViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView dateTextView;
        TextView dreamTextView;
        CardView dreamCardView;
        ImageView deleteImageView;

        public DreamViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            dreamTextView = itemView.findViewById(R.id.dreamTextView);
            dreamCardView = itemView.findViewById(R.id.dream_card_view);
            deleteImageView = itemView.findViewById(R.id.deleteImageView);
        }
    }
}
