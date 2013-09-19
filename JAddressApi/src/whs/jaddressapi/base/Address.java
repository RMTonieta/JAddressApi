package whs.jaddressapi.base;

/**
 * @author WilliamStenico 17/09/2013
 * 
 */
public class Address {

	private String zipCode;
	private String typeOfStreet;
	private String street;
	private String neighborhood;
	private String city;
	private String estate;

	public Address(String zipCode, String typeOfStreet, String street,
			String neighborhood, String city, String estate) {
		// TODO:IEquatable to tests...

		setZipCode(zipCode);
		setTypeOfStreet(typeOfStreet);
		setStreet(street);
		setNeighborhood(neighborhood);
		setCity(city);
		setEstate(estate);
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTypeOfStreet() {
		return typeOfStreet;
	}

	public void setTypeOfStreet(String typeOfStreet) {
		this.typeOfStreet = typeOfStreet;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEstate() {
		return estate;
	}

	public void setEstate(String estate) {
		this.estate = estate;
	}

}
