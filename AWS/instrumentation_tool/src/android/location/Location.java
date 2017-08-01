package android.location;

public class Location {

	private double mLatitude = 0.0;
    private double mLongitude = 0.0;
    private String mProvider;

    public Location(String provider) {
    	mProvider = provider;
    }

	public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

	public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

}
