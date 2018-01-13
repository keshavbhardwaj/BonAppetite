package bhardwaj.keshav.bonappetite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class PersonalData extends AppCompatActivity {

    ImageButton Edit;
    TableLayout LikeTable, DislikeTable, DiseaseTable, AllergiesTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);

        Edit = (ImageButton) findViewById(R.id.FillPersonalDetails);

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalData.this,FillPerData.class));
            }
        });

        AllergiesTable = (TableLayout) findViewById(R.id.Tbl4);
        DiseaseTable = (TableLayout) findViewById(R.id.Tbl3);
        DislikeTable = (TableLayout) findViewById(R.id.Tbl2);
        LikeTable = (TableLayout) findViewById(R.id.Tbl1);

        SQLiteDatabase PersonalDatabase = openOrCreateDatabase("PersonalDatabase",0, null);
        PersonalDatabase.execSQL("create table if not exists PersonalData( Categoray varchar(40), Names varchar(50));");

        String[] DomainNames = {"Allergies", "Disease", "Dislikes", "Likes"};

        for(int cIndex=0; cIndex<4; cIndex++){
            Cursor GetTableEntry = PersonalDatabase.rawQuery("select * from PersonalData where Categoray = \""+DomainNames[cIndex]+"\";",null);
            int countT = GetTableEntry.getCount();
            GetTableEntry.moveToFirst();
            while(countT != 0){

                String Str12 = GetTableEntry.getString(1);
                TableFromSQL(cIndex,Str12);
                countT--;
                GetTableEntry.moveToNext();
            }}


    }

    public void TableFromSQL (final int Offset, final String NameValue){


        TableRow R1 = new TableRow(PersonalData.this);
        RelativeLayout L1 = new RelativeLayout(PersonalData.this);
        RelativeLayout L2 = new RelativeLayout(PersonalData.this);
        //L1.setBackgroundResource(R.drawable.addingredientbox);
        //LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //L1.setLayoutParams(params1);
                /*RelativeLayout.LayoutParams prams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                prams.addRule(RelativeLayout.BELOW, TopId);
                prams.addRule(RelativeLayout.ABOVE, R.id.IngredientToFill);

                L1.setId(View.generateViewId());
                TopId = L1.getId();
                */
        RelativeLayout.LayoutParams T1params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        T1params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        RelativeLayout.LayoutParams B1params = new RelativeLayout.LayoutParams(41,40);
        B1params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);


        TextView T1,T2,T3;
        FrameLayout F1;
        T1 = new TextView(PersonalData.this);
        T1.setTextSize(15);
        T2 = new TextView(PersonalData.this);
        T2.setTextSize(15);



        T1.setText(NameValue.toUpperCase());

        T2.setText(" ");

        T1.setGravity(View.TEXT_ALIGNMENT_CENTER);
        //B1.setGravity(Gravity.TOP);
        T1.setPadding(60,0,0,0);

        //B1.setImageResource(android.R.drawable.ic_delete);
        //B1.setText("x");
        //B1.setTextColor(Color.RED);

        L1.addView( T1);



        R1.addView(L1);
        R1.addView(T2);

        switch (Offset){
            case 0:AllergiesTable.addView(R1);
                break;
            case 1:DiseaseTable.addView(R1);
                break;
            case 2:DislikeTable.addView(R1);
                break;
            case 3:LikeTable.addView(R1);
                break;
        }


    }
}