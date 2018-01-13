package bhardwaj.keshav.bonappetite;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

public class AddItemToMenu extends AppCompatActivity {

    Button Save, Cancel, Prep;
    ImageButton AddIngredient;
    TableLayout MainL;
    TextView IngName, IngAmout, IdI, NameI, ChargesI, TempI;
    int TopId;
    int Cnt=0;
    int idCount = 0, IngredientCount = 1;
    final String ServiceProviderName = "AirLine1";
    FirebaseFirestore MenuDB;
    int TextIdCount=0, ButtonIdCount=0;
    Map<String,Object> ItemCountInc =  new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_to_menu);
        Cancel = (Button) findViewById(R.id.CancelItemAddition);
        Save = (Button) findViewById(R.id.SaveItemAddition);
        Prep = (Button) findViewById(R.id.PrepareItemAddition);

        IdI = (TextView) findViewById(R.id.ID);
        NameI = (TextView) findViewById(R.id.Name);
        ChargesI = (TextView) findViewById(R.id.Charges);
        TempI = (TextView) findViewById(R.id.Temperature);

        AddIngredient = (ImageButton) findViewById(R.id.AddIngredient);
        MainL = (TableLayout) findViewById(R.id.AddedTable);
        TextView AddTemp = (TextView) findViewById(R.id.AddIngredientText);
        IngAmout = (TextView) findViewById(R.id.IngredientQuantity);
        IngName = (TextView) findViewById(R.id.IngredientNameText);
        TopId = AddTemp.getId();

        MenuDB = FirebaseFirestore.getInstance();

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        Prep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DocumentReference Item_Count = MenuDB.collection(ServiceProviderName).document("2NzBBFVRpromSOj5DRBn");

                Item_Count.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isComplete()) {
                            DocumentSnapshot Doc = task.getResult();
                            StringBuilder data = new StringBuilder("");
                            Cnt = Integer.parseInt(Doc.getString("Count"));
                            Toast.makeText(AddItemToMenu.this, "Processing..." + Integer.toString(Cnt), Toast.LENGTH_LONG).show();
                            int k = Prep.getVisibility();
                            int l = Save.getVisibility();
                            Prep.setVisibility(l);
                            Save.setVisibility(k);
                        }
                    }
                });

            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String TempIngArr = "";
                    for (int i = 0; i < MainL.getChildCount(); i++) {
                        TextView Textview1 = (TextView) findViewById(i + 4201);
                        String TempName = Textview1.getText().toString();
                        TempIngArr += TempName;
                    }

                    Map<String, Object> newItem = new HashMap<>();
                    newItem.put("ItemId", IdI.getText().toString());
                    newItem.put("ItemName", NameI.getText().toString());
                    newItem.put("Charges", ChargesI.getText().toString());
                    newItem.put("Temp", TempI.getText().toString());
                    newItem.put("Ingredients", TempIngArr);


                    ItemCountInc.put("Count", Integer.toString(Cnt + 1));
                    //ItemCountInc;
                    MenuDB.collection(ServiceProviderName).document(Integer.toString(Cnt + 1))
                            .set(newItem)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    MenuDB.collection(ServiceProviderName).document("2NzBBFVRpromSOj5DRBn")
                                            .set(ItemCountInc);
                                    Toast.makeText(AddItemToMenu.this, "Saved !", Toast.LENGTH_LONG).show();
                                    int k = Prep.getVisibility();
                                    Save.setVisibility(k);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AddItemToMenu.this, "Failed! Try Again", Toast.LENGTH_LONG).show();
                                }
                            });


            }
        });







        AddIngredient.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {




                TableRow R1 = new TableRow(AddItemToMenu.this);
                LinearLayout L1 = new LinearLayout(AddItemToMenu.this);
                L1.setBackgroundResource(R.drawable.addingredientbox);
                //LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                L1.setOrientation(LinearLayout.HORIZONTAL);

                /*RelativeLayout.LayoutParams prams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                prams.addRule(RelativeLayout.BELOW, TopId);
                prams.addRule(RelativeLayout.ABOVE, R.id.IngredientToFill);

                L1.setId(View.generateViewId());
                TopId = L1.getId();
                */

                //L1.setWeightSum(5);

                RelativeLayout.LayoutParams T1params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                T1params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                RelativeLayout.LayoutParams B1params = new RelativeLayout.LayoutParams(41,40);
                B1params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);


                TextView T1,T2,T3;
                final ImageButton B1;
                FrameLayout F1;
                T1 = new TextView(AddItemToMenu.this);
                T1.setTextSize(15);
                T2 = new TextView(AddItemToMenu.this);
                T2.setTextSize(15);
                T3 = new TextView(AddItemToMenu.this);
                B1 = new ImageButton(AddItemToMenu.this);
                F1 = new FrameLayout(AddItemToMenu.this);

                B1.setBackgroundResource(R.drawable.circle_shade_red);
                //B1.setLayoutParams(B1params);
                T1.setText("-  "+IngName.getText().toString().toUpperCase());

                T2.setText(" ");
                T3.setText("  x");
                T3.setTextColor(Color.BLACK);

                T1.setGravity(View.TEXT_ALIGNMENT_CENTER);
                //B1.setGravity(Gravity.TOP);
                T1.setPadding(60,0,0,0);
                T1.setId(++TextIdCount+4200);
                B1.setId(++ButtonIdCount+4000);
                B1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getParent();
                        View tempV = (View) v;
                        int id = tempV.getId();
                        MainL.removeViewAt(id-4000-1);
                        for(int i=id+1,j=id-4000;j<=MainL.getChildCount();j++,i++){

                            //AllergiesText.setText(Integer.toString(AddedTableAllergies.getChildCount()));
                            View TempBut = (View) findViewById(i);
                            View TempTxt = (View) findViewById(i+200);
                            TempBut.setId(i-1);
                            TempTxt.setId(i+199);
                        }
                        ButtonIdCount--;
                        TextIdCount--;
                    }
                });
                //B1.setImageResource(android.R.drawable.ic_delete);
                //B1.setText("x");
                //B1.setTextColor(Color.RED);

                L1.addView(T1);
                F1.addView(B1);
                F1.addView(T3);

                F1.setLayoutParams(B1params);
                L1.addView(F1);

                R1.addView(L1);
                R1.addView(T2);

                /*L1.addView( T1);
                L1.addView( T2);
                //L1.addView( B1);

                R1.addView(L1);*/
                MainL.addView(R1);

                IngAmout.setText(null);
                IngName.setText(null);

            }
        });

    }
}