package com.example.jarone.restoguideproject;

import android.provider.BaseColumns;

public final class ResturantContract {
    private ResturantContract(){}

    public static class PostEntry implements BaseColumns {
        public static final String TABLE_NAME = "RESTURANT";
        public static final String COL_NAME_NAME = "NAME";
        public static final String COL_NAME_ADDRESS = "ADDRESS";
        public static final String COL_NAME_PHONE = "PHONE";
        public static final String COL_NAME_DESCRIPTION = "DESCRIPTION";
        public static final String COL_NAME_TAGS = "TAGS";
        public static final String COL_NAME_RATING = "RATING";
        public static final String COL_TYPE_NAME = "TEXT";
        public static final String COL_TYPE_ADDRESS = "TEXT";
        public static final String COL_TYPE_PHONE = "INTEGER";
        public static final String COL_TYPE_DESCRIPTION = "TEXT";
        public static final String COL_TYPE_TAGS = "TEXT";
        public static final String COL_TYPE_RATING = "INTEGER";
    }

    public static final String SQL_CREATE_POSTS =
            "CREATE TABLE "+ PostEntry.TABLE_NAME+" ("+
                    PostEntry._ID+" INTEGER PRIMARY KEY,"+
                    PostEntry.COL_NAME_NAME+" "+ PostEntry.COL_TYPE_NAME+", "+
                    PostEntry.COL_NAME_ADDRESS+" "+ PostEntry.COL_TYPE_ADDRESS+", "+
                    PostEntry.COL_NAME_PHONE+" "+ PostEntry.COL_TYPE_PHONE+", "+
                    PostEntry.COL_NAME_DESCRIPTION+" "+ PostEntry.COL_TYPE_DESCRIPTION+", "+
                    PostEntry.COL_NAME_TAGS+" "+ PostEntry.COL_TYPE_TAGS+", "+
                    PostEntry.COL_NAME_RATING+" "+ PostEntry.COL_TYPE_RATING+")";

    public static final String SQL_DROP_POSTS =
            "DROP TABLE IF EXISTS "+ PostEntry.TABLE_NAME;
}
