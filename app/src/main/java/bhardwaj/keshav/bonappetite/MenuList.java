package bhardwaj.keshav.bonappetite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class MenuList extends AppCompatActivity {

    List<MenuItemCustomerSideView> DataClassList;
    MenuItemCustomerSideView[] DataClassArray;
    final String ServiceProviderName = "AirLine1";
    String SeatNo;
    MenuItemCustomerAdapter adapter;
    FirebaseFirestore MenuDB;
    RecyclerView RecyclerListView;
    int Cnt;
    int i;
    Button PlaceOrder, CancelOrder;

    SQLiteDatabase SQLDB;
    Firebase OrderRef, OrderRef2, OrderRef3;
    String A,B,C,D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            Cnt = bundle.getInt("Count");
            SeatNo = bundle.getString("Seat");
        }

        Firebase.setAndroidContext(this);

        MenuDB = FirebaseFirestore.getInstance();
        OrderRef = new Firebase("https://bonappetite-54321.firebaseio.com/");
        OrderRef3 = OrderRef.child("Main");
        //OrderRef2 = new Firebase("https://bonappetite-54321.firebaseio.com/");
        OrderRef2 = OrderRef.child("Data");
        RecyclerListView = (RecyclerView) findViewById(R.id.RecyclerMenuList);
        PlaceOrder = (Button) findViewById(R.id.ProceedOrder);
        CancelOrder = (Button) findViewById(R.id.CancelItemSelection);


               /* DocumentReference Item_Count = MenuDB.collection(ServiceProviderName).document("2NzBBFVRpromSOj5DRBn");

                Item_Count.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (true) {
                            DocumentSnapshot Doc = task.getResult();
                            Cnt = Integer.parseInt(Doc.getString("Count"));
                            PlaceOrder.setText(Integer.toString(Cnt));
                        }
                    }
                });*/



        //DataClassArray = new MenuItemCustomerSideView[Cnt];

        /*for(i=1; i<=Cnt; i++){

            DocumentReference Temp_Ref = MenuDB.collection(ServiceProviderName).document(Integer.toString(2));
            Temp_Ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isComplete()) {
                        DocumentSnapshot Doc = task.getResult();
                        DataClassArray[i-1].setCharges( Doc.getString("Charges").toString());
                        DataClassArray[i-1].setItemName( Doc.getString("ItemName").toString());
                        DataClassArray[i-1].setItemId( Doc.getString("ItemId").toString());
                        DataClassArray[i-1].setTemp( Doc.getString("Temp").toString());
                        DataClassArray[i-1].setIngredients( Doc.getString("Ingredients").toString());

                    }
                }
            });

        }


        DataClassList = Arrays.asList(DataClassArray);
            */
        DataClassList = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        RecyclerListView.setLayoutManager(new LinearLayoutManager(this));
        //RecyclerListView.hasFixedSize(true);
        //RecyclerListView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        RecyclerListView.setItemAnimator(new DefaultItemAnimator());



        /*DataClassList.add(new MenuItemCustomerSideView(
                "A","B","C","D"
        ));*/

        SQLDB = openOrCreateDatabase("MenuItems",0,null);
        SQLDB.execSQL("create table if not exists MenuTable(id varchar(20), name varchar(30), cost varchar(30), tmpr varchar(5), ingredients varchar(60));");

        Cursor C = SQLDB.rawQuery("select * from MenuTable;",null);
        C.moveToFirst();
        int CountOfCursor = C.getCount();
        while (CountOfCursor !=0){
            DataClassList.add(new MenuItemCustomerSideView(
                    C.getString(1),
            C.getString(2),
            C.getString(3),
            C.getString(0),
            C.getString(4)
            ));

            C.moveToNext();
            CountOfCursor--;
        }

      /* for(i=1; i<=Cnt; i++){

            DocumentReference Temp_Ref = MenuDB.collection(ServiceProviderName).document(Integer.toString(2));
            Temp_Ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (true) {
                        DocumentSnapshot Doc = task.getResult();
                        DataClassList.add(new MenuItemCustomerSideView(Doc.getString("ItemName").toString()
                                ,Doc.getString("Charges").toString()
                                ,Doc.getString("Temp").toString()
                                ,Doc.getString("ItemId").toString()
                                ,Doc.getString("Ingredients").toString()));

                        //PlaceOrder.setText(Integer.toString(DataClassList.size()));
                    }
                }
            });

        }*/

        /*for(i=1; i<=Cnt; i++){

            DocumentReference Temp_Ref = MenuDB.collection(ServiceProviderName).document(Integer.toString(2));
            Temp_Ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (true) {
                        DocumentSnapshot Doc = task.getResult();
                        A =  Doc.getString("Charges").toString();
                        A = "Keshav";
                        DataClassArray[i-1].setItemName( Doc.getString("ItemName").toString());
                        DataClassArray[i-1].setItemId( Doc.getString("ItemId").toString());
                        DataClassArray[i-1].setTemp( Doc.getString("Temp").toString());
                        DataClassArray[i-1].setIngredients( Doc.getString("Ingredients").toString());

                    }
                }
            });

        }*/

        //PlaceOrder.setText(Integer.toString(DataClassList.size()));


        adapter = new MenuItemCustomerAdapter(this, DataClassList);
        RecyclerListView.setAdapter(adapter);


        PlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0; i<RecyclerListView.getChildCount(); i++){
                    View TempV1 = (View) RecyclerListView.getChildAt(i);
                    TextView TempV2 = (TextView) TempV1.findViewById(R.id.MenuItemCountForCust);
                    TextView TempV3 = (TextView) TempV1.findViewById(R.id.MenuItemNameForCust);
                    //SeatNo
                    if(Integer.parseInt(TempV2.getText().toString())>0){

                        String keyTemp = OrderRef2.push().getKey();
                        Firebase mchild01 = OrderRef2.child(keyTemp);
                        Firebase mchildN = mchild01.child("Name");
                        mchildN.setValue(TempV3.getText().toString());
                        Firebase mchildC = mchild01.child("Count");
                        mchildC.setValue(TempV2.getText().toString());
                        Firebase mchildS = mchild01.child("Seat");
                        mchildS.setValue(SeatNo);


                        Map<String, HashMap<String, String>> userMap= new HashMap<String, HashMap<String, String>>();
                        HashMap<String, String> tempObject = new HashMap<String, String>();


                            tempObject.put("Name",TempV3.getText().toString());
                            tempObject.put("Count",TempV2.getText().toString());
                            tempObject.put("Seat",SeatNo);


                        userMap.put(keyTemp, tempObject);
                        OrderRef3.setValue(userMap);




                    }
                }
                Toast.makeText(MenuList.this,"Order Placed",Toast.LENGTH_LONG);
                //OrderRef.child("Main").rem();
                onBackPressed();
            }
        });

        CancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
}
