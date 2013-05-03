package com.travel.story.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.travel.story.entity.Note;

public class SQLiteTravel extends SQLiteOpenHelper {

    private static final String DB_NAME          = "kostravel.sqlite"; // 資料庫名稱
    private static final int    DATABASE_VERSION = 1;                 // 資料庫版本
    private SQLiteDatabase      db;
    private final Context       ctx;

    // Define database schema
    public interface NoteSchema {
        String TABLE_NAME = "notes";
        String title      = "title";
        String author     = "author";
        String date       = "date";
        String pic        = "pic";
        String content    = "content";
        String read_num   = "read_num";
        String note_id    = "note_id";
        String ID         = "id";
    }

    public SQLiteTravel(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        ctx = context;

        if (db == null)
            db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + NoteSchema.TABLE_NAME + " (" + NoteSchema.ID + " INTEGER PRIMARY KEY" + "," + NoteSchema.title + " TEXT"
                + "," + NoteSchema.author + " TEXT" + "," + NoteSchema.date + " TEXT" + "," + NoteSchema.pic + " TEXT" + "," + NoteSchema.content + " TEXT"
                + "," + NoteSchema.read_num + " INTEGER" + "," + NoteSchema.note_id + " INTEGER NOT NULL" + ");");

    }

    public boolean deleteNote(Note note) {
        Cursor cursor = db.rawQuery("DELETE FROM " + NoteSchema.TABLE_NAME + " WHERE id = ?", new String[] { note.getId() + "" });
        cursor.moveToFirst();
        cursor.close();
        return true;
    }

    public long insertNote(Note note) {
        ContentValues args = new ContentValues();
        args.put(NoteSchema.note_id, note.getId());
        args.put(NoteSchema.title, note.getTitle());
        args.put(NoteSchema.author, note.getAuthor());
        args.put(NoteSchema.date, note.getDate());
        args.put(NoteSchema.pic, note.getPic());
        args.put(NoteSchema.content, note.getContent());
        args.put(NoteSchema.read_num, note.getReadNum());

        return db.insert(NoteSchema.TABLE_NAME, null, args);
    }

    public ArrayList<Note> getAllNotes() {
        Cursor cursor = null;
        ArrayList<Note> notes = new ArrayList<Note>();
        cursor = db.rawQuery("SELECT * FROM " + NoteSchema.TABLE_NAME + " ORDER BY id DESC", null);

        while (cursor.moveToNext()) {

            String title = cursor.getString(1);
            String author = cursor.getString(2);
            String date = cursor.getString(3);
            String pic = cursor.getString(4);
            String content = cursor.getString(5);
            int read_num = cursor.getInt(6);
            int note_id = cursor.getInt(7);

            Note note = new Note(note_id, title, author, date, pic, content, read_num);
            notes.add(note);
        }
        return notes;
    }

    public Boolean isNoteCollected(int note_id) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + NoteSchema.TABLE_NAME + " where note_id = " + note_id, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
