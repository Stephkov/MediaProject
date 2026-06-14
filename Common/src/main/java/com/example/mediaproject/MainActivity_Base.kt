package com.example.mediaproject


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mediaproject.adapter.MediaAdapter
import com.example.mediaproject.databinding.ActivityMainBinding
import com.example.mediaproject.model.MediaItem
import com.example.mediaproject.utilities.Constants
import com.example.mediaproject.utilities.MediaStorage
import com.example.mediaproject.utilities.SignalManager

open class MainActivity_Base : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MediaAdapter
    private lateinit var items: MutableList<MediaItem>

    protected open fun getStorageKey(): String {
        return ""
    }

    protected open fun getMediaTitle(): String {
        return getString(R.string.add_item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainLBLTitle.text = getString(R.string.app_name)
        binding.mainLBLSubtitle.text = getString(R.string.media_tracker)

        loadData()
        setupRecyclerView()
        setupFab()
        updateCounter()
    }

    override fun onRestart() {
        super.onRestart()
        refreshData()
    }

    private fun loadData() {
        items = MediaStorage.loadItems(getStorageKey())
    }

    private fun refreshData() {
        items = MediaStorage.loadItems(getStorageKey())
        adapter.updateItems(items)
        updateCounter()
    }

    private fun setupRecyclerView() {
        adapter = MediaAdapter(items) {
            MediaStorage.saveItems(getStorageKey(), items)
            updateCounter()
            SignalManager.toast(this, "Status updated")
        }

        binding.mainRVMedia.layoutManager = LinearLayoutManager(this)
        binding.mainRVMedia.adapter = adapter
    }

    private fun setupFab() {
        binding.mainFABAdd.setOnClickListener {
            val intent = Intent(this, AddMediaActivity::class.java)

            intent.putExtra(
                Constants.IntentKeys.EXTRA_STORAGE_KEY,
                getStorageKey()
            )

            intent.putExtra(
                Constants.IntentKeys.EXTRA_MEDIA_TYPE,
                getMediaTitle()
            )

            startActivity(intent)
        }
    }
    private fun updateCounter() {
        val watched = items.count {
            it.status == Constants.WatchStatus.WATCHED
        }

        binding.mainLBLCount.text = watched.toString()
    }
}