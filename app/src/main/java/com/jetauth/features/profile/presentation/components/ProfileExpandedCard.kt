package com.jetauth.features.profile.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jetauth.R
import com.jetauth.auth.components.EmailState
import com.jetauth.auth.components.EmailStateSaver
import com.jetauth.auth.components.LastNameStateSaver
import com.jetauth.auth.components.NameState
import com.jetauth.auth.components.NameStateSaver
import com.jetauth.features.profile.data.model.User
import com.jetauth.ui.theme.stronglyDeemphasizedAlpha

@Composable
fun ProfileExpendedCard(
    onUpdateProfileSubmit: (firstName: String, lastName: String) -> Unit,
    isLoading: Boolean,
    email: String?,
    user: User?,
){
    var expanded by remember { mutableStateOf(true) }
    val focusRequester = remember { FocusRequester() }

    Column(
    ) {
        val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
            mutableStateOf(EmailState(user?.firstName + user?.lastName))
        }

        val firstNameState by rememberSaveable(stateSaver = NameStateSaver) {
            mutableStateOf(NameState())
        }

        val lastNameState by rememberSaveable(stateSaver = LastNameStateSaver) {
            mutableStateOf(NameState())
        }

        Card (
            modifier = Modifier.shadow(
                shape = RectangleShape,
                spotColor = DefaultShadowColor,
                elevation = 3.dp),

            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)

        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable( onClick = {
                        expanded = !expanded
                    })
                ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Row {
                    Image(painter = painterResource(id = R.drawable.name), contentDescription = "")
                    Spacer(modifier = Modifier.width(18.dp))
                    Text(text = stringResource(id = R.string.account))
                }
                Icon(
                    Icons.Default.ArrowDropDown, "",
                )
            }
            HorizontalDivider()
            AnimatedVisibility(
                visible = expanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.fullname),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeemphasizedAlpha),
                    )
                    ProfileEmail(emailState, onImeAction = { focusRequester.requestFocus() })

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = stringResource(id = R.string.firstname),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeemphasizedAlpha)
                    )
                    FirstName(firstNameState, onImeAction = { focusRequester.requestFocus() })

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = stringResource(id = R.string.lastName),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeemphasizedAlpha)
                    )
                    LastName(lastNameState, onImeAction = { focusRequester.requestFocus() })
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Button(
                            onClick = { onUpdateProfileSubmit(firstNameState.text, lastNameState.text)},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 25.dp),
                            shape = RoundedCornerShape(10.dp),
                            enabled = firstNameState.isValid && lastNameState.isValid
                        ) {
                            if(isLoading){
                                CircularProgressIndicator(
                                    color = MaterialTheme.colorScheme.onPrimary)
                            } else {
                                Text(
                                    modifier = Modifier.padding(15.dp),
                                    text = stringResource(id = R.string.save)
                                )
                            }
                        }
                    }

                }
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Row {
                    Image(painter = painterResource(id = R.drawable.password), contentDescription = "")
                    Spacer(modifier = Modifier.width(18.dp))
                    Text(text = stringResource(id = R.string.password))
                }
                Icon(
                    Icons.Default.ArrowDropDown, "",
                )
            }
            HorizontalDivider()

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Row {
                    Image(painter = painterResource(id = R.drawable.notification), contentDescription = "")
                    Spacer(modifier = Modifier.width(18.dp))
                    Text(text = stringResource(id = R.string.notification))
                }
                Icon(
                    Icons.Default.ArrowDropDown, "",
                )
            }
            HorizontalDivider()

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Row {
                    Image(painter = painterResource(id = R.drawable.heart), contentDescription = "")
                    Spacer(modifier = Modifier.width(18.dp))
                    Text(text = stringResource(id = R.string.wishlist))
                }
                Icon(
                    Icons.Default.ArrowDropDown, "",
                )
            }
            HorizontalDivider()
        }

    }
}