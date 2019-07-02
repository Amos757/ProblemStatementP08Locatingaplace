package com.example.problemstatementp08_locatingaplace;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.widget.Toast.LENGTH_LONG;


public class MainActivity extends AppCompatActivity {
    LatLng NorthHQ,Central,East;
    Marker ep,cp,NHQ;
    Button btn1, btn2, btn3;
    public GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);


                //current location button
                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }


                //north
                NorthHQ = new LatLng(1.424450, 103.829849);
                //custom marker
                NHQ = map.addMarker(new
                        MarkerOptions()
                        .position(NorthHQ)
                        .title("HQ - North")
                        .snippet("Block 333, Admiralty Ave 3, 765654 \nOperating hours: 10am-5pm\n Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.national_day)));

                //central
                Central = new LatLng(1.297520, 103.846320);
                //red marker
                cp = map.addMarker(new
                        MarkerOptions()
                        .position(Central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \nOperating hours: 11am-8pm \nTel:67788652")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                //east
                East = new LatLng(1.349860, 103.934190);
                //red marker
                 ep = map.addMarker(new
                        MarkerOptions()
                        .position(East)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \nOperating hours: 9am-5pm\n Tel:66776677\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            }


        });

//        //Button
//        btn1 = findViewById(R.id.btn1);
//        btn2 = findViewById(R.id.btn2);
//        btn3 = findViewById(R.id.btn3);
//
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                map.moveCamera(CameraUpdateFactory.newLatLngZoom(NorthHQ,15));
//                Toast.makeText(MainActivity.this, NHQ.getTitle()+ " area", Toast.LENGTH_LONG).show();
//
//            }
//        });
//
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                map.moveCamera(CameraUpdateFactory.newLatLngZoom(Central,15));
//                Toast.makeText(MainActivity.this, cp.getTitle()+ " area", Toast.LENGTH_LONG).show();
//
//            }
//        });
//
//        btn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                map.moveCamera(CameraUpdateFactory.newLatLngZoom(East,15));
//                Toast.makeText(MainActivity.this, ep.getTitle() + " area", Toast.LENGTH_LONG).show();
//
//            }
//        });


        //Spinner
        Spinner spinLocate;
        spinLocate= (Spinner) findViewById(R.id.spinLocation);//fetch the spinner from layout file
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.array_location));//setting the country_array to spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinLocate.setAdapter(adapter);

        //if you want to set any action you can do in this listener
        spinLocate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

                switch(position){
                    case 0:
                        //North
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(NorthHQ,15));
                        Toast.makeText(MainActivity.this, NHQ.getTitle()+ " area", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        //Central
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Central,15));
                        Toast.makeText(MainActivity.this, cp.getTitle()+ " area", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        //East
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(East,15));
                        Toast.makeText(MainActivity.this, ep.getTitle() + " area", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

    }
}