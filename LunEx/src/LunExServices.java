/**
 * UNTOUCHABLE!  You cannot change this file.
 *
 */
import java.util.Random;

public class LunExServices implements ITC.SecurityExchangeTransmissionInterface {
	private static Random invisibleHand = new Random();

	public double currentPrice(String symbol) {
		pauseToEmulateSendReceive();
		if (invisibleHand.nextInt(100) > 80)
			throw new LunExServiceUnavailableException();
		double currentPrice = 42.0 + (invisibleHand.nextDouble() * 2.1);
		return truncate(currentPrice);
	}

	private void pauseToEmulateSendReceive() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// lucky client!
		}
	}

	private double truncate(double original) {
		long y = (long) (original * 10000);
		return (double) y / 10000;
	}
}
