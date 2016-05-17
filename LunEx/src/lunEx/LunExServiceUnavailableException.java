package lunEx;
@SuppressWarnings("serial")
public class LunExServiceUnavailableException extends RuntimeException {
	public String getMessage() {
		return "Sorry, sunspot activity today...please try again later";
	}
}
