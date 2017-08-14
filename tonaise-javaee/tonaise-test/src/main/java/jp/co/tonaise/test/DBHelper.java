package jp.co.tonaise.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// TODO use logback sysoutにはlogbackを利用する
/**
 * DB接続コネクションを管理します。<br>
 * private static Connection con;を保持します。<br>
 * コネクション取得済みの場合は同一のコネクションを返却します。
 * 
 * @author tonaise
 *
 */
public class DBHelper {

	// ~/tonaisetestがDB名です。
	// ~をつけずにDB名のみ記載するとカレントディレクトリにDBができるとのことです。

	/** h2Driver名です */
	private final static String DRIVER_NAME = "org.h2.Driver";
	/** テストDBのURLです。 */
	private final static String DRIVER_URL = "jdbc:h2:file:~/tonaisetest";
	/** テストDB接続用ユーザです。 */
	private final static String USER_NAME = "sa";
	/** テストDB接続用パスワードです。 */
	private final static String PASSWORD = "";
	/** DBとの接続コネクションです。 */
	private static Connection con;

	/**
	 * コネクションを `DriverManager.getConnection(DRIVER_URL, USER_NAME, PASSWORD);` により取得します。<br>
	 * 設定はクラス内変数で固定値です。<br>
	 * 設定済みの場合は何もしません。
	 * 
	 * @return Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		// TODO use logback
		if (null == con) {
			try {
				Class.forName(DRIVER_NAME);
				con = DriverManager.getConnection(DRIVER_URL, USER_NAME, PASSWORD);
			} catch (ClassNotFoundException e) {
				System.out.println("Can't Find H2 Driver.");
				throw e;
			} catch (SQLException e) {
				System.out.println("Connection Error.");
				throw e;
			}
		} else {
			System.out.println("trace:コネクションは既に設定済みです");
		}
		return con;
	}

	/**
	 * SQLを記載したファイルを読み込み１行ずつ繰り返し実行します。<br>
	 * コネクションはDBHelper#getConnection()により取得し実行します。
	 * 
	 * @param resourceName DBHelper.class.getResourceAsStream(resourceName);によって読み込まれます。
	 * @return SQLクエリ実行結果を返却します。最初の結果が ResultSet オブジェクトの場合は true。更新カウントであるか、または結果がない場合は false。
	 * @throws IOException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static boolean executeSqlFile(String resourceName) throws IOException, SQLException, ClassNotFoundException {
		return executeSqlFile(getConnection(), resourceName);
	}

	/**
	 * SQLを記載したファイルを読み込み１行ずつ繰り返し実行します。
	 * 
	 * @param con DBHelper#getConnection()により取得を推奨します。
	 * @param resourceName DBHelper.class.getResourceAsStream(resourceName);によって読み込まれます。
	 * @return SQLクエリ実行結果を返却します。最初の結果が ResultSet オブジェクトの場合は true。更新カウントであるか、または結果がない場合は false。
	 * @throws IOException
	 * @throws SQLException
	 */
	public static boolean executeSqlFile(Connection con, String resourceName) throws IOException, SQLException {
		if (con == null || resourceName == null || resourceName.isEmpty()) {
			throw new NullPointerException("コネクション、リーソース名を設定してください。");
		}

		InputStream in = DBHelper.class.getResourceAsStream(resourceName);
		BufferedReader br = null;
		boolean result = false;
		try {
			br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String oneLine;
			while ((oneLine = br.readLine()) != null) {
				// コメント行は除く。複数行コメントには対応しない。
				if (oneLine.trim().startsWith("--") || oneLine.trim().startsWith("/*")) {
					continue;
				}
				sb.append(oneLine).append(" ");
			}

			String sqls = sb.toString();
			// 改行を除く。タブを半角スペースに置き換え。
			sqls = sqls.replaceAll("\r", "");
			sqls = sqls.replaceAll("\n", "");
			sqls = sqls.replaceAll("\t", " ");
			sqls = sqls.trim();
			if (sqls.endsWith(";")) {
				sqls = sqls.substring(0, sqls.length() - 1);
			}
			String[] sqlList = sqls.split(";");

			// 1行ずつ繰り返し実行する
			for (String sql : sqlList) {
				result = executeSqlQuery(con, sql);
			}
			return result;
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}

	/**
	 * sqlを実行します。<br>
	 * コネクションはDBHelper#getConnection()により取得し実行します。
	 * 
	 * @param con DBHelper#getConnection()により取得を推奨します。
	 * @param sql 実行するSQLクエリ文字列を指定します。
	 * @return SQLクエリ実行結果を返却します。最初の結果が ResultSet オブジェクトの場合は true。更新カウントであるか、または結果がない場合は false。
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static boolean executeSqlQuery(String sql) throws SQLException, ClassNotFoundException {
		return executeSqlQuery(getConnection(), sql);
	}

	/**
	 * sqlを実行します。
	 * 
	 * @param con DBHelper#getConnection()により取得を推奨します。
	 * @param sql 実行するSQLクエリ文字列を指定します。
	 * @return SQLクエリ実行結果を返却します。最初の結果が ResultSet オブジェクトの場合は true。更新カウントであるか、または結果がない場合は false。
	 * @throws SQLException
	 */
	public static boolean executeSqlQuery(Connection con, String sql) throws SQLException {
		if (sql == null || sql.isEmpty() || con == null) {
			throw new NullPointerException("コネクション、SQLクエリを設定してください");
		}
		Statement statement = null;
		try {
			statement = con.createStatement();
			// SQLクエリ実行結果を返却します。最初の結果が ResultSet オブジェクトの場合は true。更新カウントであるか、または結果がない場合は false。
			return statement.execute(sql);
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

	/**
	 * 取得済みのコネクションをクローズします。
	 */
	public static void closeConnection() {
		if (null != con) {
			try {
				con.close();
				con = null;
			} catch (Exception ex) {
				System.out.println("コネクションクローズ時に例外が発生しました。" + "class[" + ex.getClass() + "],cause[" + ex.getCause() + "],message[" + ex.getMessage() + "]");
			}
		}
	}
}
