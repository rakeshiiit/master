package com.org.assignment.GoogleDirections;

import java.util.List;
import com.google.maps.model.LatLng;

public class GoogleDirections {

	public static void main(String[] args) {
		try {
			 List<LatLng> equiDistantPoints = Utils.getRouteForLatLangs(12.94523, 12.95944, 77.61896, 77.66085, 50);
			 Utils.printPoints(equiDistantPoints);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
