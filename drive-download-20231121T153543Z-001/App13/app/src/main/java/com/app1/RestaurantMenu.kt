@file:Suppress("DEPRECATION")

package com.app1

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app1.db.MindOiksDBOpenHelper
import kotlinx.android.synthetic.main.restaurant_menu.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RestaurantMenu : AppCompatActivity() {

    private lateinit var dbHandler: MindOiksDBOpenHelper
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restaurant_menu)

        val menuId: Long = intent.getLongExtra("menu_id", 0)
//        Log.d("test000121", menuId)

        listView = findViewById(R.id.myListViewMenu)

//        val list = mutableListOf<MenuModel>()

        dbHandler = MindOiksDBOpenHelper(this, null)
//        var list = dbHandler.getITemsForResturant(menuId)

//        if (list == null) {
//            if (menuId == 0L) {
//                dbHandler.addItemForResturant(
//                    MenuModel(
//                        "Chicken Burger",
//                        "16 SAR",
//                        "mac_burger_1"
//                    ), menuId
//                )
//                dbHandler.addItemForResturant(
//                    MenuModel(
//                        "Diet Chicken Burger",
//                        "22 SAR",
//                        "mac_burger_2"
//                    ), menuId
//                )
//                dbHandler.addItemForResturant(
//                    MenuModel(
//                        "Double Burger with Cheese",
//                        "25 SAR",
//                        "mac_burger_3"
//                    ), menuId
//                )
//                dbHandler.addItemForResturant(
//                    MenuModel(
//                        "Burger with Cheese",
//                        "20 SAR",
//                        "mac_burger_4"
//                    ), menuId
//                )
//            } else if (menuId == 1L) {
//                dbHandler.addItemForResturant(
//                    MenuModel("Chicken Burger", "15 SAR", "herfy_menu_1"),
//                    menuId
//                )
//                dbHandler.addItemForResturant(
//                    MenuModel("Kids meal", "10 SAR", "herfy_menu_2"),
//                    menuId
//                )
//                dbHandler.addItemForResturant(
//                    MenuModel(
//                        "Chicken Burger with Cheese",
//                        "25 SAR",
//                        "herfy_menu_3"
//                    ), menuId
//                )
//                dbHandler.addItemForResturant(MenuModel("Fish", "20 SAR", "herfy_menu_4"), menuId)
//                dbHandler.addItemForResturant(
//                    MenuModel(
//                        "Big Chicken With Cheese",
//                        "30 SAR",
//                        "herfy_menu_5"
//                    ), menuId
//                )
//                dbHandler.addItemForResturant(
//                    MenuModel(
//                        "Tortilla Chicken",
//                        "20 SAR",
//                        "herfy_menu_6"
//                    ), menuId
//                )
//                dbHandler.addItemForResturant(MenuModel("Salade", "13 SAR", "herfy_menu_7"), menuId)
//
//            } else if (menuId == 2L) {
//                dbHandler.addItemForResturant(
//                    MenuModel("Chicken Burger", "15 SAR", "kfc_bur"),
//                    menuId
//                )
//                dbHandler.addItemForResturant(MenuModel("Box Chicken", "20 SAR", "kfc_box"), menuId)
//                dbHandler.addItemForResturant(MenuModel("twister", "16 SAR", "kfc_twis"), menuId)
//                dbHandler.addItemForResturant(MenuModel("Pepsi", "1 SAR", "pep"), menuId)
//                dbHandler.addItemForResturant(MenuModel("7Up", "1 SAR", "up"), menuId)
//                dbHandler.addItemForResturant(MenuModel("Water", "1 SAR", "water"), menuId)
//
//            } else if (menuId == 3L) {
//                dbHandler.addItemForResturant(
//                    MenuModel("Marguerita", "15 SAR", "marguerita01"),
//                    menuId
//                )
//                dbHandler.addItemForResturant(
//                    MenuModel(
//                        "tropical hawaiian",
//                        "15 SAR",
//                        "pwahawaian"
//                    ), menuId
//                )
//                dbHandler.addItemForResturant(
//                    MenuModel("Pepperoni", "15 SAR", "pepperoni01"),
//                    menuId
//                )
//                dbHandler.addItemForResturant(
//                    MenuModel("Cheesy Pops", "20 SAR", "cheesy_zft"),
//                    menuId
//                )
//                dbHandler.addItemForResturant(
//                    MenuModel("Chicken Bites", "25 SAR", "cheesy_pops01"),
//                    menuId
//                )
//                dbHandler.addItemForResturant(MenuModel("Pepsi", "1 SAR", "pep"), menuId)
//                dbHandler.addItemForResturant(MenuModel("7Up", "1 SAR", "up"), menuId)
//                dbHandler.addItemForResturant(MenuModel("Water", "1 SAR", "water"), menuId)
//
//            } else if (menuId == 4L) {
//                dbHandler.addItemForResturant(
//                    MenuModel("Fish Royal", "24 SAR", "king_fishroyal"),
//                    menuId
//                )
//                dbHandler.addItemForResturant(
//                    MenuModel(
//                        "Spicy Chicken",
//                        "25 SAR",
//                        "king_spicychken"
//                    ), menuId
//                )
//                dbHandler.addItemForResturant(
//                    MenuModel("Meet Whoober", "26 SAR", "king_whoober"),
//                    menuId
//                )
//                dbHandler.addItemForResturant(MenuModel("Frires", "10 SAR", "king_fries"), menuId)
//                dbHandler.addItemForResturant(MenuModel("Pepsi", "1 SAR", "pep"), menuId)
//                dbHandler.addItemForResturant(MenuModel("7Up", "1 SAR", "up"), menuId)
//                dbHandler.addItemForResturant(MenuModel("Water", "1 SAR", "water"), menuId)
//
//            } else if (menuId == 5L) {
//                dbHandler.addItemForResturant(
//                    MenuModel("Beef Combo", "25 SAR", "kudu_beef_combo"),
//                    menuId
//                )
//                dbHandler.addItemForResturant(
//                    MenuModel(
//                        "Chicken Combo",
//                        "23 SAR",
//                        "kudu_chicken_combo"
//                    ), menuId
//                )
//                dbHandler.addItemForResturant(
//                    MenuModel(
//                        "Lafino Chicken",
//                        "20 SAR",
//                        "kudu_lafino_chicken"
//                    ), menuId
//                )
//                dbHandler.addItemForResturant(MenuModel("Pepsi", "1 SAR", "pep"), menuId)
//                dbHandler.addItemForResturant(MenuModel("7Up", "1 SAR", "up"), menuId)
//                dbHandler.addItemForResturant(MenuModel("Water", "1 SAR", "water"), menuId)
//            }
//
//
//            list = dbHandler.getITemsForResturant(menuId)
//
//        }

        if (!updateList(menuId)) {
            progressBar.visibility = View.VISIBLE
        }

        Injection.getInstance().menuAPIs.getItems(menuId).enqueue(
            object : Callback<List<MenuModel>> {
                override fun onResponse(
                    call: Call<List<MenuModel>>,
                    response: Response<List<MenuModel>>
                ) {
                    progressBar.visibility = View.GONE
                    if (response.isSuccessful && response.body() != null) {
                        updateList(menuId, response.body()!!)
                    }
                }

                override fun onFailure(call: Call<List<MenuModel>>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this@RestaurantMenu, t.localizedMessage, Toast.LENGTH_SHORT)
                        .show()
                }

            });

        listView.setOnItemClickListener { _, _, position, id ->
            Log.d("test0000", position.toString())
            Log.d("test00001", id.toString())
        }


    }

    private fun updateList(id: Long, items: List<MenuModel>? = null): Boolean {
        items?.forEach {
            dbHandler.addItemForResturant(it, id)
        }

//        val cursor = dbHandler.getAllItems()
        var list = dbHandler.getITemsForResturant(id)

//        val list = mutableListOf<Model>()

        if (!list.isNullOrEmpty()) {


//            cursor?.close()

            updateListItems(list.toMutableList())
            return true
        } else {
            return false
        }
    }

    private fun updateListItems(list: MutableList<MenuModel>) {
        listView.adapter = MyMenuListAdapter(
            this@RestaurantMenu,
            R.layout.menu_row, list
        )
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
                val toSettingActivity = Intent(this, SettingsActivity::class.java)
                startActivity(toSettingActivity)
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
