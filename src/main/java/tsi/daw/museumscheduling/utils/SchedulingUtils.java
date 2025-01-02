package tsi.daw.museumscheduling.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import tsi.daw.museumscheduling.model.Scheduling;

public class SchedulingUtils {

	public static String generateUniqueCode(Scheduling scheduling) {
	    LocalDateTime now = LocalDateTime.now();
	    String dataHora = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	    String uniqueString = dataHora + scheduling.getId() + scheduling.getHourlyReservation().getDate() + scheduling.getHourlyReservation().getTime();

	    try {
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hashBytes = digest.digest(uniqueString.getBytes());
	        StringBuilder hexString = new StringBuilder();

	        for (byte b : hashBytes)
	            hexString.append(String.format("%02x", b));

	        return hexString.toString().substring(0, 8);
	    } catch (NoSuchAlgorithmException e) {
	        return null;
	    }
	}
}

