package com.features.test_app_imagesearch.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.features.test_app_imagesearch.R
import com.features.test_app_imagesearch.app.App
import com.features.test_app_imagesearch.app.AppComponent
import com.features.test_app_imagesearch.app.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {


    @Inject
    lateinit var factory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appComponent.inject(this)
        this.viewModel = ViewModelProvider(viewModelStore, this.factory).get(MainViewModel::class.java)



//        rv_main.adapter = adapter
        rv_main.layoutManager = LinearLayoutManager(this)

        tiet_main_search.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideSoftKeyboard(this, tiet_main_search)
                viewModel.searchImage(tiet_main_search.text.toString())
            }
            return@setOnEditorActionListener false
        }

        this.viewModel.apply {
            //TODO bind loaded image if null show error dialog
        }
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(this)
            .setNeutralButton("OK") {dialog, i ->
                dialog.dismiss()
            }.setMessage("Cannot find image")
            .create().show()
    }
}