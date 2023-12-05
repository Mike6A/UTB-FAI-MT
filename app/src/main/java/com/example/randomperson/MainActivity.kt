package com.example.randomperson

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randomperson.databinding.ActivityMainBinding
import com.example.randomperson.model.PersonListModel
import com.example.randomperson.viewmodel.MainViewModel
import com.example.randomperson.viewmodel.PersonDetailViewModel
import com.example.randomperson.viewmodel.ViewModelFactory
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var listAdapter: PersonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //todo supporbar

        val app = application as RandomPersonApplication
        viewModel = ViewModelProvider(this, ViewModelFactory(app.personService))
            .get(MainViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        listAdapter = PersonListAdapter()
        binding.recyclerView.adapter = listAdapter

        listAdapter.setOnClickListener(object : PersonListAdapter.OnClickListener {
            override fun onClick(position: Int, model: PersonListModel) {
                showPerson(model)
            }
        })

        viewModel.list.observe(this, {
                value -> listAdapter.list = value
                listAdapter.notifyDataSetChanged()
        })

        viewModel.loadData()

        binding.newPerson.setOnClickListener {
            startActivity(Intent(this, PersonDetail::class.java).apply {
                putExtra("isNew", true)
            })
        }
    }

    fun showPerson(model: PersonListModel){
        startActivity(Intent(this, PersonDetail::class.java).apply {
            putExtra("openId", model.id)
        })
    }
}