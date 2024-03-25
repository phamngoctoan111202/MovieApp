package com.noatnoat.feature_profile.presentation.screen.profilescreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon

import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.noatnoat.feature_base.presentation.composable.compose_colors.BasicColors
import com.noatnoat.feature_profile.data.datasource.UserRepositoryImpl
import com.noatnoat.feature_profile.data.datasource.firebase.FirestoreDatasource
import com.noatnoat.feature_profile.data.datasource.firebase.service.UserFirestoreService
import com.noatnoat.feature_profile.data.datasource.firebase.service.UserFirestoreServiceImpl
import com.noatnoat.feature_profile.domain.repository.UserRepository
import com.noatnoat.feature_profile.domain.usecase.GetUserInfoUseCase
import com.noatnoat.movieapp.profile.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.navigation.koinNavGraphViewModel

class ProfileFragment : Fragment() {
    private val profileViewModel: ProfileViewModel by koinNavGraphViewModel(R.id.feature_profile_nav)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        profileViewModel.getUserInfo()

        return ComposeView(requireContext()).apply {
            setContent {
                UserProfileScreen(profileViewModel)
            }
        }
    }
}

@Composable
fun UserProfileScreen(profileViewModel: ProfileViewModel) {
    val user by profileViewModel.user.observeAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(BasicColors.backgroundColor)) {
        if (user != null) {
            LazyColumn {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Gray)
                            .padding(vertical = 35.dp, horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AvatarIcon()
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${user!!.userName}",
                            color = BasicColors.textColor,
                            fontSize = 35.sp
                        )
                    }

                }

                item {
                    IconAndTextColumn()
                }


            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Black)
            }
        }
    }
}

@Composable
fun SignOutButton(onSignOutClick: () -> Unit) {
    Button(
        onClick = {
            onSignOutClick()
        },
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Text(
            text = "Sign Out",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}




@Composable
fun AvatarIcon() {
    Image(
        painter = painterResource(R.drawable.img_1),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
    )
}
@Composable
fun IconAndTextColumn() {
    val iconAndTextList = listOf(
        Pair(R.drawable.baseline_language_24, "Ngôn ngữ"),
        Pair(R.drawable.baseline_subtitles_24, "Bản dịch phụ đề"),
        Pair(R.drawable.baseline_settings_24, "Cài đặt"),
        Pair(R.drawable.baseline_question_answer_24, "Câu hỏi thường gặp"),
        Pair(R.drawable.baseline_report_problem_24, "Phản ánh ý kiến")
    )

    Column {
        iconAndTextList.forEach { (iconResId, text) ->
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp)) {
                Icon(painter = painterResource(id = iconResId), contentDescription = null, tint = BasicColors.textColor )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text, color = BasicColors.textColor)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
