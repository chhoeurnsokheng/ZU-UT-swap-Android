package com.zillennium.utswap.screens.systems.notificationScreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.zillennium.utswap.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private ArrayList<Notification> notificationsData;

    public NotificationAdapter(ArrayList<Notification> arrayList) {
        this.notificationsData = arrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public ImageView ivImage;
        /* access modifiers changed from: private */
        public LinearLayout linearLayout;
        /* access modifiers changed from: private */
        public TextView txtDescription;
        /* access modifiers changed from: private */
        public TextView txtTime;
        /* access modifiers changed from: private */
        public TextView txtTitle;

        public ViewHolder(View view) {
            super(view);
            this.ivImage = (ImageView) view.findViewById(R.id.iv_image);
            this.txtTitle = (TextView) view.findViewById(R.id.txt_title);
            this.txtDescription = (TextView) view.findViewById(R.id.txt_description);
            this.txtTime = (TextView) view.findViewById(R.id.txt_time);
            this.linearLayout = (LinearLayout) view.findViewById(R.id.lay_item);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_system_notification, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Notification notification = this.notificationsData.get(i);
        viewHolder.ivImage.setImageResource(notification.getImage());
        viewHolder.txtTitle.setText(notification.getTitle());
        viewHolder.txtDescription.setText(notification.getDescription());
        viewHolder.txtTime.setText(notification.getTime());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
    }

    public int getItemCount() {
        return this.notificationsData.size();
    }
}
