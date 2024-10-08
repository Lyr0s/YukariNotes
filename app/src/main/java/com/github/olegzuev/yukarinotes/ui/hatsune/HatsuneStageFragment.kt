package com.github.olegzuev.yukarinotes.ui.hatsune

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.olegzuev.yukarinotes.data.HatsuneStage
import com.github.olegzuev.yukarinotes.databinding.FragmentHatsuneStageBinding
import com.github.olegzuev.yukarinotes.ui.base.ViewType
import com.github.olegzuev.yukarinotes.ui.base.ViewTypeAdapter
import com.github.olegzuev.yukarinotes.ui.shared.SharedViewModelHatsune
import com.github.olegzuev.yukarinotes.ui.shared.SharedViewModelHatsuneFactory

class HatsuneStageFragment : Fragment(), OnHatsuneClickListener<HatsuneStage> {

    private lateinit var binding: FragmentHatsuneStageBinding
    private lateinit var sharedHatsune: SharedViewModelHatsune
    private lateinit var hatsuneStageVM: HatsuneStageViewModel
    private val hatsuneStageAdapter by lazy { ViewTypeAdapter<ViewType<*>>(onItemActionListener = this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedHatsune = ViewModelProvider(requireActivity())[SharedViewModelHatsune::class.java]
        hatsuneStageVM = ViewModelProvider(this, SharedViewModelHatsuneFactory(sharedHatsune))[HatsuneStageViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHatsuneStageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedHatsune.loadData()
        sharedHatsune.hatsuneStageList.observe(viewLifecycleOwner, Observer {
            binding.hatsuneStageProgressBar.visibility = if (it.isEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
            hatsuneStageAdapter.setUpdatedList(hatsuneStageVM.viewList)
        })
        binding.hatsuneStageToolbar.setNavigationOnClickListener {
            it.findNavController().navigateUp()
        }
        with (binding.hatsuneStageRecycler) {
            adapter = hatsuneStageAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onStageClicked(item: HatsuneStage) {
        sharedHatsune.selectedHatsune = item
        findNavController().navigate(HatsuneStageFragmentDirections.actionNavHatsuneStageToNavHatsuneWave())
    }

    override fun onItemClicked(position: Int) {
    }
}
