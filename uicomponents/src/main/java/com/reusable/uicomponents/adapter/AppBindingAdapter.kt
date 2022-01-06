package com.reusable.uicomponents.adapter

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.reusable.uicomponents.R
import com.reusable.uicomponents.enums.FontStyle
import java.lang.Exception

/**
 * Binder class that helps to bind the properties from this class.
 */
class AppBindingAdapter {
    companion object{
        @BindingAdapter("bind_background", requireAll = true)
        @JvmStatic
        fun setBackground(view: View, background: String){
            view.setBackgroundColor(Color.BLACK)
        }

        @BindingAdapter(
            "bind_textcolor",
            requireAll = true
        )
        @JvmStatic
        fun setTextColor(textView: TextView,
                         textColor: String?){
            textColor?.let {
                textView.setTextColor(parse(it))
            }
//TODO: To be taken care
//           makeUnderline?.let {
//               if(makeUnderline){
//                   textView.paintFlags = Paint.UNDERLINE_TEXT_FLAG
//               }
//           }
//            makeBold?.let {
//                if(makeBold){
//                    textView.typeface = Typeface.DEFAULT_BOLD
//                }
//            }
//
//            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize?.toFloat() ?: 18f)
        }

        @BindingAdapter("bind_font_style", "bind_font_size", requireAll = true)
        @JvmStatic
        fun setComponentFontStyle(textView: TextView, fontStyle: String?, fontSize:Float?){
            fontStyle?.let {
                val id = when(fontStyle){
                    FontStyle.ROBOTO_LIGHT.node -> {
                        textView.setTextAppearance(R.style.text_style_light)
                        -1
                    }
                    FontStyle.ROBOTO_MEDIUM.node -> {
                        textView.setTextAppearance(R.style.text_style_medium)
                        -1
                    }
                    FontStyle.ROBOTO_REGULAR.node -> {
                        textView.setTextAppearance(R.style.text_style_regular)
                        -1
                    }
                    else -> -1
                }
                if(id != -1) textView.typeface = ResourcesCompat.getFont(textView.context, id)
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize?.toFloat() ?: 18f)
            }
        }

        @BindingAdapter("bind_identifier", requireAll = true)
        @JvmStatic
        fun saveImage(imageView: ImageView, identifier: String?){
            val context = imageView.context
            try {
                val id = context.resources.
                        getIdentifier(identifier, "drawable", context.packageName)
                if (id != 0){
                    imageView.setImageResource(id)
                }
            }catch (e:Exception){
              Log.e("BINDING","No Image found to bind")
            }
        }

        fun parse(color: String?):Int = if (color?.isNotEmpty() == true) Color.parseColor(color) else 0
    }
}