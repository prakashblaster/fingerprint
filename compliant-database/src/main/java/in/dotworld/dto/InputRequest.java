package in.dotworld.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InputRequest<T> {
	
	private String timeZone;
	private T compliant;

	public InputRequest() {

	}

	public InputRequest( String timeZone, T compliant) {
		super();
		this.timeZone = timeZone;
		this.compliant = compliant;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public T getCompliant() {
		return compliant;
	}

	public void setCompliant(T compliant) {
		this.compliant = compliant;
	}

}
