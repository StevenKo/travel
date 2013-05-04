package com.travel.story.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.travel.story.entity.Note;
import com.travel.story.entity.Site;

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

    public interface SiteSchema {
        String TABLE_NAME = "sites";
        String name       = "name";
        String info       = "info";
        String pic        = "pic";
        String pics       = "pics";
        String starInt    = "star_int";
        String introduce  = "introduce";
        String site_id    = "site_id";
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
        db.execSQL("CREATE TABLE IF NOT EXISTS " + SiteSchema.TABLE_NAME + " (" + SiteSchema.ID + " INTEGER PRIMARY KEY" + "," + SiteSchema.name + " TEXT"
                + "," + SiteSchema.info + " TEXT" + "," + SiteSchema.pic + " TEXT" + "," + SiteSchema.pics + " TEXT" + "," + SiteSchema.starInt + " INTEGER"
                + "," + SiteSchema.introduce + " TEXT" + "," + SiteSchema.site_id + " INTEGER NOT NULL" + ");");

    }

    public boolean deleteNote(Note note) {
        Cursor cursor = db.rawQuery("DELETE FROM " + NoteSchema.TABLE_NAME + " WHERE id = ?", new String[] { note.getId() + "" });
        cursor.moveToFirst();
        cursor.close();
        return true;
    }

    public boolean deleteSite(Site site) {
        Cursor cursor = db.rawQuery("DELETE FROM " + SiteSchema.TABLE_NAME + " WHERE id = ?", new String[] { site.getId() + "" });
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

    public long insertSite(Site site) {
        ContentValues args = new ContentValues();
        args.put(SiteSchema.site_id, site.getId());
        args.put(SiteSchema.name, site.getName());
        args.put(SiteSchema.info, site.getInfo());
        args.put(SiteSchema.pic, site.getPic());
        args.put(SiteSchema.pics, convertArrayToString(site.getPics()));
        args.put(SiteSchema.starInt, site.getStarInt());
        args.put(SiteSchema.introduce, site.getIntroduction());

        return db.insert(SiteSchema.TABLE_NAME, null, args);
    }

    public ArrayList<Site> getAllSites() {
        Cursor cursor = null;
        ArrayList<Site> sites = new ArrayList<Site>();
        cursor = db.rawQuery("SELECT * FROM " + SiteSchema.TABLE_NAME + " ORDER BY id DESC", null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String info = cursor.getString(2);
            String pic = cursor.getString(3);
            String[] pics = convertStringToArray(cursor.getString(4));
            int starInt = cursor.getInt(5);
            String introduce = cursor.getString(6);
            int site_id = cursor.getInt(7);

            Site site = new Site(site_id, starInt, name, pic, info, introduce, pics);
            sites.add(site);
        }
        return sites;
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

    public Boolean isSiteCollected(int site_id) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + SiteSchema.TABLE_NAME + " where site_id = " + site_id, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static String convertArrayToString(String[] array) {
        String str = "";
        for (int i = 0; i < array.length; i++) {
            str = str + array[i];
            // Do not append comma at the end of last element
            if (i < array.length - 1) {
                str = str + ",";
            }
        }
        return str;
    }

    public static String[] convertStringToArray(String str) {
        String[] arr = str.split(",");
        return arr;
    }

}
