package com.inour.homeneeds.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.inour.homeneeds.MainActivity
import com.inour.homeneeds.R
import com.inour.homeneeds.adapters.GroupAdapter
import com.inour.homeneeds.adapters.UserAdapter
import com.inour.homeneeds.classes.ItemList
import com.inour.homeneeds.databinding.ActivityGroupsListBinding

class UsersList : AppCompatActivity(), UserAdapter.OnItemClickListener {

    private val db = Firebase.firestore
    lateinit var recyclerView: RecyclerView
    lateinit var users: List<ItemList>
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPref = this.getSharedPreferences(getString(R.string.shared_file), Context.MODE_PRIVATE)
        val group = sharedPref.getString("group","") ?: ""
        title = "Ent Min?"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView = findViewById(R.id.rv_users)
        getUsersNames(group)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return false
    }
    private fun getUsersNames(group: String) {
        db.collection("groups/Users/$group")
            .get()
            .addOnSuccessListener { result ->
                users = result.map { ItemList(it.data.getValue("name") as String, it.data.getValue("name") as String) }
                recyclerView.adapter = UserAdapter(users, this)
                recyclerView.setHasFixedSize(true)
            }
            .addOnFailureListener { exception ->
                Log.w("", "Error getting documents.", exception)
            }
        return
    }

    override fun onItemClick(position: Int) {
        //Toast.makeText(this, "Item $position ahlen", Toast.LENGTH_SHORT).show()
        val clickItem : ItemList = users[position]
        recyclerView.adapter?.notifyItemChanged(position)
        with (sharedPref.edit()) {
            putString("user", clickItem.name)
            apply()
        }
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}