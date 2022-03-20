package com.hyunjine.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.hyunjine.myapplication.R
import com.hyunjine.myapplication.data.repo.Repository
import com.hyunjine.myapplication.data.retrofit.API
import com.hyunjine.myapplication.databinding.ActivityMainBinding
import com.hyunjine.myapplication.presenter.MainContract
import com.hyunjine.myapplication.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding

    private val presenter: MainContract.Presenter by lazy { MainPresenter(this, Repository(API().server)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener { presenter.getAnyCampingSiteInfo() }
    }

    override fun onLoadCampingSiteInfo(name: String, firstImageUrl: String) {
        binding.txt.text = name

        Glide.with(this@MainActivity)
            .load(firstImageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .fallback(R.drawable.ic_launcher_foreground)
            .into(binding.img)
    }

    override fun onFailLoad(errorMsg: String) {
        binding.txt.text = errorMsg
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}