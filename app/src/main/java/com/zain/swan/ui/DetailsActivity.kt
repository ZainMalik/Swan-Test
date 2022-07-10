package com.zain.swan.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zain.swan.R
import com.zain.swan.util.Constants
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val image = intent.getStringExtra("image")
        val name = intent.getStringExtra("name")
        val overview = intent.getStringExtra("overview")
        val date = intent.getStringExtra("date")
//        Toast.makeText(this, url, Toast.LENGTH_SHORT).show()

        tv_title.text = name
        tv_explanation.text = overview
        tv_date.text = date
        Glide.with(this).load(Constants.IMAGE_BASE_URL + image)
            .apply(RequestOptions().centerCrop())
            .into(iv_item)
    }

}