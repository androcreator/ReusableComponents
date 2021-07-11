package com.reusable.components

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.reusable.components.databinding.ActivityMainBinding
import com.reusable.uicomponents.accordian.AccordianListeners
import com.reusable.uicomponents.accordian.ExpandableListAdapter
import com.reusable.uicomponents.databinding.AccordianViewBinding

class MainActivity<VB : ViewDataBinding> : BaseActivity<ActivityMainBinding, BaseViewModel>(), AccordianListeners {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): BaseViewModel = BaseViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        populateAdapter()
    }

    fun populateAdapter(){
        val list = dummydata()
        val parentList = ArrayList<String>(list.keys)
        val adapter = ExpandableListAdapter(parentList).setListener(this)
        viewBinding?.let {
            it.listExpandablelistView.setAdapter(adapter)
            it.listExpandablelistView.setExpanded(true)
        }
    }

    fun dummydata(): HashMap<String, List<String>> {
        val list = LinkedHashMap<String, List<String>>()
        list["one"] = emptyList()
        list["two"] = emptyList()
        list["three"] = emptyList()
        return list
    }

    override fun populateParentView(
        listPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): AccordianViewBinding {
        val bindingView = viewBinding?.listExpandablelistView?.getBinding() ?: AccordianViewBinding.inflate(layoutInflater)
        bindingView.imgAccordian.setBackgroundResource(android.R.drawable.arrow_down_float)
        if(isExpanded){
            bindingView.imgAccordian.setBackgroundResource(android.R.drawable.arrow_up_float)
        }
        return bindingView
    }

    override fun populateChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        var view: View? = null
        val layoutInflater = parent?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        when(listPosition){
            0 -> {
                if(view == null){
                    view = layoutInflater.inflate(R.layout.view_accordian_child,parent,false)
                }
            }
            1 -> {
                if(view == null){
                    view = layoutInflater.inflate(R.layout.view_accordian_child,parent,false)
                }
            }
            2 -> {
                if(view == null){
                    view = layoutInflater.inflate(R.layout.view_accordian_child,parent,false)
                }
            }
        }
        return view
    }

    override fun onGroupExpandedCollapse(groupPosition: Int) {
       viewBinding?.listExpandablelistView?.collapseGroup(groupPosition)
    }

    override fun getBindingVariables(): Int {
       return BR.viewModel
    }
}
