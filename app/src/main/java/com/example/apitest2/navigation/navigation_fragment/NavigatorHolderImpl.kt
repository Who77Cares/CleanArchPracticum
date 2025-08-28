import androidx.fragment.app.Fragment
import com.example.apitest2.navigation.navigation_fragment.NavigatorMy
import com.example.apitest2.navigation.navigation_fragment.NavigatorHolder


class NavigatorHolderImpl: NavigatorHolder {

    private var navigatorMy: NavigatorMy? = null

    override fun attachNavigator(navigatorMy: NavigatorMy) {
        this.navigatorMy = navigatorMy
    }

    override fun detachNavigator() {
        this.navigatorMy = null
    }

    override fun openFragment(fragment: Fragment) {
        navigatorMy?.openFragment(fragment)
    }

}