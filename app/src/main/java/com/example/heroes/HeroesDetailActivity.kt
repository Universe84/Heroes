package com.example.heroes

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.heroes.databinding.ActivityHeroesDetailBinding


class HeroesDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeroesDetailBinding

    companion object{
        val EXTRA_HERO = "hero"
    }

    override fun onCreate(savedInstanceState: Bundle?) {




        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHeroesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hero = intent.getParcelableExtra(EXTRA_HERO) ?: Hero("krill", "krill", "krill", 5, "krill")


        val resourceId = this.resources.getIdentifier(hero.image, "drawable", packageName)
        binding.imageViewHeroesDetailImage.setImageResource(resourceId)
        binding.textViewHeroesDetailDescriptionWritten.setText(hero.description)
        binding.textViewHeroesDetailRankingWritten.setText(hero.ranking.toString())
        binding.textViewHeroesDetailName.setText(hero.name)
        binding.textViewHeroesDetailSuperpowerWritten.setText(hero.superpower)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}