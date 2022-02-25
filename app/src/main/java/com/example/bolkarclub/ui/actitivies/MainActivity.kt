package com.example.bolkarclub.ui.actitivies

import android.app.Activity
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.bolkarclub.R
import com.example.bolkarclub.model.DataModel
import com.example.bolkarclub.ui.fragments.MainFragment
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {
    lateinit var tvMainMember: TextView
    lateinit var ivBack: ImageView


    lateinit var ivProfile: CircleImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvMainMember = findViewById(R.id.tvMainMembers)
        ivProfile = findViewById(R.id.profile_image)
        ivBack = findViewById(R.id.ivBack)


        ivBack.setOnClickListener {
            super.onBackPressed()
        }


        val mainFragment = MainFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, mainFragment)
            .commit()

    }

    fun fragmentMethod(dataModel: Int, dataHostModel: DataModel?) {
        tvMainMember.text = dataModel.toString()
        Glide.with(applicationContext).load(dataHostModel?.profileImg)
            .placeholder(R.drawable.user).into(ivProfile)


    }
}