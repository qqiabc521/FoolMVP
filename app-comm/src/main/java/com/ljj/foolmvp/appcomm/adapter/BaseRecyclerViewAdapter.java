package com.ljj.foolmvp.appcomm.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Lijj on 17/1/20.
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    protected List<T> data = new ArrayList<>();
    protected OnItemClickListener<T> listener;
    protected OnItemLongClickListener<T> onItemLongClickListener;
    protected int clicked = RecyclerView.NO_POSITION;
    protected boolean isSupportClicked;

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, final int position) {
        final T itemData = data.get(position);
        holder.onBindViewHolder(itemData, position);
        if(listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    processClick(position);
                    if(listener != null) {
                        listener.onClick(itemData, position);
                    }
                }
            });
        }

        if(null != onItemLongClickListener){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    processClick(position);
                    if(null != onItemLongClickListener){
                        onItemLongClickListener.onLongClick(data.get(position), position);
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    private void processClick(int currentPosition) {
        if (!isSupportClicked || currentPosition >= data.size()) {
            return;
        }

        int old = clicked;
        clicked = RecyclerView.NO_POSITION;
        if (-1 != old) {
            notifyItemChanged(old);
        }
        clicked = currentPosition;
        notifyItemChanged(currentPosition);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addAll(List<T> data) {
        if(data == null){
            return;
        }
        this.data.addAll(data);
    }

    public void addAllAt(int index, List<T> data) {
        if (null == data) {
            return;
        }

        this.data.addAll(index, data);
    }

    public void prepend(List<T> data){
        this.data.addAll(0, data);
    }

    public void add(T object) {
        data.add(object);
    }

    public void add(int index,T object) {
        data.add(index,object);
    }

    public void clear() {
        data.clear();
    }

    public void reset(List<T> source) {
        if (null == source) {
            data = new ArrayList<>();
        } else {
            data = source;
        }
    }

    public void remove(T object) {
        data.remove(object);
    }
    public void remove(int position) {
        data.remove(position);
    }
    public void removeAll(List<T> data) {
        this.data.retainAll(data);
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.listener = listener;
    }

    /**
     * Get current selected index
     *
     * @return
     */
    public int getSelected() {
        return clicked;
    }

    public int getDataIndex(T d) {
        Iterator<T> iterator = data.iterator();
        int i = 0;
        T temp;
        while (iterator.hasNext()) {
            temp = iterator.next();
            if (temp.equals(d)) {
                return i;
            }
            i++;
        }

        return -1;
    }


    public List<T> getData() {
        return data;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
