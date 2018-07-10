package eprecise.efiscal4j.nfe.event;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EventStatus {

	private final String statusCode;

	private final String statusDescription;

}
