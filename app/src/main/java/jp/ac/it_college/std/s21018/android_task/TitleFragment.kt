package jp.ac.it_college.std.s21018.android_task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import jp.ac.it_college.std.s21018.android_task.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {
    private var _binding: FragmentTitleBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTitleBinding.inflate(inflater, container, false)

        //STARTボタンタップで世代選択画面に遷移
        binding.btStart.setOnClickListener {
            Navigation.findNavController(it).navigate(
                TitleFragment.actionTitleFragmentToGenSelectFragment().apply {

                }
            )
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}