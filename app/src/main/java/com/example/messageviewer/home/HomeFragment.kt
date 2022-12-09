package com.example.messageviewer.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.messageviewer.App
import com.example.messageviewer.R
import com.example.messageviewer.common.injectViewModel
import com.example.messageviewer.data.api.model.Message
import com.example.messageviewer.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class HomeFragment : Fragment(), RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var adapter: HomeAdapter = HomeAdapter()
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.getComponent().inject(this)
        viewModel = injectViewModel(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadAllMessages()

        viewModel.messagesLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerView.adapter = adapter

        val itemTouchHelperCallback: ItemTouchHelper.SimpleCallback =
            RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        val itemTouchHelperToRightCallback: ItemTouchHelper.SimpleCallback =
            RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT, this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.recyclerView)
        ItemTouchHelper(itemTouchHelperToRightCallback).attachToRecyclerView(binding.recyclerView)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        if (viewHolder is HomeAdapter.ViewHolder) {
            val id: Long = adapter.currentList[viewHolder.adapterPosition].id
            val deletedItem: Message = adapter.currentList[viewHolder.adapterPosition]
            val deletedIndex = viewHolder.adapterPosition
            adapter.removeItem(viewHolder.adapterPosition)
            Snackbar.make(binding.root, resources.getString(R.string.message_removed, id), Snackbar.LENGTH_LONG)
                .setAction("UNDO") {
                    adapter.restoreItem(deletedItem, deletedIndex)
                }
                .setActionTextColor(Color.WHITE)
                .show()
        }
    }
}