package com.reusable.uicomponents.accordian

import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.reusable.uicomponents.databinding.AccordianViewBinding

/**
 * Adapter that handles the data that needs to be populate parent and child.
 */
class ExpandableListAdapter(private val parentTitleList:List<String>)
    : BaseExpandableListAdapter() {

        private lateinit var accordianListeners:AccordianListeners
        var lastExpandedPosition = -1

    /**
     * Listener that needs to be set get callback into client side.
     */
    fun setListener(accordianListeners:AccordianListeners):ExpandableListAdapter{
        this.accordianListeners = accordianListeners
        return  this
    }

    override fun getChild(position: Int, expandedListposition: Int): Any {
        return parentTitleList[position].get(expandedListposition)
    }

    override fun getChildId(position: Int, expandedListposition: Int): Long {
       return expandedListposition.toLong()
    }

    override fun getGroup(position: Int): Any {
        return parentTitleList[position]
    }

    override fun getChildrenCount(position: Int): Int {
        return 1
    }

    override fun getGroupCount(): Int {
        return  parentTitleList.size
    }

    override fun getGroupId(position: Int): Long {
        return  position.toLong()
    }

    override fun getGroupType(groupPosition: Int): Int {
        return super.getGroupType(groupPosition)
    }

    override fun getChildType(groupPosition: Int, childPosition: Int): Int {
        return super.getChildType(groupPosition, childPosition)
    }

    override fun getGroupView(position: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val binding =  accordianListeners.populateParentView(
            position,
            isExpanded,
            convertView,
            parent
        )
        val listTitle = getGroup(position) as String
        binding.listTitle.setTypeface(null, Typeface.BOLD)
        binding.listTitle.text = listTitle
        if(position == 0){
            binding.parentDividerShadow.visibility = View.GONE
        }
        binding.executePendingBindings()
        return binding.root
    }

    override fun getChildView(position: Int, expandedListPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View? {
       return  accordianListeners.populateChildView(
           position,
           expandedListPosition,
           isLastChild,
           convertView,
           parent
       )
    }

    override fun onGroupExpanded(groupPosition: Int) {
        super.onGroupExpanded(groupPosition)
        for (i in 0 until this.groupCount){
            if( i!= groupPosition) {
                accordianListeners.onGroupExpandedCollapse(i)
            }
        }
    }

    override fun onGroupCollapsed(groupPosition: Int) {
        super.onGroupCollapsed(groupPosition)
    }

    override fun getCombinedChildId(groupId: Long, childId: Long): Long {
        return super.getCombinedChildId(groupId, childId)
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun isChildSelectable(position: Int, p1: Int): Boolean {
        return true
    }
}

public interface AccordianListeners {
    /**
     * Method to populate parent view and it will be implemented from Client app.
     * @param listPosition[Int] position of the selected parent view
     * @param isExpanded[Boolean] identifies if view is already expanded or not.
     * @param convertView[View] view to returned while preparing parent adapter.
     * @param parent[ViewGroup] on which view each item will inflated
     */
    fun populateParentView(
        listPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ):AccordianViewBinding

    /**
     * Method to populate child view and it will be implemented from Client app.
     * @param listPosition[Int] position of the selected child view.
     * @param expandedListPosition[Boolean] identifies if view is already expanded or not.
     * @param isLastChild[Boolean] identifies if view is last child view.
     * @param convertView[View] view to returned while preparing parent adapter.
     * @param parent[ViewGroup] on which view each item will inflated.
     */
    fun populateChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ):View?

    /**
     * Method that handles the when view is collapsed.
     * @param groupPosition[Int] Position of the view collapsed.
     */
    fun onGroupExpandedCollapse(groupPosition: Int)
}