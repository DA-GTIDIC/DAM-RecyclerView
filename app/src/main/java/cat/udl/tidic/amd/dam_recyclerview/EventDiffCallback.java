package cat.udl.tidic.amd.dam_recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import cat.udl.tidic.amd.dam_recyclerview.models.Event;

public class EventDiffCallback extends DiffUtil.ItemCallback<Event> {


    @Override
    public boolean areItemsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
        return oldItem.getUserId() == newItem.getUserId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
        return oldItem.equals(newItem);
    }
}
