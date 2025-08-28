import androidx.fragment.app.Fragment
import com.example.apitest2.navigation.navigation_fragment.NavigatorHolder
import com.example.apitest2.navigation.navigation_fragment.Router


class RouterImpl: Router {

    override val navigatorHolder: NavigatorHolder = NavigatorHolderImpl()

    override fun openFragment(fragment: Fragment) {
        navigatorHolder.openFragment(fragment)
    }


}