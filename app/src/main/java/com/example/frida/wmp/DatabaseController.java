package com.example.frida.wmp;

/**
 * Created by Frida on 2014-09-24.
 */
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Log;

public class DatabaseController {
    private static Database helper;
    private static SQLiteDatabase database;

    public static void init(Context context) {
        helper = new Database(context);
    }

    /**
     *
     */
    public static void close() {
        helper.close();
        database = null;
    }

    public static SQLiteDatabase open() {
        database = helper.getWritableDatabase();
        return database;
    }

    // USER METODER/////////////////////////////////////////////////////

    public static void addUser(User user) {
        open();

        ContentValues values = new ContentValues();

        database.execSQL("DROP TABLE IF EXISTS " + Database.USER);
        database.execSQL(Database.CREATE_USER);

        values.put(Database.FIRSTNAME, user.getFirstName());
        values.put(Database.LASTNAME, user.getLastName());

        database.insert(Database.USER, null, values);

        close();
    }

    // Skapar en arraylist med user s�tter en cursor p� den (pekare)

    public static User getUser() {
        open();
        Cursor cursor = database.query(Database.USER, Database.USER_COLUMNS,
                null, null, null, null, null);

        User user = null;

        if (cursor.moveToNext())
            user = cursorToUser(cursor);

        close();
        return user;
    }

    // Konverterar cursorn till objekt User som h�mtar info

    public static User cursorToUser(Cursor cursor) {

        int id = cursor.getInt(0);
        String firstname = cursor.getString(1);
        String lastname = cursor.getString(2);

        return new User(firstname, lastname);
    }

    /**
     * Metod f�r att uppdatera user infrmation i databasen dvs f�r och efternamn
     *
     */
    public static void updateUser(User user) {
        ContentValues values = new ContentValues();

        values.put(Database.FIRSTNAME, user.getFirstName());
        values.put(Database.LASTNAME, user.getLastName());

        open();
        int r = database.update(Database.USER, values, null, null);

        Log.i("tag", "r: " + r);
        close();

    }

    // INCOME Metoder////////////////////////////////////////////////

    // L�gg till inkomst info i databasen
    public static void addIncome(Income income) {
        open();
        ContentValues values = new ContentValues();

        values.put(Database.INPRICE, income.getPrice());
        values.put(Database.INCATEGORY, income.getCategory());
        values.put(Database.INTITLE, income.getTitle());

        database.insert(Database.INCOME, null, values);
        close();
    }

    // Skapar en arraylist med inkomst s�tter en cursor p� den (pekare)

    public static ArrayList<Income> getAllIncomes() {
        open();
        ArrayList<Income> newIncomeList = new ArrayList<Income>();

        Cursor cursor = database.query(Database.INCOME,
                Database.INCOMES_COLUMNS, null, null, null, null, null);

        while (cursor.moveToNext())
            newIncomeList.add(cursorToIncome(cursor));

        close();

        return newIncomeList;

    }

    // Konverterar cursorn till objekt Income som h�mtar

    public static Income cursorToIncome(Cursor cursor) {
        int id = cursor.getInt(0);

        String title = cursor.getString(1);
        int price = cursor.getInt(2);
        String category = cursor.getString(3);

        return new Income(title, price, category);
    }

    /**
     * Metod f�r att uppdatera income
     *
     */

    public static void updateIncome(Income income) {
        ContentValues values = new ContentValues();

        values.put(Database.INCATEGORY, income.getCategory());
        values.put(Database.INTITLE, income.getTitle());
        values.put(Database.INPRICE, income.getPrice());
        database.isOpen();
        int r = database.update(Database.INCOME, values, null, null);

        Log.i("tag", "r: " + r);
        database.close();

    }

    public static Income getIncome() {
        open();
        Cursor cursor = database.query(Database.INCOME,
                Database.INCOMES_COLUMNS, null, null, null, null, null);

        Income income = null;

        if (cursor.moveToNext())
            income = cursorToIncome(cursor);

        close();
        return income;
    }

