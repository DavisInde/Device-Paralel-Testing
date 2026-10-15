package step.home

import org.example.test.base.BaseStep
import org.example.test.context.GlobalContext
import org.example.test.features.PageProvider
import org.example.test.features.home.AndroidHomePage
import org.example.test.features.home.HomePage
import org.example.test.features.home.IOSHomePage

class HomeStep: BaseStep<HomePage>() {
    override val targetPage: HomePage
        get() = PageProvider.getPage(
            GlobalContext.getAppiumDriver(),
            ::AndroidHomePage,
            ::IOSHomePage
        )

}