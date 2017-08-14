package jp.co.tonaise.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//TODO 以下のテスト実行時エラーに対応する。
//org.h2.jdbc.JdbcSQLException: ユニークインデックス、またはプライマリキー違反: "PRIMARY_KEY_F ON PUBLIC.AP_USER(USER_ID) VALUES ('A1', 1)"
public class DBHelperTest {

	@Before
	public void setUp() throws Exception {
		String resourceName = "CreateTableDBHelperTest.sql";
		try {
			DBHelper.executeSqlFile(resourceName);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() {
		String resourceName = "DeleteTableDBHelperTest.sql";
		try {
			DBHelper.executeSqlFile(resourceName);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testコネクション取得とクローズ() {
		try {
			Connection con = DBHelper.getConnection();
			assertNotNull(con);
			DBHelper.closeConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			fail("コネクション取得・クローズテストに失敗しました");
		}
	}

	@Test
	public void testSQLファイルからのクエリ実行() {
		String resourceName = "InsertTableDBHelperTest.sql";
		try {
			DBHelper.executeSqlFile(resourceName);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSQLクエリ文字列の実行() {
		String sqlInsert = "INSERT INTO AP_USER (USER_ID,NAME,BIRTHDAY,AGE,ROLE_ID,           CREATED_AT,CREATED_BY,UPDATED_AT,UPDATED_BY) VALUES ('A6','ゆーざー6','1987-01-06',26,'1','2015-01-10'     ,'SYSTEM-USER','2015-01-10','SYSTEM-USER');";
		try {
			assertFalse(DBHelper.executeSqlQuery(sqlInsert));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			fail();
		}
		//レコード取得がされること
		String sqlSelect = "SELECT * FROM TYPELIST";
		try {
			assertTrue(DBHelper.executeSqlQuery(sqlSelect));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			fail();
		}
		//条件句によるレコード取得0件時でもtrueで正常終了すること
		String sqlSelectNotExists = "SELECT * FROM TYPELIST where 1=0";
		try {
			assertTrue(DBHelper.executeSqlQuery(sqlSelectNotExists));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testexecuteSqlFileで引数null時検証() {
		try {
			DBHelper.executeSqlFile(null, null);
			fail();
		} catch (NullPointerException e) {
			assertTrue(e.getMessage().equals("コネクション、リーソース名を設定してください。"));
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testexecuteSqlQueryで引数null時検証() {
		try {
			DBHelper.executeSqlQuery(null, null);
			fail();
		} catch (NullPointerException e) {
			assertTrue(e.getMessage().equals("コネクション、SQLクエリを設定してください"));
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}

}
