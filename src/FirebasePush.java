import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.json.JSONException;
import org.json.JSONObject;

public class FirebasePush {

	private static String SERVER_KEY = "Your Server key";
	private static String DEVICE_TOKEN = "Your device token";

	/**
	 * USE THIS METHOD to send push notification
	 */
	public static void main(String[] args) throws Exception {
		String title = "Notification title";
		String message = "This is your Notification message";
		sendPushNotification(title, message);
	}

	/**
	 * Sends notification to mobile, YOU DON'T NEED TO UNDERSTAND THIS METHOD
	 */
	private static void sendPushNotification(String title, String message) throws Exception {

		String pushMessage = generateString(title, message);

		URL url = new URL("https://fcm.googleapis.com/fcm/send");
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		});
		conn.setRequestProperty("Authorization", "key=" + SERVER_KEY);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);

		OutputStream outputStream = conn.getOutputStream();
		outputStream.write(pushMessage.getBytes());

		System.out.println(conn.getResponseCode());
		System.out.println(conn.getResponseMessage());
	}

	private static String generateString(String title, String messageTxt) throws JSONException {

		JSONObject body = new JSONObject();
		body.put("to", DEVICE_TOKEN);
		body.put("priority", "high");

		JSONObject notification = new JSONObject();
		notification.put("title", title);
		notification.put("body", messageTxt);
		notification.put("sound", "default");

		JSONObject data = new JSONObject();
		data.put("extra-data-1", "123456");
		data.put("extra-data-2", "123456");

		body.put("notification", notification);
		body.put("data", data);

		System.out.println(body.toString());
		return body.toString();
	}
}
