package jp.co.casio.caios.sddatabasecsv;

import android.app.IntentService;
import android.content.Intent;


/**
 * Database csv convert service (background app)
 */
public class DatabaseCsvService extends IntentService {
	
	public DatabaseCsvService() {
		super("GetService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String bizDate  = intent.getStringExtra(BroadcastReceiver.EXTRA_BIZDATE);
		String zcounter = intent.getStringExtra(BroadcastReceiver.EXTRA_ZCOUNTER);
		
		
		// CSS012 FIXED TOTAL CONSOLIDATION
		convertCSS012(bizDate);

		// CSS013 TRANSACTION KEY CONSOLIDATION
		convertCSS013(bizDate);
		
		// CSS014 ITEM CONSOLIDATION
		convertCSS014(bizDate);

		// CSS015 DEPARTMENT CONSOLIDATION
		convertCSS015(bizDate);
		
		// CSS016 GROUP CONSOLIDATION
		convertCSS016(bizDate);
		
		// CSS017 CLERK CONSOLIDATION
		convertCSS017(bizDate);

		// CSS018 CUSTOMER GROUP CONSOLIDATION
		convertCSS018(bizDate);
		
		// CSS019 HOURLY CONSOLIDATION
		convertCSS019(bizDate);
		
		// CSS110 MIX & MATCH CONSOLIDATION
		convertCSS110(bizDate);
		
	}


	/**
	 * Convert Fixed Totalizer data to CSV
	 * @param bizDate

	 */
	public void convertCSS012( String bizDate){
		final String dbName	= "SUMMARY.DB";			// Target DB
		final String table	= "CSS012";				// Fixed Totalizer
		
		// Condition (BIZDATE='20130701' and ZCOUNTER='000001')
		String condition= "BIZDATE='"+bizDate+"'";
		// CSV file name (CSS012_20130701_000001.CSV)
		String csvName	= "Fixed_Totalizer"+"_"+bizDate+".CSV";
		
		ConvertDatabaseToCsv convert = new ConvertDatabaseToCsv();
		convert.convertDatabaseToCsv(dbName,table,condition,csvName);
	}

	/**
	 * Convert TRANSACTION KEY data to CSV
	 * @param bizDate

	 */
	public void convertCSS013( String bizDate){
		final String dbName	= "SUMMARY.DB";			// Target DB
		final String table	= "CSS013";				// TRANSACTION KEY

		String condition= "BIZDATE='"+bizDate+"'";
		String csvName	= "TRANSACTION"+"_"+bizDate+".CSV";
		
		ConvertDatabaseToCsv convert = new ConvertDatabaseToCsv();
		convert.convertDatabaseToCsv(dbName,table,condition,csvName);
	}

	/**
	 * Convert PLU data to CSV
	 * @param bizDate

	 */
	public void convertCSS014( String bizDate){
		final String dbName	= "SUMMARY.DB";			// Target DB
		final String table	= "CSS014";				// PLU
		
		// Condition (BIZDATE='20130701' and ZCOUNTER='000001')
		String condition= "BIZDATE='"+bizDate+"'";
		// CSV file name (CSS014_20130701_000001.CSV)
		String csvName	= "PLU_Report_"+bizDate+".CSV";
		
		ConvertDatabaseToCsv convert = new ConvertDatabaseToCsv();
		convert.convertDatabaseToCsv(dbName,table,condition,csvName);
	}
	
	/**
	 * Convert DEPARTMENT data to CSV
	 * @param bizDate

	 */
	public void convertCSS015( String bizDate){
		final String dbName	= "SUMMARY.DB";			// Target DB
		final String table	= "CSS015";				// DEPARTMENT

		String condition= "BIZDATE='"+bizDate+"'";
		String csvName	= "DEPARTMENT"+"_"+bizDate+".CSV";
		
		ConvertDatabaseToCsv convert = new ConvertDatabaseToCsv();
		convert.convertDatabaseToCsv(dbName,table,condition,csvName);
	}

	/**
	 * Convert GROUP data to CSV
	 * @param bizDate

	 */
	public void convertCSS016( String bizDate){
		final String dbName	= "SUMMARY.DB";			// Target DB
		final String table	= "CSS016";				// GROUP

		String condition= "BIZDATE='"+bizDate+"'";
		String csvName	= "GROUP"+"_"+bizDate+".CSV";
		
		ConvertDatabaseToCsv convert = new ConvertDatabaseToCsv();
		convert.convertDatabaseToCsv(dbName,table,condition,csvName);
	}

	/**
	 * Convert Clerk data to CSV
	 * @param bizDate

	 */
	public void convertCSS017( String bizDate){
		final String dbName	= "SUMMARY.DB";			// Target DB
		final String table	= "CSS017";				// Clerk
		
		// Condition (BIZDATE='20130701' and ZCOUNTER='000001')
		String condition= "BIZDATE='"+bizDate+"'";
		// CSV file name (CSS014_20130701_000001.CSV)
		String csvName	= "Clerk"+"_"+bizDate+".CSV";
		
		ConvertDatabaseToCsv convert = new ConvertDatabaseToCsv();
		convert.convertDatabaseToCsv(dbName,table,condition,csvName);
	}

	/**
	 * Convert CUSTOMER GROUP data to CSV
	 * @param bizDate

	 */
	public void convertCSS018( String bizDate){
		final String dbName	= "SUMMARY.DB";			// Target DB
		final String table	= "CSS018";				// CUSTOMER GROUP

		String condition= "BIZDATE='"+bizDate+"'";
		String csvName	= "CUSTOMER"+"_"+bizDate+".CSV";
		
		ConvertDatabaseToCsv convert = new ConvertDatabaseToCsv();
		convert.convertDatabaseToCsv(dbName,table,condition,csvName);
	}

	/**
	 * Convert HOURLY data to CSV
	 * @param bizDate

	 */
	public void convertCSS019( String bizDate){
		final String dbName	= "SUMMARY.DB";			// Target DB
		final String table	= "CSS019";				// HOURLY

		String condition= "BIZDATE='"+bizDate+"'";
		String csvName	= "HOURLY"+"_"+bizDate+".CSV";
		
		ConvertDatabaseToCsv convert = new ConvertDatabaseToCsv();
		convert.convertDatabaseToCsv(dbName,table,condition,csvName);
	}

	/**
	 * Convert MIX & MATCH data to CSV
	 * @param bizDate

	 */
	public void convertCSS110( String bizDate){
		final String dbName	= "SUMMARY.DB";			// Target DB
		final String table	= "CSS110";				// MIX & MATCH

		String condition= "BIZDATE='"+bizDate+"'";
		String csvName	= "MIX_MATCH"+"_"+bizDate+".CSV";
		
		ConvertDatabaseToCsv convert = new ConvertDatabaseToCsv();
		convert.convertDatabaseToCsv(dbName,table,condition,csvName);
	}

	
}
