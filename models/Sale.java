//Sale class - Same as Vehicle with different variables
package models; 

/**
 * The Class Sale.
 */
public class Sale {
	
	/** The vehicle id. */
	private int vehicle_id;
	
	/** The sold price. */
	private int sold_price;
	
	/** The sold date. */
	private String sold_date;
	
	/** The status. */
	private String status;

	/**
	 * Instantiates a new sale.
	 *
	 * @param vehicle_id the vehicle id
	 * @param sold_date the sold date
	 * @param sold_price the sold price
	 * @param status the status
	 */
	public Sale(int vehicle_id, String sold_date, int sold_price,  String status)
	{
		this.vehicle_id = vehicle_id;
		this.sold_price = sold_price;
		this.sold_date = sold_date;
		this.status = status;
	}
	
	/**
	 * Gets the vehicle id.
	 *
	 * @return the vehicle id
	 */
	public int getVehicle_id() {
		return vehicle_id;
	}

	/**
	 * Sets the vehicle id.
	 *
	 * @param vehicle_id the new vehicle id
	 */
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	/**
	 * Gets the sold price.
	 *
	 * @return the sold price
	 */
	public int getSold_price() {
		return sold_price;
	}

	/**
	 * Sets the sold price.
	 *
	 * @param sold_price the new sold price
	 */
	public void setSold_price(int sold_price) {
		this.sold_price = sold_price;
	}


	/**
	 * Gets the sold date.
	 *
	 * @return the sold date
	 */
	public String getSold_date() {
		return sold_date;
	}

	/**
	 * Sets the sold date.
	 *
	 * @param sold_date the new sold date
	 */
	public void setSold_date(String sold_date) {
		this.sold_date = sold_date;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sale [vehicle_id=" + vehicle_id + ", sold_price=" + sold_price + ", sold_date=" + sold_date
				+ ", status=" + status + "]";
	}

}
