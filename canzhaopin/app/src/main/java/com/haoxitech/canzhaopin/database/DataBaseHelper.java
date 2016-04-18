package com.haoxitech.canzhaopin.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wangtao on 16/3/10.
 */
public class DataBaseHelper {

    private static final String LOG_TAG = "SQLite";

    private static SQLiteDatabase sqLiteDatabase;

    private final static String DIR_PATH = "/data/data/com.haoxitech.canzhaopin/databases/";

    private final static String DB_PATH = "/data/data/com.haoxitech.canzhaopin/databases/zipArea.db";

    public DataBaseHelper() {

    }

    public static void openDB() {
        try {
            sqLiteDatabase = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.CONFLICT_IGNORE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int create(String sql, Object... args) {

        if (sql == null)
            return 0;
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            openDB();
        }
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            return 0;
        }
        try {
            sqLiteDatabase.execSQL(sql, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
        }
        return -1;
    }

    /**
     * 执行insert语句
     *
     * @param sql
     *            insert语句
     * @param args
     *            要插入的数据
     * @return
     */
    public static int insert(String sql, Object... args) {

        if (sql == null)
            return 0;
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            openDB();
        }
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            return 0;
        }
        try {
            sqLiteDatabase.execSQL(sql, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
        }
        return -1;
    }

    /**
     * 执行select语句
     *
     * @param sql
     *            select语句
     * @param args
     *            参数
     * @return 一个数据集合List
     */
    public static List<Map<String, Object>> select(String sql, Object... args) {

        Cursor cursor = null;
        try {
            if (sql == null)
                return null;

            if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
                openDB();
            }
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> map;
            String[] _args = new String[0];
            if (args != null) {
                _args = new String[args.length];

                for (int i = 0; i < args.length; i++) {
                    _args[i] = (args[i] == null ? "null" : args[i].toString());

                }
            }

            if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
                return null;
            }
            cursor = sqLiteDatabase.rawQuery(sql, _args);
            String[] columnNames = cursor.getColumnNames();

            if (cursor != null) {
                if (cursor.moveToFirst()) {

                    for (int i = 0; i < cursor.getCount(); i++) {
                        map = new HashMap<String, Object>();

                        for (int j = 0; j < cursor.getColumnCount(); j++) {

                            map.put(columnNames[j], cursor.getString(j));
                        }

                        cursor.moveToNext();

                        list.add(map);
                    }

                    if (list != null) {
                        return list;
                    }

                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {

            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    /**
     * 执行selectUnique语句
     *
     * @param sql
     *            selectUnique语句
     * @param args
     *            参数
     * @return 唯一一条数据
     */
    public static Map<String, Object> selectUnique(String sql, Object... args) {
        if (sql == null)
            return null;
        Cursor cursor = null;
        try {
            if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
                openDB();
            }
            Map<String, Object> map = new HashMap<String, Object>();
            String[] _args = new String[0];
            if (args != null) {
                _args = new String[args.length];

                for (int i = 0; i < args.length; i++) {
                    _args[i] = (args[i] == null ? "null" : args[i].toString());

                }
            }
            if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
                return null;
            }
            cursor = sqLiteDatabase.rawQuery(sql, _args);
            String[] columnNames = cursor.getColumnNames();
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    for (int j = 0; j < cursor.getColumnCount(); j++) {
                        map.put(columnNames[j], cursor.getString(j));
                    }

                    if (map != null) {
                        return map;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }

    /**
     * 执行update语句
     *
     * @param sql
     *            update语句
     * @param args
     *            参数
     * @return
     */
    public static int update(String sql, Object... args) {
        if (sql == null)
            return 0;
        try {
            if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
                openDB();
            }
            if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
                return 0;
            }
            sqLiteDatabase.execSQL(sql, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
        }
        return -1;
    }

    /**
     * 执行drop语句
     *
     * @param sql
     *            drop语句
     * @param args
     *            参数
     * @return
     */
    public static int drop(String sql, Object... args) {
        if (sql == null)
            return 0;
        try {
            if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
                openDB();
            }
            if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
                return 0;
            }
            sqLiteDatabase.execSQL(sql, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
        }
        return -1;
    }

    /**
     * 执行delete语句
     *
     * @param sql
     *            delete语句
     * @param args
     *            参数
     * @return
     */
    public static int delete(String sql, Object... args) {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            openDB();
        }
        try {
            sqLiteDatabase.execSQL(sql, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeDb();
        }
        return -1;
    }

    /**
     * 关闭数据库
     */
    public static void closeDb() {

        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
        }

    }

    /**
     * 将数据库写入到指定路径下
     *
     * @param context
     *            上下文
     * @throws IOException
     *             IO流异常
     */
    public static void copyDataBase(Context context, String filename)
            throws IOException {

        String tagPath = DIR_PATH;
        File dbFile = new File(tagPath + filename);
        if (dbFile.exists()) {
            return;
        }

        InputStream is = context.getAssets().open(filename);
        OutputStream os = null;
        File filePath = new File(tagPath);

        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        try {
            String outFileName = tagPath + filename;
            os = new FileOutputStream(outFileName);
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = is.read(b)) != -1) {
                os.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            is.close();
            os.close();
        }
    }
}
