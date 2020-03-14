package cat.udl.tidic.amd.dam_recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import cat.udl.tidic.amd.dam_recyclerview.databinding.ItemEventsListBinding;
import cat.udl.tidic.amd.dam_recyclerview.models.Event;


public final class EventListAdapter extends ListAdapter {


    protected EventListAdapter(@NonNull DiffUtil.ItemCallback diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_events_list, parent, false);

        ItemEventsListBinding bind = (ItemEventsListBinding) viewDataBinding;
        return new EventListAdapter.DataHolder(bind);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Event e = (Event) this.getItem(position);
        if (e != null) {
            ((DataHolder) holder).bind(e);
        }
    }

    public final class DataHolder extends RecyclerView.ViewHolder {
        private ItemEventsListBinding itemEventsBinding;

        public final void bind(Event eventItem) {
            this.itemEventsBinding.setEvent(eventItem);
            this.itemEventsBinding.executePendingBindings();
        }

        public DataHolder(ItemEventsListBinding itemEventsBinding) {
            super(itemEventsBinding.getRoot());
            this.itemEventsBinding = itemEventsBinding;
        }
    }

}





