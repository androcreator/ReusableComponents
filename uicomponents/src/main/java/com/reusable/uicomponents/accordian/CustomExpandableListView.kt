package com.reusable.uicomponents.accordian

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ExpandableListView
import com.reusable.uicomponents.BR
import com.reusable.uicomponents.R

import com.reusable.uicomponents.databinding.AccordianViewBinding

/**
 * This class is custom expandable list view which
 * can adjust its height of List view when more items are added to child view
 */
class CustomExpandableListView : ExpandableListView {

    private var expanded: Boolean = false
    lateinit var bindingView: AccordianViewBinding
    lateinit var typedArray: TypedArray
    var titleColor: String? = null
    var titleFontStyle: String? = null
    var titleFontSize: Float? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attributeSet: AttributeSet?) : super(context,attributeSet){
        init(attributeSet)
    }
    constructor(context: Context?, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr){
        init(attributeSet)
    }
    constructor(context: Context?, attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attributeSet, defStyleAttr, defStyleRes){
        init(attributeSet)
    }

    fun init(attributeSet: AttributeSet?){
        bindingView = getBinding()
        if(attributeSet != null){
            typedArray =  context.obtainStyledAttributes(attributeSet, R.styleable.accordianView)
            titleColor = typedArray.getString(R.styleable.accordianView_titleColor)
            titleFontSize = typedArray.getFloat(R.styleable.accordianView_titleFontSize, 0.0f)
            titleFontStyle = typedArray.getString(R.styleable.accordianView_titleFontStyle)
            typedArray.recycle()
        }
    }

    /**
     * This method return the binding view, so that we can access all the element of the view from client app.
     */
    fun getBinding(): AccordianViewBinding{
        val layoutInflater = LayoutInflater.from(context)
        bindingView = AccordianViewBinding.inflate(layoutInflater, this, false)
        titleColor?.let {
            bindingView.setVariable(BR.textColor, it)
        }

        titleFontStyle?.let {
            bindingView.setVariable(BR.textfont, it)
        }

        titleFontSize?.let {
            bindingView.setVariable(BR.textSize, it)
        }

        return bindingView
    }

    fun isExpanded():Boolean {
        return expanded
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if(isExpanded()){
            val expandSpec = MeasureSpec.makeMeasureSpec(View.MEASURED_SIZE_MASK shr 2, MeasureSpec.AT_MOST)
            super.onMeasure(widthMeasureSpec, expandSpec)
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    fun setExpanded(expanded: Boolean){
        this.expanded = expanded
    }

}