package iimdemo.killiangalea.com.iimdemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_commits.view.*

class CommitsAdapter(val commits: List<MyCommit>) : RecyclerView.Adapter<CommitsAdapter.ViewHolder>(){


    // Créé un viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_commits, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return commits.count()
    }


    // Lie les éléments du viewHolder à leurs données
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val commit = commits[position]
        holder.bind(commit)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(commit: MyCommit){
            itemView.textView.text = commit.commit.author.name
            itemView.messageView.text = commit.commit.message
        }

    }


}