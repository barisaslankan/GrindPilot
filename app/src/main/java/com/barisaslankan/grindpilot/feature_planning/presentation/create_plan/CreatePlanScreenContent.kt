package com.barisaslankan.grindpilot.feature_planning.presentation.create_plan

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.barisaslankan.grindpilot.core.components.IconButtonWithText
import com.barisaslankan.grindpilot.core.model.Day
import com.barisaslankan.grindpilot.core.ui.theme.BackgroundColor
import com.barisaslankan.grindpilot.core.ui.theme.HintColor
import com.barisaslankan.grindpilot.core.ui.theme.MEDIUM_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.OrangeGP
import com.barisaslankan.grindpilot.core.ui.theme.SMALL_PADDING
import com.barisaslankan.grindpilot.core.ui.theme.TextColor
import com.barisaslankan.grindpilot.core.ui.theme.Typography
import com.barisaslankan.grindpilot.feature_planning.domain.model.DurationType
import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal
import com.barisaslankan.grindpilot.feature_planning.domain.model.ProgressType
import com.barisaslankan.grindpilot.feature_planning.presentation.create_plan.component.DayPicker
import com.barisaslankan.grindpilot.feature_planning.presentation.create_plan.component.PlanTemplate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePlanScreenContent(
    modifier : Modifier,
    name : String,
    onNameChanged : (String) -> Unit,
    goals : ArrayList<Goal>,
    selectedGoals : ArrayList<Goal>,
    addGoalToPlan : (goal : Goal) -> Unit,
    createPlan : () -> Unit,
    onBackButtonClicked : () -> Unit,
    removeGoalFromPlan : (goal : Goal) -> Unit,
    isDurationTypeExpanded: Boolean,
    onDurationTypeChanged: (DurationType) -> Unit,
    durationText : String,
    onDurationTextChanged : (String) -> Unit,
    onDayPicked : (Day) -> Unit,
    selectedDays : ArrayList<Day>,
    onDurationTypeExpandedChanged: (Boolean) -> Unit,
    displayedDurationType: String,
    displayedDurationTypeChanged: (String) -> Unit,
    isBottomSheetExpanded : Boolean,
    onBottomSheetExpanded : (Boolean) -> Unit
    ){

    val modalBottomSheetState = rememberModalBottomSheetState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(MEDIUM_PADDING)
    ) {

        Column(
            modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
        ) {

            Row(
                modifier = modifier
            ) {
                IconButton(
                    colors = (IconButtonColors(
                        containerColor = OrangeGP,
                        contentColor = BackgroundColor,
                        disabledContentColor = OrangeGP,
                        disabledContainerColor = BackgroundColor
                    )),
                    onClick = onBackButtonClicked,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }
                Spacer(modifier = modifier.weight(1f))

                IconButton(
                    colors = (
                            IconButtonColors(
                                containerColor = OrangeGP,
                                contentColor = BackgroundColor,
                                disabledContentColor = OrangeGP,
                                disabledContainerColor = BackgroundColor
                            )),
                    onClick = createPlan,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Plan Button"
                    )
                }
            }

            OutlinedTextField(
                shape = RoundedCornerShape(MEDIUM_PADDING),
                modifier = Modifier
                    .fillMaxWidth(),
                value = name,
                onValueChange = onNameChanged,
                textStyle = Typography.bodyMedium,
                colors = TextFieldDefaults.colors(
                    unfocusedTextColor = TextColor,
                    focusedTextColor = TextColor,
                    focusedContainerColor = BackgroundColor,
                    unfocusedContainerColor = BackgroundColor
                ),
                label = {
                    Text(
                        text = "Plan name",
                        style = Typography.bodyMedium,
                        color = HintColor
                    )
                },
            )
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.weight(1.5f),
                    text = "Duration",
                    style = Typography.bodyMedium,
                    color = TextColor
                )

                Spacer(modifier = Modifier.weight(2f))

                TextField(
                    modifier = Modifier.weight(1f),
                    textStyle = Typography.bodyMedium,
                    colors = TextFieldDefaults.colors(
                        unfocusedTextColor = TextColor,
                        focusedTextColor = TextColor,
                        focusedContainerColor = BackgroundColor,
                        unfocusedContainerColor = BackgroundColor
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    value = durationText,
                    onValueChange = onDurationTextChanged,
                )

                Spacer(modifier = Modifier.width(SMALL_PADDING))

                DurationTypePicker(
                    modifier = Modifier.weight(3f),
                    isDurationTypeExpanded = isDurationTypeExpanded,
                    onDurationTypeChanged = onDurationTypeChanged,
                    onDurationTypeExpandedChanged = onDurationTypeExpandedChanged,
                    displayedDurationType = displayedDurationType,
                    displayedDurationTypeChanged = displayedDurationTypeChanged,
                )
            }

            DayPicker(
                modifier = modifier.padding(vertical = SMALL_PADDING),
                onDayPicked = onDayPicked,
                selectedDays = selectedDays
            )

            IconButtonWithText(
                onClick = {onBottomSheetExpanded(true)},
                icon = Icons.Default.Add,
                text = "Add Goal",
                color = OrangeGP
            )

           PlanTemplate(
               modifier = modifier
                   .weight(1f)
                   .background(BackgroundColor),
               selectedGoals = selectedGoals,
               removeGoalFromPlan = removeGoalFromPlan
           )

            OutlinedButton(
                border = null,
                modifier = modifier
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = OrangeGP,
                    contentColor = BackgroundColor,
                    disabledContainerColor = OrangeGP,
                    disabledContentColor = BackgroundColor
                ),
                content = {
                    Text(
                        text = "Upload Plan",
                        style = Typography.titleSmall,
                        color = BackgroundColor
                    )
                },
                onClick = createPlan
            )

            if(isBottomSheetExpanded){
                ModalBottomSheet(
                    sheetState = modalBottomSheetState,
                    onDismissRequest = { onBottomSheetExpanded(false) }) {
                    LazyColumn(
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        items(goals){goal ->
                            Text(
                                modifier = Modifier.clickable {
                                    addGoalToPlan(goal)
                                    onBottomSheetExpanded(false) },
                                text = "Upload Plan",
                                style = Typography.titleSmall,
                                color = BackgroundColor
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DurationTypePicker(
    modifier : Modifier,
    isDurationTypeExpanded : Boolean,
    onDurationTypeChanged : (DurationType) -> Unit,
    onDurationTypeExpandedChanged: (Boolean) -> Unit,
    displayedDurationTypeChanged : (String) -> Unit,
    displayedDurationType: String
){
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = isDurationTypeExpanded,
        onExpandedChange = onDurationTypeExpandedChanged
    ) {
        TextField(
            value = displayedDurationType,
            onValueChange = displayedDurationTypeChanged,
            maxLines = 1,
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDurationTypeExpanded)},
            modifier = Modifier.menuAnchor(),
            textStyle = Typography.bodyMedium,
            colors = TextFieldDefaults.colors(
                unfocusedTextColor = TextColor,
                focusedTextColor = TextColor,
                focusedContainerColor = BackgroundColor,
                unfocusedContainerColor = BackgroundColor
            ),
            shape = RoundedCornerShape(MEDIUM_PADDING)
        )

        ExposedDropdownMenu(
            expanded = isDurationTypeExpanded,
            onDismissRequest = { onDurationTypeExpandedChanged(false) },
            modifier = Modifier.background(BackgroundColor)
        ) {
            DurationType.entries.forEach { durationType ->
                DropdownMenuItem(
                    text = { Text(
                        text = durationType.name.substring(0, 1) + durationType.name.substring(1).lowercase(),
                        style = Typography.bodyMedium,
                        color = TextColor
                    )},
                    onClick = {
                        onDurationTypeChanged(durationType)
                        displayedDurationTypeChanged(durationType.name.substring(0, 1) + durationType.name.substring(1).lowercase())
                        onDurationTypeExpandedChanged(false)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun CreatePlanScreenContentPreview(){
    CreatePlanScreenContent(
        modifier = Modifier.fillMaxWidth(),
        goals = arrayListOf(
            Goal(
                "",
                "",
                "Goal1",
                ProgressType.HOURS,
                tasks = null,
                progress = 0.0,
                "",
                100.0,
            ),
            Goal(
                "",
                "",
                "Goal2",
                ProgressType.HOURS,
                tasks = null,
                progress = 0.0,
                "",
                100.0
            )
        ),
        selectedGoals = arrayListOf(
            Goal(
                "",
                "",
                "Goal1",
                ProgressType.HOURS,
                tasks = null,
                progress = 0.0,
                "",
                100.0,
            ),
            Goal(
                "",
                "",
                "Goal2",
                ProgressType.HOURS,
                tasks = null,
                progress = 0.0,
                "",
                100.0
            )
        ),
        addGoalToPlan = {},
        createPlan = {},
        onBackButtonClicked = {},
        removeGoalFromPlan = {},
        name = "",
        onNameChanged = {},
        isDurationTypeExpanded = false,
        onDurationTypeChanged = {},
        durationText = "",
        onDurationTextChanged = {},
        selectedDays = arrayListOf(),
        onDayPicked = {},
        onDurationTypeExpandedChanged = {},
        displayedDurationType = "WEEKS",
        displayedDurationTypeChanged = {},
        isBottomSheetExpanded = false,
        onBottomSheetExpanded = {}
    )
}