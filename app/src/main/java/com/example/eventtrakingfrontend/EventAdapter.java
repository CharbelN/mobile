package com.example.eventtrakingfrontend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private Context context;
    private List<Event> eventList;

    // Constructor
    public EventAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    // ViewHolder class
    public static class EventViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewEvent;
        private TextView textViewTitle;
        private TextView textViewDateTime;
        private ImageView imageViewFavorite;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewEvent = itemView.findViewById(R.id.imageViewEvent);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDateTime = itemView.findViewById(R.id.textViewDateTime);
            imageViewFavorite = itemView.findViewById(R.id.imageViewFavorite);
        }

        public void bind(Event event) {
            // Bind event data to views
            // For example:
            // imageViewEvent.setImageResource(event.getImage());
            textViewTitle.setText(event.getEventTitle());
            textViewDateTime.setText(event.getEventDateTime());
            // Handle favorite button click here

            // Adjust size of favorite button
            imageViewFavorite.getLayoutParams().width = itemView.getResources().getDimensionPixelSize(R.dimen.favorite_button_size);
            imageViewFavorite.getLayoutParams().height = itemView.getResources().getDimensionPixelSize(R.dimen.favorite_button_size);
        }
    }
}
