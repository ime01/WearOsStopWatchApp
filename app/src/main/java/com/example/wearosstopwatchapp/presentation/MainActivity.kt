/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.wearosstopwatchapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.*
import com.example.wearosstopwatchapp.R
import com.example.wearosstopwatchapp.presentation.theme.WearOsStopWatchAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel = viewModel<StopWatchViewModel>()

            val timerState by viewModel.timerState.collectAsStateWithLifecycle()
            val stopWatchText by viewModel.stopWatchText.collectAsStateWithLifecycle()

            Scaffold(timeText ={
                TimeText(
                    timeTextStyle = TimeTextDefaults.timeTextStyle(
                        fontSize = 10.sp
                    )
                )
            },
                vignette = {
                    Vignette(vignettePosition = VignettePosition.TopAndBottom)
                }

                ) {
                StopWatch(
                    state = timerState,
                    text = stopWatchText,
                    onToggleRunning = viewModel::toogleIsRunning,
                    onReset = viewModel::resetTimer,
                    modifier = Modifier.fillMaxSize()
                )
            }



        }
    }
}

@Composable
private fun StopWatch(
    state: TimerState,
    text:String,
    onToggleRunning:()->Unit,
    onReset:() -> Unit,
    modifier: Modifier = Modifier
){

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = " FIRST WATCH APP",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = onToggleRunning) {

                Icon(imageVector = if (state==TimerState.RUNNING){
                    Icons.Default.Pause
                }else{
                    Icons.Default.PlayArrow
                     },

                    contentDescription = "play/pause Icon")
            }

            Spacer(modifier = Modifier.width(8.dp))


            Button(
                onClick = onReset,
                enabled = state != TimerState.RESET,
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface)
            ) {

                Icon(imageVector = Icons.Default.Stop,
                    contentDescription = "play/pause Icon")
            }

        }





    }


}

