package bhardwaj.keshav.bonappetite;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by keshav on 11/1/18.
 */

public class OrderedItemsAdapter extends RecyclerView.Adapter<OrderedItemsAdapter.MyViewHolder> {


    private Context mContext;
    private List<OrderedItemsBusSide> albumList;

    public OrderedItemsAdapter(Context mContext, List<OrderedItemsBusSide> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.order_item_r_list_view, null);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        OrderedItemsBusSide  ClassData = albumList.get(position);
        holder.ShowSeatNo.setText(ClassData.getSeatNo());
        holder.ShowItemCount.setText(ClassData.getItemCount());
        holder.ShowItemName.setText(ClassData.getItemName());

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ShowItemName, ShowItemCount, ShowSeatNo;
        public View viewForeground;

        public MyViewHolder(View itemView) {
            super(itemView);
            ShowItemCount = (TextView) itemView.findViewById(R.id.OrderItemCount);
            ShowItemName = (TextView) itemView.findViewById(R.id.OrderItemName);
            ShowSeatNo = (TextView) itemView.findViewById(R.id.OrderSeatNo);
            viewForeground = (View) itemView.findViewById(R.id.viewForeground);
        }
    }

    public int removeItem(int position) {
        albumList.remove(position);

        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
        return albumList.size();
    }

    /*public void restoreItem(ClipData.Item item, int position) {
        albumList.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }*/
}
