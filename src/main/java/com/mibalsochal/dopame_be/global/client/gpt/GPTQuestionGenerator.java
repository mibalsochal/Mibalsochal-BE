package com.mibalsochal.dopame_be.global.client.gpt;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mibalsochal.dopame_be.domain.mining.domain.question.QuestionGenerator;
import com.mibalsochal.dopame_be.domain.mining.domain.question.QuestionRequest;
import com.mibalsochal.dopame_be.domain.mining.domain.question.QuestionResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class GPTQuestionGenerator implements QuestionGenerator {
	private static final String PROMPT = """

		#해린 #HAERINE #뉴진스 #NewJeans #Ditto #디토 #강해린 #직캠 #Fancam #세로보기 #세로
		키워드와 관련된 창의적인 사고력을 기를 수 있는 질문을 ~~나요?체로 생성해줘
		가능하면 이 사람에 대해 어떻게 생각하는 지에 대한 생각을 들어볼 수 있는 질문으로 짧고 간결하게 생성해줘
		가능하면 사회에 비판적인 생각을 기를 수 있는 논쟁거리가 있는 질문으로 만들어줘
		예를 들면 ‘해린과 같은 고양이상이 최근 트랜디한 얼굴로 자리잡은 이유가 무엇일까요?’, ‘뉴진스와 같은 여자 아이돌이 우리 사회에 미치는 영향력은 무엇일까요?’, ‘여자아이돌이 10대 청소년에게 미칠 수 있는 영향략은 무엇일까요?’가 있어
		한 문장으로 하나의 질문만 알려줘,한 문장으로 하나의 질문만 알려줘,한 문장으로 하나의 질문만 알려줘,한 문장으로 하나의 질문만 알려줘,한 문장으로 하나의 질문만 알려줘,한 문장으로 하나의 질문만 알려줘,한 문장으로 하나의 질문만 알려줘,한 문장으로 하나의 질문만 알려줘,한 문장으로 하나의 질문만 알려줘,한 문장으로 하나의 질문만 알려줘, 한 문장으로 하나의 질문만 알려줘,한 문장으로 하나의 질문만 알려줘,한 문장으로 하나의 질문만 알려줘,한 문장으로 하나의 질문만 알려줘,한 문장으로 하나의 질문만 알려줘,한 문장으로 하나의 질문만 알려줘,한 문장으로 하나의 질문만 알려줘,한 문장으로 하나의 질문만 알려줘,한 문장으로 하나의 질문만 알려줘,한 문장으로 하나의 질문만 알려줘
		            """;
	private final GPTProperties gptProperties;
	private final ObjectMapper objectMapper;
	private final WebClient webClient = WebClient.create("https://api.openai.com/v1/chat/completions");

	@Override
	@SneakyThrows
	public QuestionResult generateQuestion(QuestionRequest questionRequest) {

		var result = webClient.post()
			.header("Authorization", "Bearer " + gptProperties.getApiKey())
			.bodyValue(
				new GPTRequest("gpt-3.5-turbo", List.of(new Message("user", PROMPT + questionRequest.request()))))
			.retrieve()
			.bodyToMono(GPTResult.class)
			.doOnError(error -> log.info("error : {}", error))
			.block();
		return new QuestionResult(result.getContent());
	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	private static class GPTResult {
		private List<Map<String, Object>> choices;

		public String getContent() {
			return ((Map<String, Object>)choices.get(0).get("message")).get("content").toString();
		}
	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	private static class GPTRequest {
		private String model;
		private List<Message> messages;
	}

	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	private static class Message {
		private String role;
		private String content;
	}

}
