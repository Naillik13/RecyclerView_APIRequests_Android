package iimdemo.killiangalea.com.iimdemo

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_repo_bottom.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepoBottomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_bottom)

        val repoName = intent.getStringExtra("repoName")
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(API::class.java)
        val call = api.getFullRepo(repoName)

        // Appel asynchrone
        call.enqueue(object: Callback<FullRepo> {
            override fun onFailure(call: Call<FullRepo>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<FullRepo>, response: Response<FullRepo>) {
                val repo = response.body()!!
                Log.i("repo", repo.toString())


                supportFragmentManager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.frame, InfoFragment.create(repo))
                    .commit()

                navigation.setOnNavigationItemSelectedListener{item ->
                    when (item.itemId) {
                        R.id.navigation_info -> {

                            supportFragmentManager
                                .beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .replace(R.id.frame, InfoFragment.create(repo))
                                .commit()
                        }
                        R.id.navigation_commits -> {

                            supportFragmentManager
                                .beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .replace(R.id.frame, CommitsFragment.create(repo))
                                .commit()
                        }
                    }
                    true
                }


            }

        })
    }
}
