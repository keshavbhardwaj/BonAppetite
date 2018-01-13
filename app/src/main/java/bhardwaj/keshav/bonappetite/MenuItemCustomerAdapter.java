package bhardwaj.keshav.bonappetite;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by keshav on 10/1/18.
 */

public class MenuItemCustomerAdapter extends RecyclerView.Adapter<MenuItemCustomerAdapter.MyViewHolder> {

    private Context mContext;
    private List<MenuItemCustomerSideView> albumList;


    SQLiteDatabase PersonalDatabase;

    public MenuItemCustomerAdapter(Context mContext, List<MenuItemCustomerSideView> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.item_list_view_record, null);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        MenuItemCustomerSideView ClassData = albumList.get(position);
        holder.ShowItemName.setText( ClassData.getItemName().toString());
        holder.ShowItemCost.setText(ClassData.getCharges().toString());




        int Case = GetCheckedCase(ClassData);
        switch (Case){
            case 0:
                break;
            case 1: holder.Card.setBackgroundResource(R.drawable.list_rel_card_view_green);
                break;
            case 2: holder.Card.setBackgroundResource(R.drawable.list_rel_card_view_red);
                break;
        }
        //

        holder.DecCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Temp = Integer.parseInt(holder.ShowItemCount.getText().toString());
                if(Temp>0){
                    Temp--;
                }
                holder.ShowItemCount.setText(Integer.toString(Temp));
                holder.ShowItemCount2.setText(Integer.toString(Temp));

            }
        });

        holder.IncCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Temp = Integer.parseInt(holder.ShowItemCount.getText().toString());
                Temp++;
                holder.ShowItemCount.setText(Integer.toString(Temp));
                holder.ShowItemCount2.setText(Integer.toString(Temp));
            }
        });


    }

    private int GetCheckedCase(MenuItemCustomerSideView classData) {

            if(MenuItemCustomerSideView.getCold()==1) {
                if (Integer.parseInt(classData.Temp) < 15) {
                    return 2;
                }
                if(Integer.parseInt(classData.Temp) > 60){
                    return 1;
                }
            }

            if(MenuItemCustomerSideView.getLowBP()==1) {
                /*for(int i=0; i<classData.Ingredients.size();i++){
                    if(classData.Ingredients.get(i)=="  CHOCLATE")
                        return 2;
                }*/
                if(classData.Ingredients1.indexOf("CHOCLATE")!= -1){
                    return 1;
                }
            }

        return 0;
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ShowItemName, ShowItemCost;
        TextView ShowItemCount,ShowItemCount2;
        Button IncCount, DecCount;
        RelativeLayout Card;

        public MyViewHolder(View itemView) {
            super(itemView);
            ShowItemCost = (TextView) itemView.findViewById(R.id.MenuItemCostCust);
            ShowItemName = (TextView) itemView.findViewById(R.id.MenuItemNameForCust);

            ShowItemCount = (TextView) itemView.findViewById(R.id.MenuItemCountCust);
            ShowItemCount2 = (TextView) itemView.findViewById(R.id.MenuItemCountForCust);

            IncCount = (Button) itemView.findViewById(R.id.incrementCount);
            DecCount = (Button) itemView.findViewById(R.id.decrementCount);

            Card = (RelativeLayout) itemView.findViewById(R.id.SingleCardRelativeLayout);
        }
    }




}
