package com.example.frida.wmp;

/**
 * Created by Frida on 2014-09-24.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE = "Database.db";
    public static final int VERSION = 4;

    public static final String COLUMN_ID = "_id";

    public static final String USER = "user";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";

    public static final String[] USER_COLUMNS = { COLUMN_ID, FIRSTNAME, LASTNAME};

    public static final String EXPENSES = "expenses";
    public static final String EXCATEGORY = "excategory";
    public static final String EXPRICE = "exprice";
    public static final String EXTITLE = "extitle";
    public static final String EXIMAGE = "eximage";

    public static final String[] EXPENSES_COLUMNS = { COLUMN_ID, EXTITLE, EXPRICE, EXCATEGORY, EXIMAGE };

    public static final String INCOME = "income";
    public static final String INCATEGORY = "incategory";
    public static final String INTITLE = "intitle";
    public static final String INPRICE = "inprice";

    //t�nka p� ordningen?

    public static final String[] INCOMES_COLUMNS = { COLUMN_ID, INCATEGORY, INPRICE, INTITLE };

    public static final String RESULT = "result";
    public static final String CALCULATED = "calculated";

    public static final String[] RESULTS_COLUMNS = { COLUMN_ID, CALCULATED };


    public static final String CREATE_USER = " create table " + USER + "("
            + COLUMN_ID +  " integer primary key autoincrement " + ", "
            + FIRSTNAME + " text " + ", "
            + LASTNAME + " text " + "); ";


    public static final String CREATE_INCOME = " create table " + INCOME + "("
            + COLUMN_ID + " integer primary key autoincrement " + ", "
            + INCATEGORY + " text " + ", "
            + INTITLE + " text " + ", "
            + INPRICE + " integer " + " );";

    public static final String CREATE_EXPENSES = " create table " + EXPENSES + "("
            + COLUMN_ID + " integer primary key autoincrement " + ", "
            + EXTITLE + " text " + ", "
            + EXPRICE + " integer " + ", "
            + EXCATEGORY + " text " + ", "
            + EXIMAGE + " text " + " );";

    public static final String CREATE_RESULT = " create table " + RESULT + "("
            + COLUMN_ID + " integer primary key autoincrement " + ", "
            + CALCULATED + " integer " + ");";




    public Database(Context context) {
        super(context, DATABASE, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_INCOME);
        db.execSQL(CREATE_EXPENSES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER );
        db.execSQL("DROP TABLE IF EXISTS " + INCOME);
        db.execSQL("DROP TABLE IF EXISTS " + EXPENSES);

        onCreate(db);
    }


}

