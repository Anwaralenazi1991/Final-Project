@file:Suppress("DEPRECATION")

package com.app1

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app1.db.MindOiksDBOpenHelper
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.restaurants_acitvity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantsActivity : AppCompatActivity() {

    private lateinit var dbHandler: MindOiksDBOpenHelper
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurants_acitvity)

        FirebaseMessaging.getInstance().subscribeToTopic("Client")


        listView = findViewById(R.id.myListView)


        dbHandler = MindOiksDBOpenHelper(this, null)

//            val res1 = Model("McDonald's", "Al-malqa branch", "mcdonalds_icon")
//            val res2 = Model("Herfy", "Al-narjis branch", "herfy_icon")
//            val res3 = Model("Kfc", "Al-yasmin branch", "kfc_iconic")
//            val res4 = Model("Pizza hut", "Al-yasmin branch", "hut_iconic")
//            val res5 = Model("Burger King", "Al-yasmin branch", "king_logo")
//            val res6 = Model("Kudu", "Al-yasmin branch", "kudu_logo")
//
//            dbHandler.addResturant(res1)
//            dbHandler.addResturant(res2)
//            dbHandler.addResturant(res3)
//            dbHandler.addResturant(res4)
//            dbHandler.addResturant(res5)
//            dbHandler.addResturant(res6)
//
//            val cursor2 = dbHandler.getAllResturants();
//
//            while (cursor2!!.moveToNext()) {
//                val name = cursor2.getString(cursor2.getColumnIndex(MindOiksDBOpenHelper.REST_NAME))
//                val des = cursor2.getString(cursor2.getColumnIndex(MindOiksDBOpenHelper.REST_DES))
//                val res = cursor2.getString(cursor2.getColumnIndex(MindOiksDBOpenHelper.REST_LOGO))
//
//                val item = Model(name, des, res)
//
//                list.add(item)
//            }
//            cursor2.close()

        if (!updateList()) {
            progressBar.visibility = View.VISIBLE
        }

        Injection.getInstance().menuAPIs.restaurants.enqueue(object : Callback<List<Model>> {
            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful && response.body() != null) {
                    updateList(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Model>>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(this@RestaurantsActivity, t.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        })

    }

    private fun updateList(items: List<Model>? = null): Boolean {
        items?.forEach {
            dbHandler.addResturant(it)
        }

        val cursor = dbHandler.getAllResturants();
        val list = mutableListOf<Model>()

        if (cursor?.count ?: 0 > 0) {

            while (cursor!!.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndex(MindOiksDBOpenHelper.COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.REST_NAME))
                val des = cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.REST_DES))
                val res = cursor.getString(cursor.getColumnIndex(MindOiksDBOpenHelper.REST_LOGO))

                val item = Model(name, des, res, id= id)

                list.add(item)
            }
            cursor.close()

            updateListItems(list)

            return true
        } else {
            return false
        }
    }

    private fun updateListItems(list: MutableList<Model>) {
        listView.adapter = MyListAdapter(this@RestaurantsActivity, R.layout.row, list)

        listView.setOnItemClickListener { _, _, id, _ ->
            val toRestaurantMenuActivity =
                Intent(this@RestaurantsActivity, RestaurantMenu::class.java)
            toRestaurantMenuActivity.putExtra("menu_id", list[id].id.toLong())
            startActivity(toRestaurantMenuActivity)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cart -> {
                val toCartActivity = Intent(this, CartActivity::class.java)
                startActivity(toCartActivity)
                true
            }
            R.id.action_settings -> {
                val toSettingsActivity = Intent(this, SettingsActivity::class.java)
                startActivity(toSettingsActivity)
                true
            }
            R.id.action_logout -> {
                Toast.makeText(applicationContext, "You Logged Out", Toast.LENGTH_LONG).show()

                val prefs = PreferenceManager.getDefaultSharedPreferences(this)
                prefs.edit().remove("userId").commit()

                val toLoginActivity = Intent(this, MainActivity::class.java)
                startActivity(toLoginActivity)

                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
