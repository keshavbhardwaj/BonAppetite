package bhardwaj.keshav.bonappetite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class FillPerData extends AppCompatActivity {


    Button Save, Cancel;
    ImageButton AddLikes,AddDislikes,AddDisease,AddAllergies;
    TableLayout AddedTableLikes,AddedTableDislikes,AddedTableDisease,AddedTableAllergies;
    TextView LikesText, DislikesText,DiseaseText,AllergiesText;

    int AllergiesCount = 1;
    int LikesCount = 1;
    int DislikesCount = 1;
    int DiseaseCount = 1;

    int ButtonIdCount = 0;
    int TextIdCount = 0;
    int ButtonIdCountDisease = 0;
    int TextIdCountDisease = 0;
    int ButtonIdCountDislikes = 0;
    int TextIdCountDislikes = 0;
    int ButtonIdCountLikes = 0;
    int TextIdCountLikes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_per_data);


        Save = (Button) findViewById(R.id.save);
        Cancel = (Button) findViewById(R.id.cancel);

        AddAllergies = (ImageButton) findViewById(R.id.AddAllergies);
        AddDisease = (ImageButton) findViewById(R.id.AddDisease);
        AddDislikes = (ImageButton) findViewById(R.id.AddDislikes);
        AddLikes = (ImageButton) findViewById(R.id.AddLikes);

        AddedTableAllergies = (TableLayout) findViewById(R.id.AddedTableAllergies);
        AddedTableDisease = (TableLayout) findViewById(R.id.AddedTableDisease);
        AddedTableDislikes = (TableLayout) findViewById(R.id.AddedTableDislike);
        AddedTableLikes = (TableLayout) findViewById(R.id.AddedTableLike);

        LikesText = (TextView) findViewById(R.id.LikeText);
        DislikesText = (TextView) findViewById(R.id.DislikeText);
        DiseaseText = (TextView) findViewById(R.id.DiseaseText);
        AllergiesText = (TextView) findViewById(R.id.AllergiesText);


        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase PersonalDatabase = openOrCreateDatabase("PersonalDatabase",0, null);
                PersonalDatabase.execSQL("drop table if exists PersonalData;");
                PersonalDatabase.execSQL("create table PersonalData( Categoray varchar(40), Names varchar(50));");
                /*-******Category and Name of all respective things...******/


                for(int i=0; i<AddedTableLikes.getChildCount();i++){
                    TextView Textview1 = (TextView) findViewById(i+3301);
                    String TempName = Textview1.getText().toString();
                    PersonalDatabase.execSQL("insert into PersonalData values(\"Likes\",\""+TempName+"\");");
                }

                for(int i=0; i<AddedTableDislikes.getChildCount();i++){
                    TextView Textview1 = (TextView) findViewById(i+3201);
                    String TempName = Textview1.getText().toString();
                    PersonalDatabase.execSQL("insert into PersonalData values(\"Dislikes\",\""+TempName+"\");");
                }

                for(int i=0; i<AddedTableDisease.getChildCount();i++){
                    TextView Textview1 = (TextView) findViewById(i+3101);
                    String TempName = Textview1.getText().toString();
                    PersonalDatabase.execSQL("insert into PersonalData values(\"Disease\",\""+TempName+"\");");
                }

                for(int i=0; i<AddedTableAllergies.getChildCount();i++){
                    TextView Textview1 = (TextView) findViewById(i+3001);
                    String TempName = Textview1.getText().toString();
                    PersonalDatabase.execSQL("insert into PersonalData values(\"Allergies\",\""+TempName+"\");");
                }



                onBackPressed();
            }
        });







        AddAllergies.setOnClickListener(new View.OnClickListener() {
            //@SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                AlertDialog.Builder Bldr = new AlertDialog.Builder(FillPerData.this);
                Bldr.setMessage("Can not store an empty string!").setNegativeButton("OK",null);
                AlertDialog alert = Bldr.create();

                if(AllergiesText.getText().toString().length() == 0){
                    alert.show();
                    return;
                }


                TableRow R1 = new TableRow(FillPerData.this);
                RelativeLayout L1 = new RelativeLayout(FillPerData.this);
                RelativeLayout L2 = new RelativeLayout(FillPerData.this);
                L1.setBackgroundResource(R.drawable.addingredientbox);
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
                final ImageButton B1;
                FrameLayout F1;
                T1 = new TextView(FillPerData.this);
                T1.setTextSize(15);
                T2 = new TextView(FillPerData.this);
                T2.setTextSize(15);
                T3 = new TextView(FillPerData.this);
                B1 = new ImageButton(FillPerData.this);
                F1 = new FrameLayout(FillPerData.this);

                B1.setBackgroundResource(R.drawable.circle_shade_red);
                //B1.setLayoutParams(B1params);
                String TempStr = (String) AllergiesText.getHint();

                T1.setText("-  "+AllergiesText.getText().toString().toUpperCase());
                AllergiesText.setHint("   Add Allergies ");
                T2.setText(" ");
                T3.setText("  x");
                T3.setTextColor(Color.BLACK);

                T1.setGravity(View.TEXT_ALIGNMENT_CENTER);
                //B1.setGravity(Gravity.TOP);
                T1.setPadding(60,0,0,0);
                T1.setId(++TextIdCount+3000);
                B1.setId(++ButtonIdCount+2000);
                B1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getParent();
                        View tempV = (View) v;
                        int id = tempV.getId();
                        AddedTableAllergies.removeViewAt(id-2000-1);
                        for(int i=id+1,j=id-2000;j<=AddedTableAllergies.getChildCount();j++,i++){

                            //AllergiesText.setText(Integer.toString(AddedTableAllergies.getChildCount()));
                            View TempBut = (View) findViewById(i);
                            View TempTxt = (View) findViewById(i+1000);
                            TempBut.setId(i-1);
                            TempTxt.setId(i+999);
                        }
                        ButtonIdCount--;
                        TextIdCount--;
                        AllergiesCount--;
                    }
                });
                //B1.setImageResource(android.R.drawable.ic_delete);
                //B1.setText("x");
                //B1.setTextColor(Color.RED);

                L1.addView( T1);
                F1.addView(B1);
                F1.addView(T3);

                F1.setLayoutParams(B1params);

                L1.addView(F1);

                R1.addView(L1);
                R1.addView(T2);

                AddedTableAllergies.addView(R1);

                AllergiesText.setText(null);

            }
        });


        AddDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder Bldr = new AlertDialog.Builder(FillPerData.this);
                Bldr.setMessage("Can not store an empty string!").setNegativeButton("OK",null);
                AlertDialog alert = Bldr.create();

                if(DiseaseText.getText().toString().length() == 0){
                    alert.show();
                    return;
                }

                TableRow R1 = new TableRow(FillPerData.this);
                RelativeLayout L1 = new RelativeLayout(FillPerData.this);
                RelativeLayout L2 = new RelativeLayout(FillPerData.this);
                L1.setBackgroundResource(R.drawable.addingredientbox);
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
                final ImageButton B1;
                FrameLayout F1;
                T1 = new TextView(FillPerData.this);
                T1.setTextSize(15);
                T2 = new TextView(FillPerData.this);
                T2.setTextSize(15);
                T3 = new TextView(FillPerData.this);
                B1 = new ImageButton(FillPerData.this);
                F1 = new FrameLayout(FillPerData.this);

                B1.setBackgroundResource(R.drawable.circle_shade_red);
                //B1.setLayoutParams(B1params);
                String TempStr = (String) DiseaseText.getHint();
                T1.setText("-  "+DiseaseText.getText().toString().toUpperCase());
                DiseaseText.setHint("   Add Disease ");
                T2.setText(" ");
                T3.setText("  x");
                T3.setTextColor(Color.BLACK);

                T1.setGravity(View.TEXT_ALIGNMENT_CENTER);
                //B1.setGravity(Gravity.TOP);
                T1.setPadding(60,0,0,0);
                T1.setId(++TextIdCountDisease+3100);
                B1.setId(++ButtonIdCountDisease+2100);
                B1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getParent();
                        View tempV = (View) v;
                        int id = tempV.getId();
                        AddedTableDisease.removeViewAt(id-2100-1);
                        for(int i=id+1,j=id-2100;j<=AddedTableDisease.getChildCount();j++,i++){

                            //AllergiesText.setText(Integer.toString(AddedTableAllergies.getChildCount()));
                            View TempBut = (View) findViewById(i);
                            View TempTxt = (View) findViewById(i+1000);
                            TempBut.setId(i-1);
                            TempTxt.setId(i+999);
                        }
                        ButtonIdCountDisease--;
                        TextIdCountDisease--;
                        DiseaseCount--;
                    }
                });
                //B1.setImageResource(android.R.drawable.ic_delete);
                //B1.setText("x");
                //B1.setTextColor(Color.RED);

                L1.addView( T1);
                F1.addView(B1);
                F1.addView(T3);

                F1.setLayoutParams(B1params);

                L1.addView(F1);

                R1.addView(L1);
                R1.addView(T2);

                AddedTableDisease.addView(R1);

                DiseaseText.setText(null);


            }
        });
        AddDislikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder Bldr = new AlertDialog.Builder(FillPerData.this);
                Bldr.setMessage("Can not store an empty string!").setNegativeButton("OK",null);
                AlertDialog alert = Bldr.create();

                if(DislikesText.getText().toString().length() == 0){
                    alert.show();
                    return;
                }

                TableRow R1 = new TableRow(FillPerData.this);
                RelativeLayout L1 = new RelativeLayout(FillPerData.this);
                RelativeLayout L2 = new RelativeLayout(FillPerData.this);
                L1.setBackgroundResource(R.drawable.addingredientbox);
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
                final ImageButton B1;
                FrameLayout F1;
                T1 = new TextView(FillPerData.this);
                T1.setTextSize(15);
                T2 = new TextView(FillPerData.this);
                T2.setTextSize(15);
                T3 = new TextView(FillPerData.this);
                B1 = new ImageButton(FillPerData.this);
                F1 = new FrameLayout(FillPerData.this);

                B1.setBackgroundResource(R.drawable.circle_shade_red);
                //B1.setLayoutParams(B1params);
                String TempStr = (String) DislikesText.getHint();
                T1.setText("-  "+DislikesText.getText().toString().toUpperCase());
                DislikesText.setHint("   Add Dislikes ");
                T2.setText(" ");
                T3.setText("  x");
                T3.setTextColor(Color.BLACK);

                T1.setGravity(View.TEXT_ALIGNMENT_CENTER);
                //B1.setGravity(Gravity.TOP);
                T1.setPadding(60,0,0,0);
                T1.setId(++TextIdCountDislikes+3200);
                B1.setId(++ButtonIdCountDislikes+2200);
                B1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getParent();
                        View tempV = (View) v;
                        int id = tempV.getId();
                        AddedTableDislikes.removeViewAt(id-2200-1);
                        for(int i=id+1,j=id-2200;j<=AddedTableDislikes.getChildCount();j++,i++){

                            //AllergiesText.setText(Integer.toString(AddedTableAllergies.getChildCount()));
                            View TempBut = (View) findViewById(i);
                            View TempTxt = (View) findViewById(i+1000);
                            TempBut.setId(i-1);
                            TempTxt.setId(i+999);
                        }
                        ButtonIdCountDislikes--;
                        TextIdCountDislikes--;
                        DislikesCount--;
                    }
                });
                //B1.setImageResource(android.R.drawable.ic_delete);
                //B1.setText("x");
                //B1.setTextColor(Color.RED);

                L1.addView( T1);
                F1.addView(B1);
                F1.addView(T3);

                F1.setLayoutParams(B1params);

                L1.addView(F1);

                R1.addView(L1);
                R1.addView(T2);

                AddedTableDislikes.addView(R1);

                DislikesText.setText(null);


            }
        });
        AddLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder Bldr = new AlertDialog.Builder(FillPerData.this);
                Bldr.setMessage("Can not store an empty string!").setNegativeButton("OK",null);
                AlertDialog alert = Bldr.create();

                if(LikesText.getText().toString().length() == 0){
                    alert.show();
                    return;
                }

                TableRow R1 = new TableRow(FillPerData.this);
                RelativeLayout L1 = new RelativeLayout(FillPerData.this);
                RelativeLayout L2 = new RelativeLayout(FillPerData.this);
                L1.setBackgroundResource(R.drawable.addingredientbox);
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
                final ImageButton B1;
                FrameLayout F1;
                T1 = new TextView(FillPerData.this);
                T1.setTextSize(15);
                T2 = new TextView(FillPerData.this);
                T2.setTextSize(15);
                T3 = new TextView(FillPerData.this);
                B1 = new ImageButton(FillPerData.this);
                F1 = new FrameLayout(FillPerData.this);

                B1.setBackgroundResource(R.drawable.circle_shade_red);
                //B1.setLayoutParams(B1params);
                String TempStr = (String) LikesText.getHint();
                T1.setText("-  "+LikesText.getText().toString().toUpperCase());
                LikesText.setHint("   Add Likes ");
                T2.setText(" ");
                T3.setText("  x");
                T3.setTextColor(Color.BLACK);

                T1.setGravity(View.TEXT_ALIGNMENT_CENTER);
                //B1.setGravity(Gravity.TOP);
                T1.setPadding(60,0,0,0);
                T1.setId(++TextIdCountLikes+3300);
                B1.setId(++ButtonIdCountLikes+2300);
                B1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getParent();
                        View tempV = (View) v;
                        int id = tempV.getId();
                        AddedTableLikes.removeViewAt(id-2300-1);
                        for(int i=id+1,j=id-2300;j<=AddedTableLikes.getChildCount();j++,i++){

                            //AllergiesText.setText(Integer.toString(AddedTableAllergies.getChildCount()));
                            View TempBut = (View) findViewById(i);
                            View TempTxt = (View) findViewById(i+1000);
                            TempBut.setId(i-1);
                            TempTxt.setId(i+999);
                        }
                        ButtonIdCountLikes--;
                        TextIdCountLikes--;
                        LikesCount--;
                    }
                });
                //B1.setImageResource(android.R.drawable.ic_delete);
                //B1.setText("x");
                //B1.setTextColor(Color.RED);

                L1.addView( T1);
                F1.addView(B1);
                F1.addView(T3);

                F1.setLayoutParams(B1params);

                L1.addView(F1);

                R1.addView(L1);
                R1.addView(T2);

                AddedTableLikes.addView(R1);

                LikesText.setText(null);

            }
        });


    }




    public void TableFromSQL (final int Offset, final String NameValue){

        final int[] ArButtonIdCount = {ButtonIdCount,ButtonIdCountDisease,ButtonIdCountDislikes,ButtonIdCountLikes};
        final int[] ArTextIdCount = {TextIdCount,TextIdCountDisease,TextIdCountDislikes,TextIdCountLikes};

        TableRow R1 = new TableRow(FillPerData.this);
        RelativeLayout L1 = new RelativeLayout(FillPerData.this);
        RelativeLayout L2 = new RelativeLayout(FillPerData.this);
        L1.setBackgroundResource(R.drawable.addingredientbox);
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
        final ImageButton B1;
        FrameLayout F1;
        T1 = new TextView(FillPerData.this);
        T1.setTextSize(15);
        T2 = new TextView(FillPerData.this);
        T2.setTextSize(15);
        T3 = new TextView(FillPerData.this);
        B1 = new ImageButton(FillPerData.this);
        F1 = new FrameLayout(FillPerData.this);

        B1.setBackgroundResource(R.drawable.circle_shade_red);
        //B1.setLayoutParams(B1params);


        T1.setText(NameValue.toUpperCase());

        T2.setText(" ");
        T3.setText("  x");
        T3.setTextColor(Color.BLACK);

        T1.setGravity(View.TEXT_ALIGNMENT_CENTER);
        //B1.setGravity(Gravity.TOP);
        T1.setPadding(60,0,0,0);
        T1.setId(++ArTextIdCount[Offset]+3000+(Offset*100));
        B1.setId(++ArButtonIdCount[Offset]+2000+(Offset*100));
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getParent();
                View tempV = (View) v;
                int id = tempV.getId();
                int max=0;
                switch (Offset){
                    case 0:AddedTableAllergies.removeViewAt(id-2000-1);
                        max = AddedTableAllergies.getChildCount();
                        break;
                    case 1:AddedTableDisease.removeViewAt(id-2100-1);
                        max = AddedTableDisease.getChildCount();
                        break;
                    case 2:AddedTableDislikes.removeViewAt(id-2200-1);
                        max = AddedTableDislikes.getChildCount();
                        break;
                    case 3:AddedTableLikes.removeViewAt(id-2300-1);
                        max = AddedTableLikes.getChildCount();
                        break;
                }

                for(int i=id+1,j=id-2000-(Offset*100);j<=max;j++,i++){

                    //AllergiesText.setText(Integer.toString(AddedTableAllergies.getChildCount()));
                    View TempBut = (View) findViewById(i);
                    View TempTxt = (View) findViewById(i+1000);
                    TempBut.setId(i-1);
                    TempTxt.setId(i+999);
                }
                ArButtonIdCount[Offset] -= 1;
                ArTextIdCount[Offset] -= 1;

            }
        });
        //B1.setImageResource(android.R.drawable.ic_delete);
        //B1.setText("x");
        //B1.setTextColor(Color.RED);

        L1.addView( T1);
        F1.addView(B1);
        F1.addView(T3);

        F1.setLayoutParams(B1params);

        L1.addView(F1);

        R1.addView(L1);
        R1.addView(T2);

        switch (Offset){
            case 0:AddedTableAllergies.addView(R1);
                break;
            case 1:AddedTableDisease.addView(R1);
                break;
            case 2:AddedTableDislikes.addView(R1);
                break;
            case 3:AddedTableLikes.addView(R1);
                break;
        }

        ButtonIdCount = ArButtonIdCount[0];
        TextIdCount = ArTextIdCount[0];
        ButtonIdCountDisease = ArButtonIdCount[1];
        TextIdCountDisease = ArTextIdCount[1];
        ButtonIdCountDislikes = ArButtonIdCount[2];
        TextIdCountDislikes = ArTextIdCount[2];
        ButtonIdCountLikes = ArButtonIdCount[3];
        TextIdCountLikes = ArTextIdCount[3];
    }

}