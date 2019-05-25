package com.example.ubertest2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.uber.sdk.android.core.Deeplink;
import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.rides.RideParameters;
import com.uber.sdk.android.rides.RideRequestButton;
import com.uber.sdk.android.rides.RideRequestButtonCallback;
import com.uber.sdk.android.rides.RideRequestDeeplink;
import com.uber.sdk.core.client.ServerTokenSession;
import com.uber.sdk.core.client.SessionConfiguration;
import com.uber.sdk.rides.client.error.ApiError;

public class MainActivity extends AppCompatActivity implements RideRequestButtonCallback {
        private static final String DROPOFF_ADDR = "407台中市西屯區台灣大道四段938號號";
//    private static final String DROPOFF_ADDR = null;
    private static final Double DROPOFF_LAT = 24.1824359;
    private static final Double DROPOFF_LONG = 120.6156633;
        private static final String DROPOFF_NICK = "馬克創意";
//    private static final String DROPOFF_NICK = null;
        private static final String PICKUP_ADDR = "434台中市龍井區新興路17號";
//    private static final String PICKUP_ADDR = null;
    private static final Double PICKUP_LAT = 24.1807413;
    private static final Double PICKUP_LONG = 120.5884896;
        private static final String PICKUP_NICK = "東海四季百貨";
//    private static final String PICKUP_NICK = null;
    private static final int WIDGET_REQUEST_CODE = 1234;

    private SessionConfiguration configuration;
    private RideRequestButton blackButton;
    private Button testbutton;
private RideRequestDeeplink deeplink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configuration = new SessionConfiguration.Builder()
                // mandatory
                .setClientId("AwSVrn6_lxHb7sA5eKEeCACBA23ao9VC")
                // required for enhanced button features
                .setServerToken("UW_2u_4fMyqsK3fXip189TORSMyYbpDosfjirVzp")
                // required for implicit grant authentication
                .setRedirectUri("com.example.ubertest2://redirect")
                // optional: set sandbox as operating environment
                .build();

        ServerTokenSession session = new ServerTokenSession(configuration);

        RideParameters rideParametersForProduct = new RideParameters.Builder()
                .setPickupLocation(PICKUP_LAT, PICKUP_LONG, PICKUP_NICK, PICKUP_ADDR)
                .setDropoffLocation(DROPOFF_LAT, DROPOFF_LONG, DROPOFF_NICK, DROPOFF_ADDR)
                .build();

        deeplink = new RideRequestDeeplink.Builder(this)
                .setSessionConfiguration(configuration)
                .setFallback(Deeplink.Fallback.APP_INSTALL)
                .setRideParameters(rideParametersForProduct)
                .build();
//        deeplink.execute();



//        blackButton = (RideRequestButton) findViewById(R.id.uberbutton);
//        blackButton.setRideParameters(rideParametersForProduct);
//        blackButton.setSession(session);
//        blackButton.setDeeplinkFallback(Deeplink.Fallback.APP_INSTALL);
//        blackButton.loadRideInformation();



        UberSdk.initialize(configuration);


        testbutton = (Button) findViewById(R.id.testbutton);
        testbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, deeplink.getUri());
//               Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.uber.com/ul/?client_id=AwSVrn6_lxHb7sA5eKEeCACBA23ao9VC&action=setPickup&pickup[latitude]=24.1807413&pickup[longitude]=120.5884896&pickup[nickname]=東海四季百貨&pickup[formatted_address]=434台中市龍井區新興路17號&dropoff[latitude]=24.1824359&dropoff[longitude]=120.6156633&dropoff[nickname]=馬克創意&dropoff[formatted_address]=407台中市西屯區台灣大道四段938號號&product_id=a1111c8c-c720-46c3-8534-2fcdd730040d&link_text=View%20team%20roster&partner_deeplink=partner%3A%2F%2Fteam%2F9383"));

//                private static final String DROPOFF_ADDR = "407台中市西屯區台灣大道四段938號號";
//                private static final String DROPOFF_ADDR = null;
//                private static final Double DROPOFF_LAT = 24.1824359;
//                private static final Double DROPOFF_LONG = 120.6156633;
////    private static final String DROPOFF_NICK = "馬克創意";
//                private static final String DROPOFF_NICK = null;
////    private static final String PICKUP_ADDR = "434台中市龍井區新興路17號";
//                private static final String PICKUP_ADDR = null;
//                private static final Double PICKUP_LAT = 24.1807413;
//                private static final Double PICKUP_LONG = 120.5884896;
//    private static final String PICKUP_NICK = "東海四季百貨";
                startActivity(browserIntent);

            }
        });

    }


    @Override
    public void onRideInformationLoaded() {
        Toast.makeText(this, "Estimates have been refreshed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(ApiError apiError) {
        Toast.makeText(this, apiError.getClientErrors().get(0).getTitle(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(Throwable throwable) {
        Log.e("SampleActivity", "Error obtaining Metadata", throwable);
        Toast.makeText(this, "Connection error", Toast.LENGTH_LONG).show();
    }
}
