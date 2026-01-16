package com.example.heroes

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import com.example.heroes.databinding.ActivityHeroesListBinding

class HeroesListActivity : AppCompatActivity() {

    companion object {
        val TAG = "HeroesListActivity"
    }
    private lateinit var binding: ActivityHeroesListBinding
    private lateinit var customAdapter: HeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHeroesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var gson = Gson()
        val inputStream = resources.openRawResource(R.raw.heroes)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        val type = object : TypeToken<List<Hero>>() { }.type
        val heroes = gson.fromJson<List<Hero>>(jsonString, type)
        Log.d(TAG, "onCreate: $heroes")

        var customAdapter = HeroAdapter(heroes)
        binding.recyclerViewHeroesList.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewHeroesList.adapter = customAdapter

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView_heroesList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter



        customAdapter.heroList = customAdapter.heroList.sortedWith(compareBy({it.ranking}, {it.name}))
        customAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.sorter, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection.
        return when (item.itemId) {
            R.id.item_listOptions_sortByName -> {
                customAdapter.heroList = customAdapter.heroList.sortedBy {it.name}
                customAdapter.notifyDataSetChanged()
                true
            }
            R.id.item_listOptions_sortByRank -> {
                customAdapter.heroList = customAdapter.heroList.sorted()
                customAdapter.notifyDataSetChanged()
                true
            }
            R.id.item_listOptions_sortByDescription -> {
                customAdapter.heroList = customAdapter.heroList.sortedWith (compareBy({it.description.length}, {it.ranking}, {it.name}))
                customAdapter.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}