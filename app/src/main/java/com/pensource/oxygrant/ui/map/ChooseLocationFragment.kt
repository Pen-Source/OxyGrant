package com.pensource.oxygrant.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pensource.oxygrant.R
import com.pensource.oxygrant.databinding.FragmentChooseLocationBinding

class ChooseLocationFragment : Fragment() {

    private lateinit var binding: FragmentChooseLocationBinding

    private lateinit var locationManager: LocationManager

    private lateinit var googleMap: GoogleMap

    private val callback = OnMapReadyCallback {
        googleMap = it

        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .title("Marker in Sydney")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        binding.myLocationImageView.setOnClickListener {
            if (hasLocationPermission()) {
                if (isGpsEnabled()) {

                }
            }
        }
    }

    private fun hasLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    }

    private fun isGpsEnabled(): Boolean {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            locationManager.isLocationEnabled
        } else {
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation(
        locationManager: LocationManager,
        location: (loc: Location) -> Unit
    ) {
        loop@ locationManager.allProviders.forEach {
            val x = locationManager.getLastKnownLocation(it)

        }
    }
}