package com.justwayward.booker.ui.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.justwayward.booker.R;
import com.justwayward.booker.utils.LogUtils;

import java.util.List;
/*this place is the state for rewrite the adapter.*/
public class MsgAdapter extends ArrayAdapter<Msg> {
    private int resourceId;/*this place define the new parameter and the next is the the constructor. like the concrete struction.*/
    public MsgAdapter(Context context, int textViewResourceId, List<Msg> objects)
    {
        super(context,textViewResourceId,objects);/*this is the define */
        resourceId = textViewResourceId;
    }
/*overwrite the function*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent)//每个子项滚动到屏幕中的时候调用，
    {
        Msg msg = getItem(position);/*this is for the concrete place*///得到当前的实例，
        View view;/*1*/
        ViewHolder viewHolder;/*2*/
        if (convertView == null)/*this is for the initial*/
        {
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);/*get a view*///为子项加载我们传入的布局。
            viewHolder = new ViewHolder();
            viewHolder.leftLayout=(LinearLayout) view.findViewById(R.id.left_layout);//
            viewHolder.rightLayout=(LinearLayout) view.findViewById(R.id.right_layout);//
            viewHolder.leftMsg = (TextView) view.findViewById(R.id.left_msg);//
            viewHolder.rightMsg=(TextView) view.findViewById(R.id.right_msg);//以上四条为获取实例。
            view.setTag(viewHolder);//setTag(key,object)
        }
        else {
            view = convertView;
            viewHolder=(ViewHolder) view.getTag();//getTag(key)
        }

        if (msg.getType() == Msg.TYPE_RECEIVED)
        {
            LogUtils.i("(msg.getType() == Msg.TYPE_RECEIVED) ");
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.leftMsg.setText(msg.getContent());//设置需要显示的内容，到此，结构上完成了适配器的配置。
        }
        else if(msg.getType() == Msg.TYPE_SENT)
        {
            LogUtils.i("(msg.getType() == Msg.TYPE_SENT) ");
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightMsg.setText(msg.getContent());
        }
        return view;
    }
    class ViewHolder{/*get out from the objects*/
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
    }
}

