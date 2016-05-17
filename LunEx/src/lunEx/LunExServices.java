package lunEx;
/**
 * UNTOUCHABLE!  You cannot change this file.
 *
 */

import java.util.Random;

public class LunExServices implements ITC.SecurityExchangeTransmissionInterface {
	private static Random invisibleHand = new Random();

	public int currentPrice(String symbol) {
		pauseToEmulateSendReceive();
		if (invisibleHand.nextInt(100) > 80)
			throw new LunExServiceUnavailableException();
		return 103 + invisibleHand.nextInt(20);
	}

	private void pauseToEmulateSendReceive() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// warp speed!
		}
	}
}
