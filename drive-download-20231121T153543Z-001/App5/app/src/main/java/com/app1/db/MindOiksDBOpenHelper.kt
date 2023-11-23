package com.app1.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.app1.MenuModel
import com.app1.Model

class MindOiksDBOpenHelper(
    context: Context,
    factory: SQLiteDatabase.CursorFactory?
) :
    SQLiteOpenHelper(
        context, DATABASE_NAME,
        factory, DATABASE_VERSION
    ) {
    override fun onCreate(db: SQLiteDatabase) {
        val sql =
            ("CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY,$COLUMN_NAME TEXT,$COLUMN_PRICE TEXT)")
        db.execSQL(sql)
        val sql2 =
            ("CREATE TABLE $USER_TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,$USER_USERNAME TEXT,$USER_PASSWORD TEXT,$USER_EMAIL TEXT,$USER_PHONE_NUMBER TEXT, $USER_REGION TEXT, $USER_STREET TEXT)")
        db.execSQL(sql2)
        val sql3 =
            ("CREATE TABLE $REST_TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,$REST_NAME TEXT,$REST_DES TEXT,$REST_LOGO TEXT)")
        db.execSQL(sql3)

        val sql4 =
            ("CREATE TABLE $ITEM_TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,$ITEM_NAME TEXT,$ITEM_PRICE TEXT,$ITEM_PHOTO TEXT,$ITEM_REST_ID INTEGER)")
        db.execSQL(sql4)

        val sql5 =
            ("CREATE TABLE $REST_ITEM_TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,$REST_ID INTEGER,$ITEM_ID INTEGER)")
        db.execSQL(sql5)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $USER_TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $REST_TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $ITEM_TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $REST_ITEM_TABLE_NAME")


        onCreate(db)
    }

    fun addResturant(item: Model) {
        val values = ContentValues()
        values.put(REST_NAME, item.name)
        values.put(REST_LOGO, item.res)
        values.put(REST_DES, item.des)

        val db = this.writableDatabase
        val idd = db.insert(REST_TABLE_NAME, null, values)
        db.close()
    }

    fun addItemForResturant(item: MenuModel, id: Long) {
        var itemId: Long = -1

//        val db = this.readableDatabase
//        val cursor2 =
//            db.rawQuery("SELECT * FROM $ITEM_TABLE_NAME WHERE $ITEM_NAME  = '${item.name}'", null)
//        cursor2.use { cur ->
//            if (cur != null && cur.count > 0) {
//                cur.moveToNext()
//                itemId = cur.getLong(cur.getColumnIndex(COLUMN_ID))
//
//            }else{
                val values = ContentValues()
                values.put(ITEM_NAME, item.name)
                values.put(ITEM_PRICE, item.price)
                values.put(ITEM_PHOTO, item.photo)
                values.put(ITEM_REST_ID, id)

                val db4 = this.writableDatabase
                itemId = db4.insert(ITEM_TABLE_NAME, null, values)
                db4.close()
//            }
//        }

        val values2 = ContentValues()
        values2.put(REST_ID, id)
        values2.put(ITEM_ID, itemId)

        val db2 = this.writableDatabase
        db2.insertOrThrow(REST_ITEM_TABLE_NAME, null, values2)
        db2.close()

    }

    fun getITemsForResturant(id: Long): List<MenuModel>? {
//        val values = ContentValues()
//        values.put(ITEM_NAME, item.name)
//        values.put(ITEM_PRICE, item.price)
//        values.put(ITEM_PHOTO, item.photo)
//
//        val db = this.writableDatabase
////        try {
//        val itemId = db.insert(ITEM_TABLE_NAME, null, values)
////        }catch (ex: SQLException){
//        db.close()
//
////        }
//
//        val values2 = ContentValues()
//        values2.put(REST_ID, id)
//        values2.put(ITEM_ID, itemId)
//
//        val db2 = this.writableDatabase
//        db2.insert(REST_ITEM_TABLE_NAME, null, values2)
//        db2.close()

        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $ITEM_TABLE_NAME WHERE $ITEM_REST_ID = ${id}", null)

        cursor.use { it ->
            if (it != null && it.count > 0) {
                val list = ArrayList<MenuModel>();
                while (cursor.moveToNext()) {
//                    val _id = it.getLong(it.getColumnIndex(ITEM_ID))

//                    val cursor2 =
//                        db.rawQuery(
//                            "SELECT * FROM $ITEM_TABLE_NAME WHERE $COLUMN_ID = ${_id}",
//                            null
//                        )
//                    cursor2.use { cur ->
//                        if (cur != null && cur.count > 0) {
//                            cur.moveToNext()
                            val name = it.getString(it.getColumnIndex(ITEM_NAME))
                            val price = it.getString(it.getColumnIndex(ITEM_PRICE))
                            val photo = it.getString(it.getColumnIndex(ITEM_PHOTO))

                            list.add(MenuModel(name, price, photo))
//                        }
//                    }
                }
                return list
            } else {
                return null
            }
        }

    }

    fun addItem(item: Items) {
        val values = ContentValues()
        values.put(COLUMN_NAME, item.itemName)
        values.put(COLUMN_PRICE, item.itemPrice)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun addUser(user: User) {
        val values = ContentValues()
        values.put(USER_USERNAME, user.userName)
        values.put(USER_PASSWORD, user.password)
        values.put(USER_EMAIL, user.email)
        values.put(USER_PHONE_NUMBER, user.phoneNum)
        values.put(USER_REGION, user.region)
        values.put(USER_STREET, user.street)

        val db = this.writableDatabase
        db.insert(USER_TABLE_NAME, null, values)
        db.close()
    }

    fun updateUser(user: User) {
        val values = ContentValues()
        values.put(USER_USERNAME, user.userName)
        values.put(USER_PASSWORD, user.password)
        values.put(USER_EMAIL, user.email)
        values.put(USER_PHONE_NUMBER, user.phoneNum)
        values.put(USER_REGION, user.region)
        values.put(USER_STREET, user.street)

        val db = this.writableDatabase
        db.update(USER_TABLE_NAME, values, "$COLUMN_ID = ${user.id}", null)
        db.close()
    }

    fun getUser(id: Long): User? {
        val db = this.readableDatabase
        val cursor =
            db.rawQuery("SELECT * FROM $USER_TABLE_NAME WHERE $COLUMN_ID = ${id}", null)

        cursor.use { it ->
            if (it != null && it.count > 0) {
                it.moveToNext()
                val _id = it.getLong(it.getColumnIndex(COLUMN_ID))
                val userName = it.getString(it.getColumnIndex(USER_USERNAME))
                val password = it.getString(it.getColumnIndex(USER_PASSWORD))
                val email = it.getString(it.getColumnIndex(USER_EMAIL))
                val phoneNumber = it.getString(it.getColumnIndex(USER_PHONE_NUMBER))

                val region = it.getString(it.getColumnIndex(USER_REGION))
                val street = it.getString(it.getColumnIndex(USER_STREET))

                return User(_id, userName, password, email, phoneNumber, region, street)
            } else {
                return null
            }
        }
    }

    fun getUser(userName: String): User? {
        val db = this.readableDatabase
        val cursor =
            db.rawQuery(
                "SELECT * FROM $USER_TABLE_NAME WHERE $USER_USERNAME = '$userName'",
                null
            )

        cursor.use { it ->
            if (it != null && it.count > 0) {
                it.moveToNext()
                val id = it.getLong(it.getColumnIndex(COLUMN_ID))
                val _userName = it.getString(it.getColumnIndex(USER_USERNAME))
                val _password = it.getString(it.getColumnIndex(USER_PASSWORD))
                val email = it.getString(it.getColumnIndex(USER_EMAIL))
                val phoneNumber = it.getString(it.getColumnIndex(USER_PHONE_NUMBER))

                val region = it.getString(it.getColumnIndex(USER_REGION))
                val street = it.getString(it.getColumnIndex(USER_STREET))

                return User(id, _userName, _password, email, phoneNumber, region, street)
            } else {
                return null
            }
        }
    }

    fun getAllResturants(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $REST_TABLE_NAME", null)
    }

    fun getAllItems(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun deleteAllItems(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("Delete FROM $TABLE_NAME", null)
    }

    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "myDB.db"
        private const val TABLE_NAME = "name"

        private const val USER_TABLE_NAME = "users"
        private const val USER_USERNAME = "username"
        private const val USER_PASSWORD = "password"
        private const val USER_EMAIL = "email"
        private const val USER_PHONE_NUMBER = "phone_number"
        private const val USER_REGION = "region"
        private const val USER_STREET = "street"

        const val COLUMN_ID = "_id"
        const val COLUMN_NAME = "item_name"
        const val COLUMN_PRICE = "item_price"

        private const val REST_TABLE_NAME = "restaurants"
        const val REST_NAME = "name"
        const val REST_DES = "destination"
        const val REST_LOGO = "logo"


        private const val ITEM_TABLE_NAME = "items"
        const val ITEM_NAME = "name"
        const val ITEM_PRICE = "price"
        const val ITEM_PHOTO = "photo"
        const val ITEM_REST_ID = "restID"


        private const val REST_ITEM_TABLE_NAME = "restaurants_items"
        const val REST_ID = "restID"
        const val ITEM_ID = "itemID"

    }


}