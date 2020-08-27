package alfianyabdullah.submission.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module

abstract class GameBaseFragment(private val resLayout: Int) : Fragment() {

    abstract fun modules(): List<Module>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(modules())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(resLayout, container, false)
    }
}