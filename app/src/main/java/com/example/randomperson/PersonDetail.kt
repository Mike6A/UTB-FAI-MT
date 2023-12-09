package com.example.randomperson

import android.content.ClipData
import android.content.ClipboardManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.randomperson.databinding.ActivityPersonDetailBinding
import com.example.randomperson.viewmodel.PersonDetailViewModel
import com.example.randomperson.viewmodel.ViewModelFactory

class PersonDetail : AppCompatActivity() {

    private lateinit var binding: ActivityPersonDetailBinding
    private lateinit var viewModel: PersonDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPersonDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.myToolbar)

        // showing the back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val app = application as RandomPersonApplication
        viewModel = ViewModelProvider(this, ViewModelFactory(app.personService))
            .get(PersonDetailViewModel::class.java)

        var isNew = intent.getBooleanExtra("isNew", false)
        var openId = intent.getIntExtra("openId", 0)

        if (isNew) {
            viewModel.openWithNewPerson()
        }

        if (openId != 0) {
            viewModel.openPerson(openId)
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.closeActivity.observe(this) { value -> if (value) finish() }

        viewModel.copyToClipboard.observe(this) { value ->
            if (!value.isNullOrEmpty()) {
                val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Person data", value)
                clipboard.setPrimaryClip(clip)

                val toast = Toast.makeText(this, "Copied to clipboard!", Toast.LENGTH_SHORT)
                toast.show()

                viewModel.copyToClipboard.value = ""
            }
        }
    }

}