    public static Cursor getIncome2() {
        Cursor c = database.rawQuery("SELECT * FROM " + Database.INCOME, null);
        return c;
    }

    public static int calcIncome() {
        int sum = 0;
        open();
        Cursor c = getIncome2();
        if (c.moveToFirst()) {
            do {
                sum += c.getInt(3);
            } while (c.moveToNext());
        }
        close();

        return sum;
    }

    // EXPENSE METODER////////////////////////////////////////////

    public static void addExpense(Expense expense) {
        open();
        ContentValues values = new ContentValues();

        values.put(Database.EXCATEGORY, expense.getCategory());
        values.put(Database.EXTITLE, expense.getTitle());
        values.put(Database.EXPRICE, expense.getPrice());
        values.put(Database.EXIMAGE, expense.getImage());

        database.insert(Database.EXPENSES, null, values);

        close();

    }

    public static ArrayList<Expense> getAllExpenses() {
        open();
        ArrayList<Expense> newExpenseList = new ArrayList<Expense>();

        Cursor cursor = database.query(Database.EXPENSES,
                Database.EXPENSES_COLUMNS, null, null, null, null, null);

        while (cursor.moveToNext())
            newExpenseList.add(cursorToExpense(cursor));

        cursor.close();
        close();

        return newExpenseList;
    }

    public static Expense getExpense() {
        open();
        Cursor cursor = database.query(Database.EXPENSES,
                Database.EXPENSES_COLUMNS, null, null, null, null, null);

        Expense expense = null;

        if (cursor.moveToNext())
            expense = cursorToExpense(cursor);

        close();
        return expense;
    }


    /**
     * Metod f�r att uppdatera Expence
     *
     */
    public static void updateExpense(Expense expense) {
        ContentValues values = new ContentValues();

        values.put(Database.EXCATEGORY, expense.getCategory());
        values.put(Database.EXTITLE, expense.getTitle());
        values.put(Database.EXPRICE, expense.getPrice());
        //values.put(Database.EXIMAGE, expense.getImage());
        database.isOpen();
        int r = database.update(Database.EXPENSES, values, null, null);

        Log.i("tag", "r: " + r);
        database.close();

    }

    // public static Expense getExpense() {
    // open();
    // Cursor cursor = database.query(Database.EXPENSES,
    // Database.EXPENSES_COLUMNS, null, null, null, null, null);
    //
    // Expense expense = null;
    //
    // if (cursor.moveToNext())
    // expense = cursorToExpense(cursor);
    //
    // close();
    // return expense;
    // }

    public static Expense cursorToExpense(Cursor cursor) {
        long id = cursor.getInt(0);

        String title = cursor.getString(1);
        int price = cursor.getInt(2);
        String category = cursor.getString(3);
        String image = cursor.getString(4);

        Expense exp = new Expense(title, price, category,image);//
        exp.setId(id);

        return exp;
    }

    public static Cursor getExpense2() {
        Cursor c = database
                .rawQuery("SELECT * FROM " + Database.EXPENSES, null);
        return c;
    }

    public static int calcExpense() {
        int sum = 0;
        open();
        Cursor c = getExpense2();
        if (c.moveToFirst()) {
            do {
                sum += c.getInt(2);
            } while (c.moveToNext());
        }
        close();

        return sum;

    }

    public static void cleanExp() {
        open();

        database.execSQL("DROP TABLE IF EXISTS " + Database.EXPENSES);
        database.execSQL(Database.CREATE_EXPENSES);

        close();
    }

    public static void cleanInc() {
        open();

        database.execSQL("DROP TABLE IF EXISTS " + Database.INCOME);
        database.execSQL(Database.CREATE_INCOME);

        close();
    }

    public static void cleanUser() {
        open();

        database.execSQL("DROP TABLE IF EXISTS " + Database.USER);
        database.execSQL(Database.CREATE_USER);

        close();
    }

}

