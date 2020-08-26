package alfianyabdullah.submission.detail

import alfianyabdullah.submission.base.KoinFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.core.module.Module

class GameDetailFragment : KoinFragment() {
    override fun modules() = listOf<Module>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_detail, container, false)
    }

}