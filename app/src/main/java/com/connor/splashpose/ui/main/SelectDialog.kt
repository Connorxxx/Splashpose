package com.connor.splashpose.ui.main

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun SelectDialog(onDismiss: () -> Unit, cancelIconClick: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Column {
                DialogTitle(cancelIconClick = cancelIconClick)
                Spacer(modifier = Modifier.height(12.dp))
                DialogBody()
                //Test()
            }
        },
        text = { },
        confirmButton = {

        }
    )
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun DialogBody() {
    Column {
        val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf(options[0]) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                label = { Text("Option") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        var text by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            readOnly = false,
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text("Text") },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
        )
    }
}

@Composable
private fun Test() {
    Column(modifier = Modifier.fillMaxWidth()) {
        // val (select) = createRefs()
        var value by remember { mutableStateOf("") }
        val suggestions = remember { mutableStateListOf("karan", "karanx", "karany") }
        var expanded by remember { mutableStateOf(false) }
        var dropDownWidth by remember { mutableStateOf(0) }
        val focusManager = LocalFocusManager.current
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { dropDownWidth = it.width }
                .onFocusChanged { expanded = it.isFocused },
            value = value,
            onValueChange = {
                value = it
                expanded = true
            },
            label = {
                Text("Label")
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                focusManager.clearFocus()
            },
            modifier = Modifier
                .width(with(LocalDensity.current) { dropDownWidth.toDp() }),
            properties = PopupProperties(focusable = false)
        ) {
            suggestions.forEach {
                DropdownMenuItem(onClick = {
                    value = it
                    expanded = false
                    focusManager.clearFocus()
                }) {
                    Text(text = it)
                }
            }
        }
    }
}

@Composable
private fun DialogTitle(cancelIconClick: () -> Unit) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (icon, tvTitle, tvCancel) = createRefs()
        Icon(
            modifier = Modifier
                .constrainAs(icon) {
                    start.linkTo(parent.start)
                }
                .size(24.dp)
                .clickable(onClick = cancelIconClick),
            imageVector = Icons.Filled.Close,
            contentDescription = "close",
            tint = Color.Gray
        )
        Text(text = "Dialog Title",
            fontSize = 28.sp,
            modifier = Modifier.constrainAs(tvTitle) {
                start.linkTo(icon.end, margin = 12.dp)
                top.linkTo(icon.top)
                bottom.linkTo(icon.bottom, margin = 7.dp)
            })
        Text(
            text = "Save",
            color = Color.Blue,
            modifier = Modifier.constrainAs(tvCancel) {
                end.linkTo(parent.end, margin = 8.dp)
                top.linkTo(tvTitle.top)
                bottom.linkTo(tvTitle.bottom)
                //baseline.linkTo(tvTitle.baseline)
            })
    }
}