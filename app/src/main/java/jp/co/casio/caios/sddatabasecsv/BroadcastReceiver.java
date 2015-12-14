package jp.co.casio.caios.sddatabasecsv;

import android.content.Context;
import android.content.Intent;


/**
 * Broadcast receiver for Z.
 */
public class BroadcastReceiver extends android.content.BroadcastReceiver {

	private final static String ACTION_ReginfoZ = "ReginfoZ";
	public final static String EXTRA_BIZDATE  = "BIZDATE";
	public final static String EXTRA_ZCOUNTER = "ZCOUNTER";

	@Override
	public void onReceive(Context context, Intent intent) {
		
		if (intent.getAction().equals(ACTION_ReginfoZ)) {
			// Set parameter to access database.
			String bizDate = intent.getStringExtra(EXTRA_BIZDATE);
			String zcounter = intent.getStringExtra(EXTRA_ZCOUNTER);
			
			// Start database access service.
			Intent sendIntent = new Intent(context, DatabaseCsvService.class);
			sendIntent.putExtra(EXTRA_BIZDATE, bizDate);
			sendIntent.putExtra(EXTRA_ZCOUNTER, zcounter);
			context.startService(sendIntent);
		}
	}
}
