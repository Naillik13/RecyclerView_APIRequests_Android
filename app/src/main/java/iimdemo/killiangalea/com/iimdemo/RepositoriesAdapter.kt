package iimdemo.killiangalea.com.iimdemo

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item.view.*

class RepositoriesAdapter(val repos: List<Repo>) : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>(){


    // Créé un viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repos.count()
    }


    // Lie les éléments du viewHolder à leurs données
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = repos[position]
        holder.bind(repo)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(repo: Repo){
            itemView.textView.text = repo.name

            // Click sur la ligne
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, RepoBottomActivity::class.java)
                intent.putExtra("repoName", repo.name)
                itemView.context.startActivity(intent)
            }

            // Click sur le bouton
            itemView.button.setOnClickListener {
                val intent = Intent(itemView.button.context, RepoActivity::class.java)
                intent.putExtra("html_url", repo.html_url)
                itemView.context.startActivity(intent)
            }

        }

    }


}