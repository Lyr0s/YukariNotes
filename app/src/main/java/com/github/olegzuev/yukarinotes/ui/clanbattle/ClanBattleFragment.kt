package com.github.olegzuev.yukarinotes.ui.clanbattle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.olegzuev.yukarinotes.R
import com.github.olegzuev.yukarinotes.databinding.FragmentClanBattleBinding
import com.github.olegzuev.yukarinotes.ui.shared.SharedViewModelClanBattle

class ClanBattleFragment : Fragment() {

    private lateinit var sharedClanBattle: SharedViewModelClanBattle
    private lateinit var adapter: ClanBattleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedClanBattle = ViewModelProvider(requireActivity())[SharedViewModelClanBattle::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding =
            DataBindingUtil.inflate<FragmentClanBattleBinding>(
                inflater, R.layout.fragment_clan_battle, container, false
            ).apply{
                lifecycleOwner = viewLifecycleOwner
                adapter = ClanBattleAdapter(requireContext(), sharedClanBattle)
                clanBattleListRecycler.layoutManager = LinearLayoutManager(context)
                clanBattleListRecycler.adapter = adapter
                clanBattleListRecycler.setHasFixedSize(true)
            }

        sharedClanBattle.apply {
            periodList.observe(
                viewLifecycleOwner, Observer{
                    adapter.update(it)
                }
            )
            loadingFlag.observe(
                viewLifecycleOwner, Observer {
                    if (it) binding.clanBattleProgressBar.visibility = View.VISIBLE
                    else binding.clanBattleProgressBar.visibility = View.GONE
                }
            )
        }
        sharedClanBattle.loadData()
        return binding.root
    }
}