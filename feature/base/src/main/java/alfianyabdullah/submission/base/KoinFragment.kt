package alfianyabdullah.submission.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module

abstract class KoinFragment : Fragment() {
    abstract fun modules(): List<Module>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(modules())
    }
}