package cat.udl.tidic.amd.dam_recyclerview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cat.udl.tidic.amd.dam_recyclerview.models.Event;
import cat.udl.tidic.amd.dam_recyclerview.repositories.EventRepoI;

public class EventsAdapter extends ListAdapter {


    protected EventsAdapter(@NonNull DiffUtil.ItemCallback diffCallback) {
        super(diffCallback);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tittle;
        private TextView description;
        private RatingBar avaluation;

        private ViewHolder(View itemView) {
            super(itemView);

            tittle = itemView.findViewById(R.id.tittle);
            description = itemView.findViewById(R.id.description);
            avaluation = itemView.findViewById(R.id.avaluation);
        }

        private void bindItem(Event e){

            tittle.setText(e.getTittle());
            description.setText(e.getDescription());
            avaluation.setRating(e.getAvaluation());

        }
    }

    @NonNull
    @Override
    // Use the xml to inflate the view
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_events_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Event e = (Event) this.getItem(position);
        if (e != null) {
            ((EventListAdapter.DataHolder) holder).bind(e);
        }
    }

}