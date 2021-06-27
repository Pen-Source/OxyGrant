package com.pensource.oxygrant.util

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.core.app.ActivityCompat

/**
 * Get location updates and loading status
 */
class LocationHelper(
    private val context: Context,
    private val locationManager: LocationManager,
    private val singleLocationUpdate: Boolean = false,
    private val minTimeMs: Long = 10000,
    private val minDistance: Float = 0F,
    private val listener: Listener
) : LocationListener {

    interface Listener {
        fun requirePermission()
        fun onMockLocation()
        fun onNewLocation(location: Location)
        fun onLoadingStateChange(state: LoadingState)
        fun onGpsStateChange(enabled: Boolean)
    }

    enum class LoadingState {
        LOADING, GOT_LOCATION, MOCKED, STOPPED
    }

    /**
     * Starts location updates
     */
    fun start() {
        listener.onLoadingStateChange(LoadingState.LOADING)
        getLocation()
    }

    /**
     * Stops location updates
     * Call when activity no longer need location updates
     */
    fun stop() {
        listener.onLoadingStateChange(LoadingState.STOPPED)
        locationManager.removeUpdates(this)
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (!hasPermission()) {
            listener.requirePermission()
            return
        }

        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER, minTimeMs, minDistance, this
        )
    }

    private fun hasPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    }

    override fun onLocationChanged(location: Location) {
        if (location.isFromMockProvider) {
            listener.onLoadingStateChange(LoadingState.MOCKED)
            listener.onMockLocation()
        } else {
            listener.onNewLocation(location)
            listener.onLoadingStateChange(LoadingState.GOT_LOCATION)

            if (singleLocationUpdate) stop()
        }
    }

    override fun onProviderEnabled(provider: String) {
        super.onProviderEnabled(provider)
        if (provider == LocationManager.GPS_PROVIDER) {
            listener.onGpsStateChange(true)
        }
    }

    override fun onProviderDisabled(provider: String) {
        super.onProviderDisabled(provider)
        if (provider == LocationManager.GPS_PROVIDER) {
            listener.onGpsStateChange(false)
        }
    }
}