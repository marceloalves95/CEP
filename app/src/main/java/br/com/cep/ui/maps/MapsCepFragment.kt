package br.com.cep.ui.maps

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import br.com.cep.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsCepFragment : Fragment() {

    private val args by navArgs<MapsCepFragmentArgs>()

    private val callback = OnMapReadyCallback { googleMap ->

        val myplace = LatLng(args.latitude.toDouble(), args.longitude.toDouble())
        googleMap.uiSettings.isZoomControlsEnabled = true

        googleMap.addMarker(MarkerOptions().position(myplace).title("Em casa"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myplace,12.0f))
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_maps_cep, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }



}