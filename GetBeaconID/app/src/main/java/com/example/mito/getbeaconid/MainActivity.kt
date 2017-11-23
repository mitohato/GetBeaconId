package com.example.mito.getbeaconid

import android.os.Bundle
import android.os.RemoteException
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import butterknife.bindView
import org.altbeacon.beacon.BeaconConsumer
import org.altbeacon.beacon.BeaconManager
import org.altbeacon.beacon.BeaconParser
import org.altbeacon.beacon.Region



class MainActivity : AppCompatActivity() , BeaconConsumer{
    val btn: Button by bindView(R.id.button)
    val TAG = "AltBeacon Sample"
    var beaconManager : BeaconManager? = null
    val IBEACON_FORMAT = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"
    var flag = true

    override fun onBeaconServiceConnect() {

//        beaconManager!!.addMonitorNotifier(object : MonitorNotifier{
//            override fun didDetermineStateForRegion(p0: Int, p1: Region?) {
//                Log.d(TAG, "Deter")
//            }
//
//            override fun didEnterRegion(p0: Region?) {
//                Log.d(TAG, p0.toString())
//                Log.d(TAG, "Enter")
//            }
//
//            override fun didExitRegion(p0: Region?) {
//                Log.d(TAG, "Exit")
//            }
//        })

        beaconManager!!.addRangeNotifier { beacons, region ->
            for (beacon in beacons){
                Log.d(TAG, "uuid:${beacon.id1}, major:${beacon.id2}, minor:${beacon.id3}" +
                        ", distance:${beacon.distance}, name:${beacon.bluetoothName}")
            }
        }

        try {
            Log.d("hoge", "hoge")
            beaconManager!!.startRangingBeaconsInRegion(Region("iBeacon", null, null, null))
//            beaconManager!!.startMonitoringBeaconsInRegion(Region("iBeacon", null, null, null))
        }catch (e: RemoteException){
            e.printStackTrace()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        beaconManager = BeaconManager.getInstanceForApplication(this)
        beaconManager!!.beaconParsers.add(BeaconParser().setBeaconLayout(IBEACON_FORMAT))

        btn.setOnClickListener {
            if(flag)
                beaconManager!!.bind(this)
            else
                beaconManager!!.unbind(this)
            flag = !flag
        }
    }

    override fun onStop() {
        super.onStop()
        beaconManager!!.unbind(this)
    }
}