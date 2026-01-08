package com.example.heroes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

            init {
                // Define click listener for the ViewHolder's View
                textViewRank = view.findViewById(R.id.textView_itemHero_rank)
                textViewDesc = view.findViewById(R.id.textView_itemHero_desc)
                textViewName = view.findViewById(R.id.textView_itemHero_name)
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

            viewHolder.textViewRank.text = hero.ranking.toString()
            viewHolder.textViewDesc.text = hero.description
            viewHolder.textViewName.text = hero.name

            //if need onClick, put here
        }

        // Return the size of your heroList (invoked by the layout manager)
        override fun getItemCount() = heroList.size

    }
}