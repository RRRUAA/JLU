package javaee.restws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/Weather")   
public class TravelWS {

	@GET
	@Produces("text/plain; charset=UTF-8")
	public String getWeather1() {
		return "大太阳";
	}
	
	
}
