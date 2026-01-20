package io.fluxio.app.presentation.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.fluxio.app.R
import io.fluxio.app.presentation.ui.theme.FluxioTheme

@Composable
fun VideoCard(
    title: String,
    channelName: String,
    viewsText: String,
    publishedText: String,
    thumbnailModel: Any,
    avatarModel: Any,
    @DrawableRes overflowIconRes: Int,
    onOverflowClick: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        VideoThumbnail(
            model = thumbnailModel,
            contentDescription = title
        )
        VideoMetaRow(
            title = title,
            channelName = channelName,
            viewsText = viewsText,
            publishedText = publishedText,
            avatarModel = avatarModel,
            overflowIconRes = overflowIconRes,
            onOverflowClick = onOverflowClick
        )
    }
}

@Composable
fun VideoThumbnail(
    model: Any,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = model,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
    )
}

@Composable
fun VideoMetaRow(
    title: String,
    channelName: String,
    viewsText: String,
    publishedText: String,
    avatarModel: Any,
    @DrawableRes overflowIconRes: Int,
    onOverflowClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Top
    ) {
        AsyncImage(
            model = avatarModel,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = "$channelName • $viewsText • $publishedText",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Icon(
            painter = painterResource(id = overflowIconRes),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun VideoCardPreview() {
    FluxioTheme {
        VideoCard(
            title = "ЭТИ МАШИНЫ НЕВОЗМОЖНО НАЙТИ! 20 МАШИН С УРОВНЕМ СПРЯТАНЫ В НОВОЕ ШОУ - АУКЦИОН ЗА КОНТЕЙНЕР! ТОРГУЙСЯ - ДОГОВАРИВАЙСЯ",
            channelName = "Coffi Channel",
            viewsText = "438 тыс. просмотров",
            publishedText = "1 год назад",
            thumbnailModel = android.R.drawable.ic_menu_report_image,
            avatarModel = android.R.drawable.sym_def_app_icon,
            overflowIconRes = R.drawable.ic_more,
            onOverflowClick = {},
            onClick = {}
        )
    }
}
