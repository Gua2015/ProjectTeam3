package com.missouristate.guadagnano.groupprojectapp;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "attendanceDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STUDENTS = "students";
    private static final String TABLE_INSTRUCTORS = "instructors";
    private static final String ID = "id";
    private static final String FIRSTNAME = "firstName";
    private static final String LASTNAME = "lastName";
    private static final String STATUS = "status";


    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //build sql create statement

        String sqlCreateTableStudent = "create table " + TABLE_STUDENTS + "(" + ID;
        sqlCreateTableStudent += " integer primary key autoincrement, " + FIRSTNAME;
        sqlCreateTableStudent += " text, " + LASTNAME;
        sqlCreateTableStudent += " text, " + STATUS;
        sqlCreateTableStudent += " text)";

        String sqlCreateTableInstructor = "create table " + TABLE_INSTRUCTORS + "(" + ID;
        sqlCreateTableStudent += " integer primary key autoincrement, " + FIRSTNAME;
        sqlCreateTableStudent += " text, " + LASTNAME;
        sqlCreateTableStudent += " text, " + STATUS;
        sqlCreateTableStudent += " text)";

        //create tables
        db.execSQL(sqlCreateTableStudent);
        db.execSQL(sqlCreateTableInstructor);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // on upgrade, drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSTRUCTORS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);

        // create new tables
        onCreate(db);
    }

    public void insertStudent (Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsertStudent = "insert into " + TABLE_STUDENTS;
        sqlInsertStudent += " values( null, '" + student.getFirstName( );
        sqlInsertStudent += "', '" + student.getLastName( ) ;
        sqlInsertStudent += "', '" + student.getStatus( ) + "' )";

        db.execSQL(sqlInsertStudent);
        db.close();
    }

    public void insertInstructor (Instructor instructor){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsertInstructor = "insert into " + TABLE_INSTRUCTORS;
        sqlInsertInstructor += " values( null, '" + instructor.getFirstName( );
        sqlInsertInstructor += "', '" + instructor.getLastName( ) ;
        sqlInsertInstructor += "', '" + instructor.getStatus( ) + "' )";

        db.execSQL(sqlInsertInstructor);
        db.close();
    }

    public void deleteInstructorById( int id ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlDeleteInstructor = "delete from " + TABLE_INSTRUCTORS;
        sqlDeleteInstructor += " where " + ID + " = " + id;

        db.execSQL( sqlDeleteInstructor );
        db.close( );
    }

    public void deleteStudentById( int id ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlDeleteStudent = "delete from " + TABLE_STUDENTS;
        sqlDeleteStudent += " where " + ID + " = " + id;

        db.execSQL( sqlDeleteStudent );
        db.close( );
    }

    public void updateStudentById( int id, String fname, String lname, String email ) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlUpdate = "update " + TABLE_STUDENTS;
        sqlUpdate += " set " + FIRSTNAME + " = '" + fname + "', ";
        sqlUpdate += LASTNAME + " = '" + lname + "', ";
        sqlUpdate += STATUS + " = '" + email + "'";
        sqlUpdate += " where " + ID + " = " + id;

        db.execSQL( sqlUpdate );
        db.close( );
    }

    public void updateInstructorById( int id, String fname, String lname, String email ) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlUpdate = "update " + TABLE_INSTRUCTORS;
        sqlUpdate += " set " + FIRSTNAME + " = '" + fname + "', ";
        sqlUpdate += LASTNAME + " = '" + lname + "', ";
        sqlUpdate += STATUS + " = '" + email + "'";
        sqlUpdate += " where " + ID + " = " + id;

        db.execSQL( sqlUpdate );
        db.close( );
    }

    public ArrayList<Student> selectAllStudents( ) {
        String sqlQuery = "select * from " + TABLE_STUDENTS;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<Student> students = new ArrayList<Student>( );
        while( cursor.moveToNext( ) ) {
            Student currentStudents
                    = new Student( Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ), cursor.getString( 2 ),
                    cursor.getString(3));
            students.add( currentStudents );
        }
        db.close( );
        return students;
    }

    public ArrayList<Instructor> selectAllInstructors( ) {
        String sqlQuery = "select * from " + TABLE_INSTRUCTORS;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<Instructor> instructors = new ArrayList<Instructor>( );
        while( cursor.moveToNext( ) ) {
            Instructor currentInstructors
                    = new Instructor( Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ), cursor.getString( 2 ),
                    cursor.getString(3));
            instructors.add( currentInstructors );
        }
        db.close( );
        return instructors;
    }

    public Student selectStudentById( int id ) {
        String sqlQuery = "select * from " + TABLE_STUDENTS;
        sqlQuery += " where " + ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        Student student = null;
        if( cursor.moveToFirst( ) )
            student = new Student( Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ), cursor.getString( 2 ),
                    cursor.getString(3));
        return student;
    }

    public Instructor selectById( int id ) {
        String sqlQuery = "select * from " + TABLE_INSTRUCTORS;
        sqlQuery += " where " + ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        Instructor instructor = null;
        if( cursor.moveToFirst( ) )
            instructor = new Instructor( Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ), cursor.getString( 2 ),
                    cursor.getString(3));
        return instructor;
    }
}
