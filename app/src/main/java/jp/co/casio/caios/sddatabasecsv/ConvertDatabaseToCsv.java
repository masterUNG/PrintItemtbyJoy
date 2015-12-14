package jp.co.casio.caios.sddatabasecsv;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;


/**
 * Database data output to CSV file
 *
 */
public class ConvertDatabaseToCsv {
	
	private final String DBFOLDER  = "/jp.co.casio.caios.framework.database/databases";
	private final String CSVFOLDER = "/ReportCSV";
	
	/**
	 * Read SD database, convert to CSV and write to SD
	 * SD file: /csv/xxxx.csv
	 * @param dbName  DB file name (Ex.SUMMARY.DB)
	 * @param table   Table name (Ex.CSS012)
	 * @param condition  Search condition (Ex.BIZDATE='20130701')
	 * @param csvName CSV file name (Ex.data.csv)
	 * @return  CSV data or error message.
	 */
	public String  convertDatabaseToCsv(
			String dbName,
			String table,
			String condition,
			String csvName ) {

		//
		// Open database on SD
		//
		File dbfile = Environment.getExternalStorageDirectory();
		String dbpath = dbfile.getPath() + DBFOLDER + "/" + dbName;
		// "/mnt/sdcard/jp.co.casio.caios.framework.database/databases/SUMMARY.DB"

		SQLiteDatabase db = null;
		try {
			db = SQLiteDatabase.openDatabase(
					dbpath,
					null,
					SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.OPEN_READONLY);
		} catch (Exception e) {
			return e.getMessage();
		}

		//
		// Read database from SD
		//
		Cursor cursor = null;
		String sql = "select * from '" + table + "' where " + condition;
		try {
			cursor = db.rawQuery(sql, null);
		} catch (Exception e) {
			//
		}
		if (cursor == null) {
			db.close();
			return "SQL query error:[" + sql + "]";
		}

		// เอา column nameมาวาง
		int columnCount = cursor.getColumnCount();

		String[] columnName = new String[columnCount];

		for (int i = 0; i < columnCount; i++) {

			columnName[i] = cursor.getColumnName(i);
		}
		//
		// Read data and convert to CSV
		//
		StringBuilder csvData = new StringBuilder("Report from : "+ table + condition+ "\n"+"\n" + Arrays.toString(columnName) + "\n");
//		StringBuilder csvData = new StringBuilder(sql+"\n");
		try {
			cursor.moveToFirst();
			final int column = cursor.getColumnCount();
			if (cursor.getCount() > -1) {
				do {
					for (int c = 0; c < column; c++) {
						String val = cursor.getString(c);
						if (c != 0) {
							csvData.append(",");
						}
						csvData.append(val);
					}
					csvData.append("\n");
				} while (cursor.moveToNext());
			}
			cursor.close();
			db.close();
		} catch (Exception e) {
			return condition;
		}


		//
		// Write CSV data to SD under "csv" folder.
		//
		final String folderName =
				Environment.getExternalStorageDirectory().getAbsolutePath() +
						CSVFOLDER;
		File csvfile = new File(folderName);
		if (csvfile.exists() == false) {
			csvfile.mkdir();
		}

		OutputStream outputStream;
		final String csvFileName =
				Environment.getExternalStorageDirectory().getAbsolutePath() +
						CSVFOLDER + "/" + csvName;
		try {
			csvfile = new File(csvFileName);
			outputStream = new FileOutputStream(csvfile);
		} catch (Exception e) {
			return e.getMessage();
		}

		String cvsDataString = csvData.toString();
		try {
			outputStream.write(cvsDataString.getBytes("TIS620"));
			outputStream.close();
		} catch (Exception e) {
			return e.getMessage();
		}

		return cvsDataString;
	}
	

}
