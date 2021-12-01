package com.hyrule.app

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyrule.app.data.HyruleEntity
import com.hyrule.app.databinding.MainFragmentBinding

class MainFragment : Fragment(), HyruleListAdapter.HyruleEntityListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: HyruleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(true)

        requireActivity().title = getString(R.string.app_name)

        binding = MainFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        with(binding.recyclerView) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context, LinearLayoutManager(context).orientation
            )
            addItemDecoration(divider)
        }

        viewModel.hyruleList?.observe(viewLifecycleOwner, Observer {
            Log.i("hyruleEntityLogging", it.toString())
            adapter = HyruleListAdapter(it, this@MainFragment)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)

            val selectedEntities =
                savedInstanceState?.getParcelableArrayList<HyruleEntity>(SELECTED_ENTITIES_KEY)
            adapter.selectedEntities.addAll(selectedEntities ?: emptyList())
        })

        binding.floatingActionButton2.setOnClickListener {
            editEntity(NEW_HYRULE_ID)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val menuId =
            if (this::adapter.isInitialized &&
                adapter.selectedEntities.isNotEmpty()
            ) {
                R.menu.menu_main_selected_items
            } else {
                R.menu.menu_main
            }
        inflater.inflate(menuId, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sample_data -> addSampleData()
            R.id.action_delete -> deleteSelectedEntities()
            R.id.action_delete_all -> deleteAllEntites()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteAllEntites(): Boolean {
        viewModel.deleteAllEntities()
        return true
    }


    private fun deleteSelectedEntities(): Boolean {
        viewModel.deleteEntities(adapter.selectedEntities)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                adapter.selectedEntities.clear()
                requireActivity().invalidateOptionsMenu()
            }, 100
        )
        return true
    }

    private fun addSampleData(): Boolean {
        viewModel.addSampleData()
        return true
    }

    override fun editEntity(entityId: Int) {
        Log.i(TAG, "onEntityClick: received entity id $entityId")
        val action = MainFragmentDirections.actionEditEntity(entityId)
        findNavController().navigate(action)
    }

    override fun onItemSelectionChanged() {
        requireActivity().invalidateOptionsMenu()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (this::adapter.isInitialized) {
            outState.putParcelableArrayList(SELECTED_ENTITIES_KEY, adapter.selectedEntities)
        }
        super.onSaveInstanceState(outState)
    }

}