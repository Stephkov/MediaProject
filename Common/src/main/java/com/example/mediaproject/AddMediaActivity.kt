package com.example.mediaproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mediaproject.databinding.ActivityAddMediaBinding
import com.example.mediaproject.model.MediaItem
import com.example.mediaproject.utilities.Constants
import com.example.mediaproject.utilities.Constants.IntentKeys.EXTRA_MEDIA_TYPE
import com.example.mediaproject.utilities.Constants.IntentKeys.EXTRA_STORAGE_KEY
import com.example.mediaproject.utilities.MediaStorage
import com.example.mediaproject.utilities.SignalManager

class AddMediaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMediaBinding
    private lateinit var storageKey: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddMediaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storageKey = intent.getStringExtra(EXTRA_STORAGE_KEY) ?: ""

        binding.addLBLTitle.text =
            intent.getStringExtra(EXTRA_MEDIA_TYPE) ?: getString(R.string.add_item)

        setupButtons()
    }

    private fun setupButtons() {
        binding.addBTNSave.setOnClickListener {
            saveItem()
        }

        binding.addBTNCancel.setOnClickListener {
            finish()
        }
    }

    private fun saveItem() {
        val title = binding.addEDTTitle.text.toString().trim()
        val genre = binding.addEDTGenre.text.toString().trim()

        if (title.isEmpty() || genre.isEmpty()) {
            SignalManager.toast(this, "Please enter title and genre")
            return
        }

        val item = MediaItem.Builder()
            .id(System.currentTimeMillis().toInt())
            .title(title)
            .genre(genre)
            .status(Constants.WatchStatus.NOT_WATCHED)
            .build()

        val items = MediaStorage.loadItems(storageKey)
        items.add(item)
        MediaStorage.saveItems(storageKey, items)

        SignalManager.toast(this, "Item added")
        finish()
    }
}