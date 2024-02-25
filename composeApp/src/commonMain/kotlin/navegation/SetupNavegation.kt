package navegation
import CepSearch
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.ScaleTransition

@Composable
fun SetupNavigation(){
    Navigator(
        screen = CepSearch(id = "form")
    ){
            navigator ->
        ScaleTransition(navigator)
    }
}
