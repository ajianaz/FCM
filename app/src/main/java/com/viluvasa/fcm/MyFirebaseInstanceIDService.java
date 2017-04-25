package com.viluvasa.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by ASA on 12/14/2016.
 */

//Class extending FirebaseInstanceIdService
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";
    String refreshedToken;

    @Override
    public void onTokenRefresh() {

        //Getting registration token
        refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //Displaying token on logcat
        Log.e(TAG, "Refreshed token: " + refreshedToken);

    }

    public String getTokenID() {

        //Getting registration token
        refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //Displaying token on logcat
        //Log.e(TAG, "Refreshed token: " + refreshedToken);
        return refreshedToken;
    }

    private void sendRegistrationToServer(String token) {
        //You can implement this method to store the token on your server
        //Not required for current project
    }
}
