package com.org.assignment.GoogleDirections;

import java.util.ArrayList;
import java.util.List;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.LatLng;

public class Utils {
	
	/**
	 * Find the point for the distance
	 * @param x1  lattitude for point1
	 * @param x2  lattitude for point2
	 * @param D   Distance between point 1 and point 2
	 * @param d   distance to find the point from point1.
	 * @return    double
	 */
	public static double findPoint(double x1, double x2, double D, double d) {
		double result = (x2-x1) *(d/D);
		result += x1;
		return result;
	}
	
	/**
	 * Uses Haversines formula to find the distance between two points
	 * @param lat1    lattitude of point1
	 * @param lat2    lattitude of point2
	 * @param lon1    longitude of point1
	 * @param lon2    longitude of point2
	 * @return double distance between the points
	 */
	public static double distance(double lat1, double lat2, double lon1, double lon2) 
	{ 
		lon1 = Math.toRadians(lon1); 
		lon2 = Math.toRadians(lon2); 
		lat1 = Math.toRadians(lat1); 
		lat2 = Math.toRadians(lat2); 
		
		//Haversine formula  
		double dlon = lon2 - lon1;  
		double dlat = lat2 - lat1; 
		double a = Math.pow(Math.sin(dlat / 2), 2) 
		        + Math.cos(lat1) * Math.cos(lat2) 
		        * Math.pow(Math.sin(dlon / 2),2); 
		     
		double c = 2 * Math.asin(Math.sqrt(a)); 
		
		// Radius of earth in kilometers. Use 3956  
		// for miles 
		double r = 6371; 
		
		// calculate the result 
		return(c * r); 
	}  
	
	/**
	 * Find lattitude and longitude of point along the line with (lat1, long1) and (lat2, long2).
	 * @param lat1            lattitude of point1
	 * @param lat2            lattitude of point2
	 * @param long1           longitude of point1
	 * @param long2           longitude of point2
	 * @param d               distance for which lattitude and longitude should be find.
	 * @param totalDistance   Distance between (lat1, long1) and (lat2, long2).
	 * @return LatLng  lattitude and longitude of point.
	 */
	public static LatLng findXAndY(double lat1, double lat2, double long1, double long2, double d, double totalDistance) {
		double x = findPoint(lat1, lat2, totalDistance, d);
		double y = findPoint(long1, long2, totalDistance, d);
		return new LatLng(x, y);
	}
	
	/**
	 * 
	 * @param lat1            lattitude of point1
	 * @param lat2            lattitude of point2
	 * @param long1           longitude of point1
	 * @param long2           longitude of point2
	 * @param eqDistInMeter   distance of equidistanct points
	 * @return List<LatLng>   list of equidistant points 
	 * @throws Exception
	 */
	public static List<LatLng> getRouteForLatLangs(double lat1, double lat2, double long1, double long2, int eqDistInMeter) throws Exception {
		List<LatLng> output = new ArrayList<LatLng>();
		GeoApiContext context = new GeoApiContext.Builder().apiKey(Constants.API_KEY).build();
		String origin = lat1 + "," + long1;
		String destination = lat2 + "," + long2;
		DirectionsResult out = DirectionsApi.newRequest(context).origin(origin).destination(destination).await();
		DirectionsRoute[] routes = out.routes;
		if(routes != null && routes.length > 0) {
			DirectionsLeg[] legs = routes[0].legs;
			DirectionsStep[] steps = legs[0].steps;
			double currentLat = lat1, currentLong = long1, distToFind = eqDistInMeter;
			output.add(new LatLng(lat1, long1));
			for(int i = 0; i < steps.length; i++) {
				List<LatLng> latLngs = steps[i].polyline.decodePath();
				int len = latLngs.size();
				if(steps[i].distance.inMeters < distToFind) {
					distToFind -= steps[i].distance.inMeters;
					currentLat = steps[i].endLocation.lat;
					currentLong = steps[i].endLocation.lng;
					continue;
				}
				
				for(int j = 0; j < len; j++) {
					double x = latLngs.get(j).lat;
					double y = latLngs.get(j).lng;
					double dist = distance(currentLat, x, currentLong, y) * 1000;
					if(distToFind == dist) {
						output.add(latLngs.get(j));
						currentLat = x;
						currentLong = y;
						distToFind = eqDistInMeter;
						
					} else if(distToFind < dist) {
						LatLng point = findXAndY(currentLat, x, currentLong, y, distToFind, dist);
						output.add(point);
						currentLat = point.lat;
						currentLong = point.lng;
						distToFind = eqDistInMeter;
						
					} else {
						distToFind -= dist;
						currentLat = x;
						currentLong = y;
					}
					
				}
			}
		}
		
		return output;
	}
	
	public static void printPoints(List<LatLng> points) {
		int len = points.size();
		for(int i = 0; i < len; i++) {
			System.out.println(points.get(i).lat + "," + points.get(i).lng);
		}	
	}

}
