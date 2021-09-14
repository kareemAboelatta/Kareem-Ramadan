package com.example.kareemramadan.forsalatimes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.TextView;

import com.example.kareemramadan.R;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SalatTimesActivity extends AppCompatActivity implements LocationListener{



    TextView tvCity,tvAddress,tv_fajr,tv_Sunrise,tv_Dhuhr,tv_Asr
            ,tv_Sunset,tv_Maghrib,tv_Isha ,upcoming,upcoming_time
            ,current;



    LocationManager locationManager;
    String city="Cairo",addressLine;
    Retrofit retrofit;
    ApiInterface apiInterface;
    String date;


    ProgressDialog progressDialog ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salat_times);


        grantPermission();
        tvCity=findViewById(R.id.city);
        tvAddress=findViewById(R.id.fullAdress);
        tv_fajr=findViewById(R.id.tv_fajr);
        tv_Sunrise=findViewById(R.id.tv_Sunrise);
        tv_Dhuhr=findViewById(R.id.tv_Dhuhr);
        tv_Asr=findViewById(R.id.tv_Asr);
        tv_Sunset=findViewById(R.id.tv_Sunset);
        tv_Maghrib=findViewById(R.id.tv_Maghrib);
        tv_Isha=findViewById(R.id.tv_Isha);
        upcoming=findViewById(R.id.upcoming);
        upcoming_time=findViewById(R.id.upcoming_time);
        current=findViewById(R.id.current);


        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("getting your location ....");
        progressDialog.show();


        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String str = sdf.format(new Date());


        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.aladhan.com/timingsByAddress/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface =retrofit.create(ApiInterface.class);


        // get date  because the Api want it to get prayers time in this day..
        Date cc = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        date = df.format(cc);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);




        locationEnabled();
        getLocation();


    }
    private void grantPermission(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
    }
    private void locationEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!gps_enabled && !network_enabled) {
            new AlertDialog.Builder(SalatTimesActivity.this)
                    .setTitle("Enable GPS Service")
                    .setMessage("We need your GPS location to show Near Places around you.")
                    .setCancelable(false)
                    .setPositiveButton("Enable", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                }
                            })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }
    private void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 5, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        progressDialog.dismiss();
        progressDialog.setMessage("Now getting prayer times according to your location..  ");
        progressDialog.show();
        try {
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.ENGLISH);
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            city=addresses.get(0).getAdminArea();
            addressLine=addresses.get(0).getAddressLine(0);
            int index= city.lastIndexOf(" ");
            city=city.substring(0, index);
            tvCity.setText(city);
            tvAddress.setText(addressLine);



            Call<Post> call =apiInterface.getPost(date,city);
            call.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                 //   textView.setText("getMaghrib :"+response.body().getData().getTimings().getMaghrib());
                    tv_Sunrise.setText(""+response.body().getData().getTimings().getSunrise());
                    tv_Sunset.setText(""+response.body().getData().getTimings().getSunset());

                    Date cc =  Calendar.getInstance().getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    String mydate = sdf.format(cc);



                    String fajr=response.body().getData().getTimings().getFajr();
                    String duhr=response.body().getData().getTimings().getDhuhr();
                    String asr=response.body().getData().getTimings().getAsr();
                    String maghrib=response.body().getData().getTimings().getMaghrib();
                    String isha=response.body().getData().getTimings().getIsha();
                    tv_fajr.setText(fajr);
                    tv_Dhuhr.setText(duhr);
                    tv_Asr.setText(asr);
                    tv_Maghrib.setText(maghrib);
                    tv_Isha.setText(isha);
                    Date currentTime = null;
                    Date fajrTime = null;
                    Date duhrTime = null;
                    Date asrTime = null;
                    Date maghribTime = null;
                    Date ishaTime = null;


                    try {
                        currentTime = sdf.parse(mydate);
                        fajrTime = sdf.parse(fajr);
                        duhrTime = sdf.parse(duhr);
                        asrTime = sdf.parse(asr);
                        maghribTime = sdf.parse(maghrib);
                        ishaTime = sdf.parse(isha);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    String nextPrayer="";
                    String nextPrayerTime="";
                    String currentPrayer="";
                    String currentPrayerTime="";

                    if (isSmaller(currentTime, fajrTime)) {
                        //next
                        nextPrayer=getString(R.string.fajr);
                        nextPrayerTime=fajr;
                        //current
                        currentPrayer=getString(R.string.isha);
                        currentPrayerTime=isha;
                    }else if (isGreaterThanOrEqual(currentTime, fajrTime) && isSmaller(currentTime, duhrTime)) {
                        //next
                        nextPrayer=getString(R.string.dhuhr);
                        nextPrayerTime=duhr;
                        //current
                        currentPrayer=getString(R.string.fajr);
                        currentPrayerTime=fajr;
                    }else if (isGreaterThanOrEqual(currentTime, duhrTime) && isSmaller(currentTime, asrTime)) {
                        //next
                        nextPrayer=getString(R.string.asr);
                        nextPrayerTime=asr;
                        //current
                        currentPrayer=getString(R.string.dhuhr);
                        currentPrayerTime=duhr;
                    }else if (isGreaterThanOrEqual(currentTime, asrTime) && isSmaller(currentTime, maghribTime)) {
                        //next
                        nextPrayer=getString(R.string.maghrib);
                        nextPrayerTime=maghrib;
                        //current
                        currentPrayer=getString(R.string.asr);
                        currentPrayerTime=asr;
                    }else if (isGreaterThanOrEqual(currentTime, maghribTime) && isSmaller(currentTime, ishaTime)) {
                        //next
                        nextPrayer=getString(R.string.isha);
                        nextPrayerTime=isha;
                        //current
                        currentPrayer=getString(R.string.maghrib);
                        currentPrayerTime=maghrib;
                    }

                    current.setText(currentPrayer);
                    upcoming.setText(nextPrayer);
                    upcoming_time.setText(nextPrayerTime);



                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                        }
                    },2000);


                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {

                }
            });


        } catch (Exception e) {
        }
    }
    public  boolean isGreaterThanOrEqual(Date d1,Date d2) {
        if (d2.compareTo(d1) ==0 || d2.compareTo(d1) ==-1 ) {
            return true;
        }else
            return false;
    }

    public  boolean isSmaller(Date d1,Date d2) {
        if (d1.compareTo(d2) == -1 ) {
            return true;
        }else
            return false;
    }
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}