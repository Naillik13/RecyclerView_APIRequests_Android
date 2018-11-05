package iimdemo.killiangalea.com.iimdemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : Fragment() {

    companion object {
        fun create(repo: FullRepo):InfoFragment{
            val fragment = InfoFragment()
            val args = Bundle()
            args.putString("name", repo.name)
            args.putString("avatarUrl", repo.owner.avatar_url)
            args.putString("login", repo.owner.login)
            args.putString("date", repo.created_at)
            args.putString("homepage", repo.homepage)
            args.putString("language", repo.language)
            args.putInt("watchers", repo.watchers)

            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = "Repository : " + arguments!!.getString("name")
        val avatarUrl = arguments!!.getString("avatarUrl")
        val login = "Login : " + arguments!!.getString("login")
        val date = "Date de création : " + arguments!!.getString("date").subSequence(0, 10)
        val homepage = "Lien vers la homepage : " + arguments!!.getString("homepage")
        val language = "Langage(s) utilisé(s) : " + arguments!!.getString("language")
        val watchers = "Nombre de watchers : " + arguments!!.getInt("watchers")



        title.text = name
        loginView.text = login
        dateView.text =  date
        homepageView.text = homepage
        languageView.text = language
        watchersView.text = watchers

        Glide.with(this).load(avatarUrl).into(imageView)
    }
}