

package com.jetauth.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetauth.R
import com.jetauth.ui.theme.JetAuthTheme
import com.jetauth.ui.theme.stronglyDeemphasizedAlpha

@Composable
fun SignInSignUpScreen(
    onSignInAsGuest: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    content: @Composable () -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(44.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                content()
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){

                CustomSocialButton(
                    imagePath = painterResource(id = R.drawable.facebook),
                    onTap = {

                    }
                )
                Spacer(modifier = Modifier.width(20.dp))
                CustomSocialButton(
                    imagePath = painterResource(id = R.drawable.google),
                    onTap = {

                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            CreateNewAccount(
                onSignInAsGuest = onSignInAsGuest,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))


        }
    }
}


@Composable
fun CustomSocialButton(
    imagePath: Painter,
    onTap: () -> Unit
){
    Box(
        modifier = Modifier
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(10.dp))
            .clip(RectangleShape)
            .background(color = MaterialTheme.colorScheme.onPrimary)
            .size(60.dp)


    ){
        Image(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize(),
            painter = imagePath, contentDescription = stringResource(id = R.string.fb))
    }
}
@OptIn(ExperimentalMaterial3Api::class) // CenterAlignedTopAppBar is experimental in m3
@Composable
fun SignInSignUpTopAppBar(
    topAppBarText: String,
    onNavUp: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = topAppBarText,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavUp) {
                Icon(
                    imageVector = Icons.Filled.ChevronLeft,
                    contentDescription = stringResource(id = R.string.back),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        // We need to balance the navigation icon, so we add a spacer.
        actions = {
            Spacer(modifier = Modifier.width(68.dp))
        },
    )
}



@Composable
fun CreateNewAccount(
    onSignInAsGuest: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        
        text = stringResource(id = R.string.createNewAccount),
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeemphasizedAlpha),
        modifier = Modifier.paddingFromBaseline(top = 25.dp)
    )
}

@Preview
@Composable
fun SignInSignUpScreenPreview() {
    JetAuthTheme {
        Surface {
            SignInSignUpScreen(
                onSignInAsGuest = {},
                content = {}
            )
        }
    }
}
