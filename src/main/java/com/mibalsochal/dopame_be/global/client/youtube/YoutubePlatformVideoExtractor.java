package com.mibalsochal.dopame_be.global.client.youtube;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.mibalsochal.dopame_be.domain.mining.domain.platform.PlatformDetailResult;
import com.mibalsochal.dopame_be.domain.mining.domain.platform.PlatformVideoInfoExtractor;
import com.mibalsochal.dopame_be.domain.mining.domain.platform.PlatformVideoProcessingData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class YoutubePlatformVideoExtractor implements PlatformVideoInfoExtractor {
	private final YoutubeProperties youtubeProperties;

	private final WebClient webClient = WebClient.create("https://www.googleapis.com/youtube/v3/videos");

	@Override
	public PlatformDetailResult extract(PlatformVideoProcessingData platformVideoProcessingData) {
		Pattern pattern = Pattern.compile("shorts\\/[a-zA-Z0-9_-]{11}");
		Matcher matcher = pattern.matcher(platformVideoProcessingData.platformUrl());

		if (matcher.find()) {
			final String videoId = matcher.group(0).split("/")[1];

			log.info("videoId: {}", videoId);
			YoutubeVideoInfo youtubeVideoInfo = webClient.get()
				.uri(uriBuilder -> uriBuilder
					.queryParam("key", youtubeProperties.getApiKey())
					.queryParam("part", "snippet")
					.queryParam("id", videoId)
					.build())
				.retrieve()
				.bodyToMono(YoutubeVideoInfo.class)
				.block();

			return new PlatformDetailResult(youtubeVideoInfo.getTitle(), platformVideoProcessingData.platformUrl(),
				youtubeVideoInfo.getTags());
		}
		throw new RuntimeException("not found youtube video id");
	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	private static class YoutubeVideoInfo {
		private List<Item> items;

		public String getTitle() {
			return this.items.get(0).snippet.title;
		}

		public List<String> getTags() {
			return this.items.get(0).snippet.tags;
		}

	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Item {
		private Snippet snippet;

	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Snippet {
		String title;
		List<String> tags;
	}
}
