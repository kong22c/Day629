package com.example.day629.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day629.R;
import com.example.day629.bean.ProcBean;

import java.util.ArrayList;

public class ProcAdapter extends RecyclerView.Adapter<ProcAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ProcBean.DataBean.DatasBean> list;

    public ProcAdapter(Context context, ArrayList<ProcBean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_proc, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getEnvelopePic()).into(holder.mIvPic);
        holder.mTvTitle.setText(list.get(position).getTitle());
        holder.mTvDesc.setText(list.get(position).getDesc());
        holder.mTvAuthor.setText("作者：" + list.get(position).getAuthor());
        holder.mTvNicedater.setText("时间：" + list.get(position).getNiceDate());
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               onItemClickLister.onItemClick(position);

          }
        });
        boolean isck = list.get(position).isIsck();
        if (isck==false){
            holder.mIv.setImageResource(R.drawable.follow_unselected);
        }else {
            holder.mIv.setImageResource(R.drawable.follow);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvPic;
        TextView mTvTitle;
        TextView mTvDesc;
        TextView mTvAuthor;
        TextView mTvNicedater;
       ImageView mIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mIvPic = itemView.findViewById(R.id.iv_pic);
            this.mTvTitle = itemView.findViewById(R.id.tv_title);
            this.mTvDesc = itemView.findViewById(R.id.tv_desc);
            this.mTvAuthor = itemView.findViewById(R.id.tv_author);
            this.mTvNicedater = itemView.findViewById(R.id.tv_niceDater);
            this.mIv = itemView.findViewById(R.id.iv);
        }
    }

    private OnItemClickLister onItemClickLister;

    public void setOnItemClickLister(OnItemClickLister onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }

    public interface OnItemClickLister {
        void onItemClick(int position);
    }
}
