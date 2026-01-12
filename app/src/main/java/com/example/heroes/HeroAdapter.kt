package com.example.heroes

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class HeroAdapter {
    class HeroAdapter(var heroList: List<Hero>) :
        RecyclerView.Adapter<HeroAdapter.ViewHolder>() {

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder)
         */
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textViewRank : TextView
            val textViewDesc : TextView
            val textViewName : TextView
            val layout: ConstraintLayout
            init {
                // Define click listener for the ViewHolder's View
                textViewRank = view.findViewById(R.id.textView_itemHero_rank)
                textViewDesc = view.findViewById(R.id.textView_itemHero_desc)
                textViewName = view.findViewById(R.id.textView_itemHero_name)
                layout = view.findViewById(R.id.layout_heroItem)
            }
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_hero, viewGroup, false)

            return ViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

            // Get element from your heroList at this position and replace the
            // contents of the view with that element
            val hero = heroList[position]
            val context = viewHolder.layout.context

            viewHolder.textViewRank.text = hero.ranking.toString()
            viewHolder.textViewDesc.text = hero.description
            viewHolder.textViewName.text = hero.name
            viewHolder.layout.setOnClickListener {
                val intent = Intent(context, HeroesDetailActivity::class.java)
                Toast.makeText(context, "${hero.name} clicked!", Toast.LENGTH_SHORT).show()
                context.startActivity(intent)
            }
            //if need onClick, put here
        }

        // Return the size of your heroList (invoked by the layout manager)
        override fun getItemCount() = heroList.size

    }
}