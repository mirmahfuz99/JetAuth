package com.jetauth.features.profile.presentation.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetauth.R
import com.jetauth.features.profile.data.model.User
import com.jetauth.features.profile.presentation.components.DottedBorderImage
import com.jetauth.features.profile.presentation.components.ProfileExpendedCard
import com.jetauth.ui.theme.JetAuthTheme

@Composable
fun ProfileScreen(
    onUpdateProfileSubmit: (firstName: String, lastName: String) -> Unit,
    isLoading: Boolean,
    snackbarHostState: SnackbarHostState,
    user: User?,
){
    Scaffold (
        content = {innerPadding ->
            Box (
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ){
                Column(

                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxWidth()
                        .padding(16.dp)

                ) {
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = stringResource(id = R.string.myAccount),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)

                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    DottedBorderImage(
                        painter = painterResource(id = R.drawable.profile_dummy_image),
                        contentDescription = stringResource(id = R.string.profile)
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        text = stringResource(id = R.string.myAccount),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)

                    )
                    Text(
                        text = """info@johnsmith.com""",
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurface)

                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    ProfileExpendedCard(
                        email = R.string.email.toString(),
                        onUpdateProfileSubmit = onUpdateProfileSubmit,
                        isLoading = isLoading,
                        user = user
                    )

                }
            }

        }
    )
}




@Preview(name = "ProfileScreen light theme", uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Preview(name = "Sign in dark theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomePreview() {
    JetAuthTheme {

        val snackbarHostState = remember { SnackbarHostState() }

        ProfileScreen(
            onUpdateProfileSubmit = {_, _ ->},
            isLoading = false,
            snackbarHostState = snackbarHostState,
            user = null
        )
    }
}
