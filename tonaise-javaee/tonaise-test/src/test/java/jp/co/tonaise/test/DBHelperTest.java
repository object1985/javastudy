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
		String resourceName = "CreateTableDBHelperTest.sql";
		try {
			DBHelper.executeSqlFile(resourceName);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSQLクエリ文字列の実行() {
		String sqlInsert = "INSERT INTO type_model values('もじ列','1','1','1',1,2,3,0.1,0.2,0.3,0.4,'hogehogehogeohgeohgeohgeoh','1013','1','2015-07-01','12:13:14','2015-07-01 12:13:14.555')";
		try {
			assertFalse(DBHelper.executeSqlQuery(sqlInsert));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			fail();
		}
		String sqlSelect = "SELECT * FROM type_model";
		try {
			assertTrue(DBHelper.executeSqlQuery(sqlSelect));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			fail();
		}
		String sqlSelectNotExists = "SELECT * FROM type_model where 1=0";
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
