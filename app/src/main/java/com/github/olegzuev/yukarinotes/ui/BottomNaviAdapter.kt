package com.github.olegzuev.yukarinotes.ui

import androidx.fragment.app.Fragment
import com.github.olegzuev.yukarinotes.ui.charalist.CharaListFragment
import com.github.olegzuev.yukarinotes.ui.clanbattle.ClanBattleFragment
import com.github.olegzuev.yukarinotes.ui.drop.DropFragment
import com.github.olegzuev.yukarinotes.ui.menu.MenuFragment

const val CHARA_INDEX = 0
const val DROP_INDEX = 1
const val CLAN_BATTLE_INDEX = 2
const val MENU_INDEX = 3

class BottomNaviAdapter(val mFragment: Fragment) {
    private val tabFragmentCreator: Map<Int, () -> Fragment> = mapOf(
        CHARA_INDEX to { CharaListFragment() },
        DROP_INDEX to { DropFragment() },
        CLAN_BATTLE_INDEX to { ClanBattleFragment() },
        MENU_INDEX to { MenuFragment() }
    )

    fun getItemCount() = tabFragmentCreator.size

    fun createFragment(index: Int): Fragment {
        return tabFragmentCreator[index]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}