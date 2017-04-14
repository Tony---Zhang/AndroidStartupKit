package com.thoughtworks.startup.ui.main;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thoughtworks.startup.R;
import com.thoughtworks.startup.data.model.Ribot;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RibotsAdapter extends RecyclerView.Adapter<RibotsAdapter.RibotViewHolder> {

    private List<Ribot> ribots;

    @Inject
    public RibotsAdapter() {
        ribots = new ArrayList<>();
    }

    public void setRibots(List<Ribot> ribots) {
        this.ribots = ribots;
    }

    @Override
    public RibotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ribot, parent, false);
        return new RibotViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RibotViewHolder holder, int position) {
        Ribot ribot = ribots.get(position);
        holder.hexColorView.setBackgroundColor(Color.parseColor(ribot.profile.hexColor));
        holder.nameTextView.setText(String.format("%s %s",
                ribot.profile.name.first, ribot.profile.name.last));
        holder.emailTextView.setText(ribot.profile.email);
    }

    @Override
    public int getItemCount() {
        return ribots.size();
    }

    class RibotViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.view_hex_color) View hexColorView;
        @BindView(R.id.text_name) TextView nameTextView;
        @BindView(R.id.text_email) TextView emailTextView;

        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
