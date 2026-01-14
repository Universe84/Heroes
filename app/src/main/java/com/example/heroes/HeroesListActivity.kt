package com.example.heroes

import android.os.Bundle
import android.util.Log
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
        val TAG = "MainActivity"
    }
    private lateinit var binding: ActivityHeroesListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHeroesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var gson = Gson()
        val inputStream = resources.openRawResource(R.raw.heroes)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val type = object : TypeToken<List<Hero>>() { }.type
        val heroes = gson.fromJson<List<Hero>>(jsonString, type)
        Log.d(TAG, "onCreate: $heroes")

        val customAdapter = HeroAdapter.HeroAdapter(heroes)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView_heroesList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter

        binding.recyclerViewHeroesList.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewHeroesList.adapter

        customAdapter.heroList = customAdapter.heroList.sorted()
        customAdapter.notifyDataSetChanged()


    }
}