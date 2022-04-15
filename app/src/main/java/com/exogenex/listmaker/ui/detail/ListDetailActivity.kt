package com.exogenex.listmaker.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.exogenex.listmaker.MainActivity
import com.exogenex.listmaker.R
import com.exogenex.listmaker.databinding.ListDetailActivityBinding
import com.exogenex.listmaker.ui.detail.ui.detail.ListDetailFragment
import com.exogenex.listmaker.ui.detail.ui.detail.ListDetailViewModel

class ListDetailActivity : AppCompatActivity() {

    lateinit var binding: ListDetailActivityBinding
    lateinit var viewModel: ListDetailViewModel
    lateinit var fragment: ListDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListDetailActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(ListDetailViewModel::class.java)
        viewModel.list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!

        binding.addTaskButton.setOnClickListener { showCreateTaskDialog() }

        title = viewModel.list.name

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.detail_container, ListDetailFragment.newInstance()).commitNow()
        }
    }

    private fun showCreateTaskDialog() {
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this)
            .setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task) { dialog, _ ->
                val task = taskEditText.text.toString()
                viewModel.addTask(task)
                dialog.dismiss()
            }
            .create()
            .show()
    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(MainActivity.INTENT_LIST_KEY, viewModel.list)
        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }
}