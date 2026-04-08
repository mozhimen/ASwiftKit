package com.mozhimen.uik.databinding.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mozhimen.uik.databinding.test.databinding.FragmentMainBinding
import com.mozhimen.uik.databinding.utils.viewBinding

/**
 * @ClassName MainFragment
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/10/6 22:04
 * @Version 1.0
 */
class MainFragment : Fragment() {
    private val vb: FragmentMainBinding by viewBinding()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return vb.root
    }
}