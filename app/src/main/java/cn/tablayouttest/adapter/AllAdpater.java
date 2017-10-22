package cn.tablayouttest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import cn.tablayouttest.widget.AllFileAndItemView;
import cn.tablayouttest.model.FileAnd;


/**
 * Created by yu on 2017/10/9.
 */

public class AllAdpater extends RecyclerView.Adapter<AllAdpater.AllFileAndItemViewViewHolder>{

    private Context mContext;
    private List<FileAnd> items;


    public AllAdpater(Context context, List<FileAnd> items) {
        mContext = context;
        this.items = items;
    }


    @Override
    public AllFileAndItemViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AllFileAndItemViewViewHolder(new AllFileAndItemView(mContext));
    }

    @Override
    public void onBindViewHolder(AllFileAndItemViewViewHolder holder, int position) {
            holder.mAllFileAndItemView.bindView(items.get(position));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    public class AllFileAndItemViewViewHolder extends RecyclerView.ViewHolder {
        private AllFileAndItemView mAllFileAndItemView;

        public AllFileAndItemViewViewHolder(AllFileAndItemView itemView) {
            super(itemView);
            mAllFileAndItemView = itemView;
        }
    }

}
