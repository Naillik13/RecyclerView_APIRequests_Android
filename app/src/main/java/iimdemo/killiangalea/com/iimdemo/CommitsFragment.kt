package iimdemo.killiangalea.com.iimdemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_commits.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommitsFragment : Fragment() {

    companion object {
        fun create(repo: FullRepo):CommitsFragment{
            val fragment = CommitsFragment()
            val args = Bundle()
            args.putString("name", repo.name)

            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_commits, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments!!.getString("name")

        // Layout manager du recyclerView
        val lm = LinearLayoutManager(this.context)
        recyclerView.layoutManager = lm
        recyclerView.addItemDecoration(DividerItemDecoration(this.context, lm.orientation))

        // Instanciation du service retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(API::class.java)
        val call = api.getCommits(name)

        // Appel asynchrone
        call.enqueue(object: Callback<List<MyCommit>> {
            override fun onFailure(call: Call<List<MyCommit>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<MyCommit>>, response: Response<List<MyCommit>>) {
                val commits = response.body()!!
                for (commit in  commits){
                    Log.i("Commit", commit.toString())

                    // Envoi des commits à l'adapter
                    recyclerView.adapter = CommitsAdapter(commits)

                }
            }

        })
    }


}