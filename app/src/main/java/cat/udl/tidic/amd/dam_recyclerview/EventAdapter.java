package cat.udl.tidic.amd.dam_recyclerview;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
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

public class EventAdapter  extends ListAdapter<Event, EventAdapter.EventHolder> {


    private OnItemClickListener eventItemListener;


    protected EventAdapter(@NonNull DiffUtil.ItemCallback<Event> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_events_list, parent, false);
        return new EventHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {

        Event currentEvent = getItem(position);
        holder.textViewTitle.setText(currentEvent.getTittle());
        holder.textViewDescription.setText(currentEvent.getDescription());
        holder.ratingAvaluation.setRating(currentEvent.getAvaluation());
    }



    public Event getEventAt(int position) {
        Log.d("EventAdapter", "Position: "+ position);
        Log.d("EventAdapter", "Event: "+ getItem(position).getTittle());
        return getItem(position);
    }



    class EventHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private ImageButton imageButtonRemove;
        private RatingBar ratingAvaluation;

        public EventHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tittle);
            textViewDescription = itemView.findViewById(R.id.description);
            ratingAvaluation = itemView.findViewById(R.id.avaluation);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (eventItemListener != null && position != RecyclerView.NO_POSITION) {
                        eventItemListener.onItemClick(getItem(position));
                    }
                }
            });
        }

    }

    public interface OnItemClickListener {
        void onItemClick(Event event);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.eventItemListener = listener;
    }

}