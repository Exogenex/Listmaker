package com.exogenex.listmaker.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exogenex.listmaker.MainActivity
import com.exogenex.listmaker.R
import com.exogenex.listmaker.models.TaskList
import com.exogenex.listmaker.ui.detail.ui.detail.ListDetailFragment

class ListDetailActivity : AppCompatActivity() {

    lateinit var list: TaskList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_detail_activity)

        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!
        title = list.name

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, ListDetailFragment.newInstance()).commitNow()
        }
    }
}