package bhardwaj.keshav.bonappetite;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by keshav on 10/1/18.
 */

public class BonAppetite extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
