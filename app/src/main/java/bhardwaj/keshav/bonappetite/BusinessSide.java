package bhardwaj.keshav.bonappetite;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BusinessSide extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    LinearLayout Order, NoOrder;
    ImageView NoOrderImage;
    Button Press;

    Firebase rootref1, rootref, rootref2;

    List<OrderedItemsBusSide> DataClassList;
    OrderedItemsAdapter adapter;
    RecyclerView RecyclerListView;

    int keyFlag = 0;
    int CheckValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_side);

        Firebase.setAndroidContext(this);

        Order = (LinearLayout) findViewById(R.id.Order);
        NoOrder = (LinearLayout) findViewById(R.id.NoOrder);
        NoOrderImage = (ImageView) findViewById(R.id.NoOrderImage);

        rootref1 = new Firebase("https://bonappetite-54321.firebaseio.com/");
        rootref = rootref1.child("Main");
        rootref2 = rootref1.child("Data");

        /*rootref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });*/

        RecyclerListView = (RecyclerView) findViewById(R.id.OrderRecyclerList);
        DataClassList = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        RecyclerListView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerListView.setItemAnimator(new DefaultItemAnimator());

        /*
        ***********   How to switch current orders to no current orders  ***********
        Press = (Button) findViewById(R.id.PressMe);

        Press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int k = NoOrder.getVisibility();
                int l = Order.getVisibility();
                NoOrder.setVisibility(l);
                Order.setVisibility(k);
            }
        });
        *****************************************************************************/

        Press = (Button) findViewById(R.id.PressMe);

        Press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusinessSide.this, AddItemToMenu.class));

            }
        });


        /*DataClassList.add(new OrderedItemsBusSide(
                "A","B","C"
        ));
        DataClassList.add(new OrderedItemsBusSide(
                "D","E","F"
        ));
        DataClassList.add(new OrderedItemsBusSide(
                "G","H","I"
        ));*/
        DataClassList.clear();

        /*rootref.addValueEventListener(new com.firebase.client.ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                *****for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    DataClassList.add(new OrderedItemsBusSide(
                            noteDataSnapshot.child("Name").getValue().toString()
                            ,noteDataSnapshot.child("Count").getValue().toString()
                            ,noteDataSnapshot.child("Seat").getValue().toString()));
                }*****

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });*/


        adapter = new OrderedItemsAdapter(this, DataClassList);
        /*DataClassList.add(new OrderedItemsBusSide(
                "L","M","N"
        ));*/


        rootref.addChildEventListener(new com.firebase.client.ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               // DataClassList = new ArrayList<>();

                /*DataClassList.add(new OrderedItemsBusSide(
                        "L","M","N"
                ));*/



                DataClassList.add(new OrderedItemsBusSide(
                         dataSnapshot.child("Name").getValue().toString()
                        ,dataSnapshot.child("Count").getValue().toString()
                        ,dataSnapshot.child("Seat").getValue().toString()));

                /*DataClassList.clear();
                rootref2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                            DataClassList.add(new OrderedItemsBusSide(
                                    noteDataSnapshot.child("Name").getValue().toString()
                                    , noteDataSnapshot.child("Count").getValue().toString()
                                    , noteDataSnapshot.child("Seat").getValue().toString()));
                        }

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });*/
                if(keyFlag==0)
                    DataClassList.clear();
                else{
                    NoOrder.setVisibility(View.GONE);
                    Order.setVisibility(View.VISIBLE);
                    NoOrderImage.setVisibility(View.GONE);
                }
                keyFlag++;
                RecyclerListView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });







        /*adapter = new OrderedItemsAdapter(this, DataClassList);
        DataClassList.add(new OrderedItemsBusSide(
                "L","M","N"
        ));
        RecyclerListView.setAdapter(adapter);
            */

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(RecyclerListView);

    }



    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        if (viewHolder instanceof OrderedItemsAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String name = DataClassList.get(viewHolder.getAdapterPosition()).getItemName();

            // backup of removed item for undo purpose
            final OrderedItemsBusSide deletedItem = DataClassList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            CheckValue = adapter.removeItem(viewHolder.getAdapterPosition());
            if(CheckValue == 0){
                Order.setVisibility(View.GONE);
                NoOrder.setVisibility(View.VISIBLE);
                NoOrderImage.setVisibility(View.VISIBLE);
            }


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds cartList to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}