package cat.udl.tidic.amd.dam_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import cat.udl.tidic.amd.dam_recyclerview.databinding.ItemEventsListBinding;
import cat.udl.tidic.amd.dam_recyclerview.models.Event;


public final class EventListAdapter extends ListAdapter  {

    private AdapterView.OnItemClickListener listener;

    protected EventListAdapter(@NonNull DiffUtil.ItemCallback diffCallback,
                               AdapterView.OnItemClickListener listener) {
        super(diffCallback);
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_events_list, parent, false);

        ItemEventsListBinding bind = (ItemEventsListBinding) viewDataBinding;
        return new EventListAdapter.EventHolder(bind);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Event e = (Event) this.getItem(position);
        if (e != null) {
            ((EventHolder) holder).bind(e);
        }

    }


    public final class EventHolder extends RecyclerView.ViewHolder {
        private ItemEventsListBinding itemEventsBinding;

        public final void bind(Event eventItem) {
            //this.itemEventsBinding.setEvent(eventItem);
            this.itemEventsBinding.executePendingBindings();
        }

        public EventHolder(ItemEventsListBinding itemEventsBinding) {
            super(itemEventsBinding.getRoot());
            this.itemEventsBinding = itemEventsBinding;
        }
    }

}





