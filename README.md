# ReusableComponents
This projects contains reusable components that contains accordian view built and this library can be updated for adding more views

![Demo Video](https://user-images.githubusercontent.com/5475652/145971410-fc740931-e941-42e2-b5a5-3d96a62e9288.gif)

## Modules of App

## - App
It uses the all the components and class releated to Android Framework. It gets the data from presentation layer and shows on UI.

## - BuildSrc
This module helps to list and manage all the dependencies of the app at one place. It has list of dependencies and versions of that dependencies.

## - uicomponents
This module contains code related to accordian view, it contains parent and child view population with custom list view.
### - Accordian View
### - Other views to be added soon

# Usage

## - Inside Layout file
```
 <com.reusable.uicomponents.accordian.CustomExpandableListView
            android:id="@+id/list_expandablelistView"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_marginTop="24dp"
            android:groupIndicator="@null"
            android:layoutDirection="ltr"
            android:listSelector="@android:color/transparent"
            android:divider="@null"
            android:childDivider="@null"
            android:dividerHeight="0dp"
            app:titleColor = "@string/accordian_color"  //Custom property
            app:titleFontStyle = "@string/accordian_fontStyle" //Custom property
            app:titleFontSize = "22.0" //Custom property
            android:nestedScrollingEnabled="false"/>
            
```
## - Inside Activity 
### - Impliment this linstener AccordianListeners inside your activity.

```
/**
     * This method populates parent and child view.
     */
    fun populateAdapter(){
        val list = dummydata()
        val parentList = ArrayList<String>(list.keys)
        val adapter = ExpandableListAdapter(parentList).setListener(this)
        viewBinding?.let {
            it.listExpandablelistView.setAdapter(adapter)
            it.listExpandablelistView.setExpanded(true)
        }
    }

    /**
     * Just Dummy data for populating UI
     */
    fun dummydata(): HashMap<String, List<String>> {
        val list = LinkedHashMap<String, List<String>>()
        list["one"] = emptyList()
        list["two"] = emptyList()
        list["three"] = emptyList()
        return list
    }
            
```

# Built With

[Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.

[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.

[Ktlint](https://github.com/pinterest/ktlint) -Ktlint is a static code analysis tool maintain by Pinterest. Linter and formatter for Kotlin code.

[Databinding](https://developer.android.com/topic/libraries/data-binding) -The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically


# -License


MIT License

Copyright (c) 2021 Vijay

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
