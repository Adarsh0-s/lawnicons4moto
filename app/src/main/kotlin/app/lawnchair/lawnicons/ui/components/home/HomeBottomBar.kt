package app.lawnchair.lawnicons.ui.components.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.lawnchair.lawnicons.R
import app.lawnchair.lawnicons.model.IconRequestModel
import app.lawnchair.lawnicons.ui.util.Constants

@Composable
fun HomeBottomBar(
    context: Context,
    iconRequestModel: IconRequestModel?,
    snackbarHostState: SnackbarHostState,
    onNavigate: () -> Unit,
    onExpandSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BottomAppBar(
        actions = {
//            make empty space
            Box(
                modifier = Modifier.size(20.dp),
            )
            SimpleTooltipBox(
                label = stringResource(id = R.string.apply),
            ) {
                IconButton(
                    onClick = {
//                        com.motorola.personalize.app.IconPacksActivity
                        val intent = Intent();
                        intent.setClassName("com.motorola.personalize", "com.motorola.personalize.app.IconPacksActivity")
                        if (intent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(intent)
                        }
                    },
//                   set backgroundColor as @color/primaryBackground
                    modifier = Modifier.background(color = BottomAppBarDefaults.bottomAppBarFabColor, shape = RoundedCornerShape(24)).size(160.dp, 60.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.applybutton),
                        contentDescription = stringResource(id = R.string.apply),
                        modifier = Modifier.requiredSize(100.dp),
                    )
                }
            }
        },
        floatingActionButton = {
            SimpleTooltipBox(
                label = stringResource(id = R.string.search),
            ) {
                FloatingActionButton(
                    onClick = onExpandSearch,
                    containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = stringResource(id = R.string.search),
                    )
                }
            }
        },
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SimpleTooltipBox(
    label: String,
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit),
) {
    TooltipBox(
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = {
            PlainTooltip {
                Text(label)
            }
        },
        state = rememberTooltipState(),
        modifier = modifier,
    ) {
        content()
    }
}

@Preview
@Composable
private fun HomeBottomBarPreview() {
    HomeBottomBar(
        context = androidx.compose.ui.platform.LocalContext.current,
        iconRequestModel = null,
        snackbarHostState = SnackbarHostState(),
        onNavigate = {},
        onExpandSearch = {},
    )
}
