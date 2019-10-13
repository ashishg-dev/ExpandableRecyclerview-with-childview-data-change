package com.ashish.expandablerecyclerview

import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashish.expandablerecyclerview.adapter.HeroNameParentAdapter
import com.ashish.expandablerecyclerview.model.HeroDetails
import com.ashish.expandablerecyclerview.model.Songs
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var heroNameParentAdapter: HeroNameParentAdapter
    private lateinit var arrayListHeros: ArrayList<HeroDetails>
    private lateinit var arrayListSongs: ArrayList<Songs>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arrayListHeros = ArrayList()

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView.layoutManager = layoutManager

        heroNameParentAdapter = HeroNameParentAdapter(baseContext, arrayListHeros)
        recyclerView.adapter = heroNameParentAdapter

        heroNameParentAdapter.setOnItemClickListener(object :
            HeroNameParentAdapter.OnParentItemClickListener {

            override fun onItemClicked(parentPosition: Int, childPosition: Int) {
                changeSongsNameDialog(parentPosition, childPosition)
            }

        })

        getAkshayKumarSongs()
        getRanveerSinghSongs()
        getSalmanKhanSongs()
        getVarunDhawanSongs()
        heroNameParentAdapter.notifyDataSetChanged()

    }

    private fun getSalmanKhanSongs() {
        arrayListSongs = ArrayList()
        arrayListSongs.add(Songs("Tere Naam"))
        arrayListSongs.add(Songs("O O Jaane Jaana"))
        arrayListSongs.add(Songs("Selfie Le Le Re"))
        arrayListHeros.add(
            HeroDetails(
                "Salman Khan",
                "https://www.hindustantimes.com/rf/image_size_960x540/HT/p2/2019/03/04/Pictures/_146f44ea-3e38-11e9-92c7-2b8d3185a4e0.jpg",
                arrayListSongs
            )
        )

    }

    private fun getAkshayKumarSongs() {
        arrayListSongs = ArrayList()
        arrayListSongs.add(Songs("Make Some Noise For The Desi Boyz"))
        arrayListSongs.add(Songs("Bhool Bhulaiyaa"))
        arrayListSongs.add(Songs("Hookah Bar"))
        arrayListHeros.add(
            HeroDetails(
                "Akshay Kumar",
                "https://akm-img-a-in.tosshub.com/indiatoday/images/story/201905/AKSHAY1.jpeg?HAKoXX_ba8nMjk4Vo.AOXOxLFBo7E6oY",
                arrayListSongs
            )
        )

    }

    private fun getRanveerSinghSongs() {
        arrayListSongs = ArrayList()
        arrayListSongs.add(Songs("Apna Time Aayega"))
        arrayListSongs.add(Songs("Malhari"))
        arrayListSongs.add(Songs("Ram Chahe Leela"))
        arrayListHeros.add(
            HeroDetails(
                "Ranveer Singh",
                "https://akm-img-a-in.tosshub.com/indiatoday/images/story/201905/ranveer.jpeg?CSQg4p_49LvjlVYNhgDErJhofnNjBE2N",
                arrayListSongs
            )
        )

    }

    private fun getVarunDhawanSongs() {
        arrayListSongs = ArrayList()
        arrayListSongs.add(Songs("Saturday Saturday"))
        arrayListSongs.add(Songs("Lucky Tu Lucky Me"))
        arrayListSongs.add(Songs("Radha"))
        arrayListHeros.add(
            HeroDetails(
                "Varun Dhawan",
                "http://www.gstatic.com/tv/thumb/persons/685931/685931_v9_ba.jpg",
                arrayListSongs
            )
        )

    }

    private fun changeSongsNameDialog(parentPosition: Int, childPosition: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Change Song Name")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton("OK") { dialog, arg1 ->


            arrayListHeros[parentPosition].songsDetails[childPosition].songName =
                input.text.toString()

            heroNameParentAdapter.notifyChildItemChanged(parentPosition, childPosition)

        }

        builder.setNegativeButton("Cancel") { dialog, arg1 ->
            dialog.cancel()
        }

        builder.show()
    }

}
