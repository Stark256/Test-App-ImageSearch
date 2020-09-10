package com.features.test_app_imagesearch.app.ui

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.features.test_app_imagesearch.R
import com.features.test_app_imagesearch.app.base.BaseActivity
import com.features.test_app_imagesearch.db.models.ImageDBModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {


    @Inject
    lateinit var factory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent.inject(this)
        this.viewModel = ViewModelProvider(viewModelStore, this.factory).get(MainViewModel::class.java)

        this.adapter = MainAdapter(object : MainAdapter.AdapterCallback{
            override fun onImageLoaded(byteArray: ByteArray) {
                viewModel.saveImage(byteArray)
            }
        })

        rv_main.adapter = adapter
        rv_main.layoutManager = LinearLayoutManager(this)

        tiet_main_search.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideSoftKeyboard(this, tiet_main_search)
                viewModel.searchImage(tiet_main_search.text.toString()) {
                    showErrorDialog()
                }
            }
            return@setOnEditorActionListener false
        }

        this.viewModel.apply {
            savedImages.observe(this@MainActivity, Observer<ArrayList<ImageDBModel>>{
                adapter.replaceAll(it)
            })
            addNewImage.observe(this@MainActivity, Observer<ImageDBModel> {
                adapter.addSingleObject(it)
            })
            loadAllImages()
        }
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(this)
            .setPositiveButton("OK") {dialog, i ->
                dialog.dismiss()
            }.setMessage("Cannot find image")
            .create().show()
    }

}