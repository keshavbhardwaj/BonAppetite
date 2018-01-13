package bhardwaj.keshav.bonappetite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class CustomerSide extends AppCompatActivity implements View.OnClickListener{

    ImageButton PersonalData, ScanQR, PlaceOrder, GetMenu;
    TextView ScanQRtext, PlaceOrderText, GetMenuText;
    private IntentIntegrator qrScan;
    TextView textViewName;
    final String ServiceProviderName = "AirLine1";
    FirebaseFirestore MenuDB;
    int Cnt, i;
    String A, B, C, D, E;
    SQLiteDatabase SQLDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_side);


        MenuDB = FirebaseFirestore.getInstance();


        PersonalData = (ImageButton) findViewById(R.id.PersonalDetails);
        ScanQR = (ImageButton) findViewById(R.id.ScanQR);
        PlaceOrder = (ImageButton) findViewById(R.id.PlaceOrder);
        textViewName = (TextView) findViewById(R.id.TextNameScan);
        GetMenu = (ImageButton) findViewById(R.id.DownloadMenu);

        ScanQRtext = (TextView) findViewById(R.id.ScanQRtext);
        PlaceOrderText = (TextView) findViewById(R.id.PlaceOrderText);
        GetMenuText = (TextView) findViewById(R.id.DownloadMenuText);

        qrScan = new IntentIntegrator(this);
        ScanQR.setOnClickListener(this);



        SQLiteDatabase PersonalDatabase = openOrCreateDatabase("PersonalDatabase",0,null);
        PersonalDatabase.execSQL("create table if not exists PersonalData( Categoray varchar(40), Names varchar(50));");
        Cursor GetTableEntry = PersonalDatabase.rawQuery("select * from PersonalData where Names = \"-  COLD\";",null);
        if(0 < GetTableEntry.getCount()){
            MenuItemCustomerSideView.setCold(1);
        }
        else {
            MenuItemCustomerSideView.setCold(0);
        }

        Cursor GetTableEntry1 = PersonalDatabase.rawQuery("select * from PersonalData where Names = \"-  LOW BP\";",null);
        if(0 < GetTableEntry1.getCount()){
            MenuItemCustomerSideView.setLowBP(1);
        }
        else {
            MenuItemCustomerSideView.setLowBP(0);
        }





        PersonalData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerSide.this, PersonalData.class));
            }
        });

        PlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerSide.this, MenuList.class);
                intent.putExtra("Count", Cnt);
                intent.putExtra("Seat",textViewName.getText().toString());
                startActivity(intent);

                //startActivity(new Intent(CustomerSide.this, MenuList.class));
            }
        });

        GetMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLDB = openOrCreateDatabase("MenuItems",0,null);
                SQLDB.execSQL("drop table if exists MenuTable;");
                SQLDB.execSQL("create table MenuTable(id varchar(20), name varchar(30), cost varchar(30), tmpr varchar(5), ingredients varchar(60));");

                DocumentReference Item_Count = MenuDB.collection(ServiceProviderName).document("2NzBBFVRpromSOj5DRBn");

                Item_Count.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (true) {
                            DocumentSnapshot Doc = task.getResult();
                            Cnt = Integer.parseInt(Doc.getString("Count"));

                        }
                    }
                });

                for(i=1; i<=Cnt; i++){

                    DocumentReference Temp_Ref = MenuDB.collection(ServiceProviderName).document(Integer.toString(i));
                    Temp_Ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                DocumentSnapshot Doc = task.getResult();
                                A =  Doc.getString("Charges").toString();
                                B =  Doc.getString("ItemName").toString();
                                C =  Doc.getString("ItemId").toString();
                                D =  Doc.getString("Temp").toString();
                                E =  Doc.getString("Ingredients").toString();
                                String STemp = "insert into MenuTable values(\""+C+"\",\""+B+"\",\""+A+"\",\""+D+"\",\""+E+"\");";
                                SQLDB.execSQL(STemp);

                        }

                    });
                    if(i==Cnt){
                    GetMenu.setVisibility(View.GONE);
                    GetMenuText.setVisibility(View.GONE);
                    PlaceOrder.setVisibility(View.VISIBLE);
                    PlaceOrderText.setVisibility(View.VISIBLE);
                    }
                }


            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                textViewName.setText(result.getContents().toString());
                ScanQR.setVisibility(View.GONE);
                ScanQRtext.setVisibility(View.GONE);
                GetMenu.setVisibility(View.VISIBLE);
                GetMenuText.setVisibility(View.VISIBLE);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void onClick(View view) {
        //initiating the qr code scan
        qrScan.initiateScan();
    }
}