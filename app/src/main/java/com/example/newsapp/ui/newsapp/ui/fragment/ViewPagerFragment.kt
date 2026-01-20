package com.example.newsapp.ui.newsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.newsapp.R
import com.example.newsapp.ui.newsapp.ui.adapter.ViewPagerAdapter
import com.example.newsapp.ui.newsapp.ui.screen.FirstScreen
import com.example.newsapp.ui.newsapp.ui.screen.SecondScreen
import com.example.newsapp.ui.newsapp.ui.screen.ThirdScreen
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class ViewPagerFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()

        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = adapter
        viewPager.setPageTransformer(ZoomOutPageTransformer())
//        viewPager.beginFakeDrag()
//        viewPager.fakeDragBy(-10f)
//        viewPager.endFakeDrag()

        val dotsIndicator = view.findViewById<SpringDotsIndicator>(R.id.dotIndicator)
        dotsIndicator.attachTo(viewPager)
        return view
    }

}

// يمكنك وضع هذا الكلاس خارج نطاق ViewPagerFragment أو داخله
class ZoomOutPageTransformer : ViewPager2.PageTransformer {
    private val MIN_SCALE = 0.85f
    private val MIN_ALPHA = 0.5f

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            val pageHeight = height

            when {
                position < -1 -> { // [-Infinity,-1)
                    // الصفحة على اليسار بعيدة جداً، نجعلها غير مرئية
                    alpha = 0f
                }

                position <= 1 -> { // [-1,1]
                    // الصفحة بين المنتصف والصفحة المجاورة

                    // حساب النسبة بين الحركة والـ Zoom
                    val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))

                    // حساب موضع الـ Zoom (لجعل الـ View يتوسط الصفحة)
                    val vertMargin = pageHeight * (1 - scaleFactor) / 2
                    val horzMargin = pageWidth * (1 - scaleFactor) / 2

                    translationX = if (position < 0) {
                        horzMargin - vertMargin / 2
                    } else {
                        -horzMargin + vertMargin / 2
                    }

                    // تطبيق الـ Zoom
                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    // تطبيق التلاشي (Fade)
                    alpha = (MIN_ALPHA +
                            (((1 - MIN_ALPHA) / (1 - MIN_SCALE)) * (scaleFactor - MIN_SCALE)))
                }

                else -> { // (1,+Infinity]
                    // الصفحة على اليمين بعيدة جداً، نجعلها غير مرئية
                    alpha = 0f
                }
            }
        }
    }
}