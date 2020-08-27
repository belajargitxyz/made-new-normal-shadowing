package alfianyabdullah.submission.base

import alfianyabdullah.submission.core.domain.model.Game
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module

abstract class GameBaseFragment(private val resLayout: Int) : Fragment() {

    lateinit var gameBundle: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            gameBundle = it.getParcelable<Game>("GAME") as Game
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(resLayout, container, false)
    }
}