package com.codenova.mindmate.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codenova.mindmate.R
import com.codenova.mindmate.data.model.Onboarding
import com.codenova.mindmate.ui.theme.LARGE_PADDING
import com.codenova.mindmate.ui.theme.MindMateTheme
import com.codenova.mindmate.ui.theme.SMALL_PADDING
import kotlinx.coroutines.launch

@Composable
fun OnboardingPage() {
    val onboardingData: List<Onboarding> = listOf(
        Onboarding(
            imageId = R.drawable.support,
            title ="Support Tailored Just For You",
            description = "Get advice and resources based on your unique\nchallenges — from homesickness to academic pressure\nand everything in between"
        ),
        Onboarding(
            imageId = R.drawable.happy,
            title = "Understand How You Feel",
            description = "Monitor your mood, reflect on your emotions, and see\nyour growth over time with helpful insights"
        ),
        Onboarding(
            imageId = R.drawable.not_alone,
            title = "You're Not Alone",
            description = "We're here for you, every step of the way. Let's start\nbuilding healthier habits together!"
        )
    )
    val pageState = rememberPagerState(
        initialPage = 0,
        pageCount = {onboardingData.size})
    val animationScope = rememberCoroutineScope()

        Scaffold(
            bottomBar = {
                OnboardingFooter(
                    pageCount = onboardingData.size,
                    currentPage = pageState.currentPage,
                    onBackClick = {
                        animationScope.launch {
                            animationScope.launch {
                                if(pageState.currentPage != 0) {
                                    pageState.animateScrollToPage(page = pageState.currentPage-1)
                                }
                            }
                        }
                    },
                    onProceedClick = {
                        animationScope.launch {
                            if(pageState.currentPage !=2) {
                                pageState.animateScrollToPage(page = pageState.currentPage+1)
                            }
                        }
                    }
                )
            }
        ) {innerPadding ->
            HorizontalPager(state = pageState) { page ->
                OnboardingItem(item = onboardingData[page], innerPadding = innerPadding)
            }
        }
}

@Composable
fun OnboardingFooter(
    pageCount: Int = 0,
    currentPage: Int = 0,
    onBackClick: () -> Unit = {},
    onProceedClick: () -> Unit = {}
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
 ) {
        Box(modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center) {
            FooterItemContainer(currentPage = currentPage) {
                TextButton(onClick = onBackClick) {
                    Text(text = if(currentPage !=0) stringResource(id = R.string.back_button) else "")
                }
            }
        }

        // page indicator
        Box(modifier = Modifier.weight(1.4f)
            .fillMaxHeight(),
            contentAlignment = Alignment.Center) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING)
            ) {
                repeat(pageCount) {iteration ->
                    val color = if(currentPage == iteration) MaterialTheme.colorScheme.primary else
                        Color.LightGray
                    Box(modifier = Modifier
                        .clip(CircleShape)
                        .background(color)
                        .size(12.dp))

                }
            }
        }


        Box(modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center) {
            FooterItemContainer(currentPage = currentPage) {
                Button(onClick = onProceedClick) {
                    Text(text = if(currentPage !=2) stringResource(R.string.next_button)
                    else stringResource(id = R.string.done_button))
                }
            }
        }
    }
}

@Composable
fun FooterItemContainer(currentPage: Int, child: @Composable () -> Unit) {
    child()
}

@Composable
fun OnboardingItem(item: Onboarding, innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = LARGE_PADDING),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = item.imageId),
            contentDescription = item.title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(LARGE_PADDING * 2))

        Text(text = item.title,
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(height = LARGE_PADDING * 2))

        Text(text = item.description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center)
    }
}

@Composable
@Preview(showBackground=true)
fun OnboardingPagePreview() {
    MindMateTheme {
        OnboardingPage()
    }
}