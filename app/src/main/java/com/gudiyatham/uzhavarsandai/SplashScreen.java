package com.gudiyatham.uzhavarsandai;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;


import com.google.gson.JsonObject;
import com.gudiyatham.uzhavarsandai.utils.NetWorkUtils;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    private static final String TAG = "SplashScreen";
    private static int SPLASH_TIME_OUT = 3000;
    private AlertDialog alertDialog;
    private NetWorkUtils netWorkUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        session = new SessionManager(getApplicationContext());
//        apiService = ApiClient.getClient().create(APIService.class);
        netWorkUtils = new NetWorkUtils(this);
//        if (session.isLoggedIn()) {
//            sendRegistrationToServer();
//            userDetailCrashlytics();
//        }
        if (!netWorkUtils.isNetworkAvailable()) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Check Internet Connectivity", Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SPLASH_TIME_OUT = 100;
                    launchNextScreen();
                }
            });
            snackbar.show();
            return;
        }
//        apiService.getAppStatus().enqueue(new Callback<JsonObject>() {
//            @Override
//            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                try {
//                    if (response.isSuccessful()) {
//                        JsonObject json = response.body();
//                        String updateNotice = json.get("update_notice_text").getAsString();
//                        int minVersion = json.get("min_version_code").getAsInt();
//                        int currentVersion = json.get("current_version_code").getAsInt();
//                        PackageInfo pInfo = SplashScreen.this.getPackageManager().getPackageInfo(getPackageName(), 0);
//                        String version = pInfo.versionName;
//                        int verCode = pInfo.versionCode;
//                        checkAppVersion(verCode, minVersion, currentVersion, updateNotice);
//
//                    } else {
//                        SPLASH_TIME_OUT = 300;
//                        launchNextScreen();
//                    }
//
//                } catch (Exception e) {
//                    SPLASH_TIME_OUT = 3000;
//                    launchNextScreen();
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<JsonObject> call, Throwable t) {
//                SPLASH_TIME_OUT = 1000;
//                launchNextScreen();
//            }
//        });

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "MyFirebaseMsgService Key: " + key + " Value: " + value);
            }
        }


    }

    private void launchNextScreen() {
        new Handler().postDelayed(new Runnable() {

			/*
             * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

            @Override
            public void run() {

                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();

//                if (session.isLoggedIn()) {
//                    sendRegistrationToServer();
//                    userDetailCrashlytics();
//                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
//                    startActivity(i);
//                    finish();
//                } else {
//                    Intent i = new Intent(SplashScreen.this, SigninActivity.class);
//                    startActivity(i);
//                    finish();
//
//                }
            }
        }, SPLASH_TIME_OUT);
    }

//    private void sendRegistrationToServer() {
//
//        Boolean isFcmTokenSent = session.getPropertyBoolean(SessionManager.IS_FCM_TOKEN_SENT);
//        String fcmToken = session.getPropertyString(SessionManager.FCM_TOKEN);
//        if (!isFcmTokenSent && !TextUtils.isEmpty(fcmToken)) {
//            String email = session.getPropertyString(SessionManager.KEY_EMAIL);
//            Call<MSG> callback = apiService.sentFcmToken(email, fcmToken);
//            callback.enqueue(new Callback<MSG>() {
//                @Override
//                public void onResponse(Call<MSG> call, Response<MSG> response) {
//                    Log.i(TAG, "onResponse: " + response.body().getSuccess());
//                    if (response.body().getSuccess() == 1) {
//                        session.setPropertyBoolean(SessionManager.IS_FCM_TOKEN_SENT, true);
//
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<MSG> call, Throwable t) {
//                    Log.i(TAG, "onFailure: ");
//                }
//            });
//
//
//        }
//    }

//    private void userDetailCrashlytics() {
//        String email = session.getPropertyString(SessionManager.KEY_EMAIL);
//        Crashlytics.setUserIdentifier(email);
//    }

    private void checkAppVersion(int verCode, int minVersion, int currentVersion, String updateNotice) {
        if (verCode <= minVersion) {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(SplashScreen.this, android.R.style.Theme_Material_Light_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(SplashScreen.this);
            }
            builder.setTitle("Update")
                    .setMessage(updateNotice)
                    .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            final String appPackageName = getPackageName();
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                            } catch (android.content.ActivityNotFoundException anfe) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                            }
                            finish();
                        }
                    })
                    .setIcon(R.mipmap.ic_launcher)
                    .setCancelable(false)
                    .show();

        } else if (verCode < currentVersion) {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(SplashScreen.this, android.R.style.Theme_Material_Light_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(SplashScreen.this);
            }

            builder.setTitle("Update Available")
                    .setMessage(updateNotice)
                    .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                            } catch (android.content.ActivityNotFoundException anfe) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                            }
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SPLASH_TIME_OUT = 100;
                            launchNextScreen();
                            alertDialog.dismiss();
                        }
                    })
                    .setCancelable(false)
                    .setIcon(R.mipmap.ic_launcher);
            alertDialog = builder.show();

        } else {
            SPLASH_TIME_OUT = 300;
            launchNextScreen();
        }
    }

}
