package com.example.qrcode.app.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.preference.PreferenceActivity;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.qrcode.R;
import com.example.qrcode.databinding.ListHistoryHeaderBinding;
import com.example.qrcode.databinding.ListHistoryItemBinding;
import com.example.qrcode.model.BaseHistory;
import com.example.qrcode.model.HeaderHistory;
import com.example.qrcode.model.Transaction;
import com.example.qrcode.util.CalendarUtil;
import com.example.qrcode.util.MoneyUtil;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<BaseHistory> listHistory;

    public HomeAdapter(Context context, List<BaseHistory> listHistory){
        this.context = context;
        this.listHistory = listHistory;
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        ListHistoryHeaderBinding binding;
        public HeaderViewHolder(ListHistoryHeaderBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        ListHistoryItemBinding binding;
        public ItemViewHolder(ListHistoryItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i == BaseHistory.HEADER){
            ListHistoryHeaderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_history_header, viewGroup, false);
            return new HeaderViewHolder(binding);
        }
        else if (i == BaseHistory.ITEM){
            ListHistoryItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_history_item, viewGroup, false);
            return new ItemViewHolder(binding);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof HeaderViewHolder){
            HeaderHistory header = (HeaderHistory) listHistory.get(i);
            header.setDate(CalendarUtil.convertDateToView(header.getDate()));
            ((HeaderViewHolder) viewHolder).binding.setViewModel(header);
        }
        else if (viewHolder instanceof ItemViewHolder){
            Transaction transaction = (Transaction) listHistory.get(i);
            String message = "";
            switch (transaction.getType()){
                case Transaction.TRANSFER_IN:
                    message = String.format(context.getResources().getString(R.string.text_history_transfer_in), MoneyUtil.convertMoney(transaction.getAmount()), transaction.getPhone());
                    break;
                case Transaction.TRANSFER_OUT:
                    message = String.format(context.getResources().getString(R.string.text_history_transfer_out), MoneyUtil.convertMoney(transaction.getAmount()), transaction.getPhone());
                    break;
                case Transaction.TOP_UP:
                    message = String.format(context.getResources().getString(R.string.text_history_top_up), MoneyUtil.convertMoney(transaction.getAmount()));
                    break;
            }
            ((ItemViewHolder) viewHolder).binding.setText(message);
        }
    }

    @Override
    public int getItemCount() {
        return listHistory.size();
    }

    @Override
    public int getItemViewType(int position) {
        return listHistory.get(position).getItemType();
    }
}
