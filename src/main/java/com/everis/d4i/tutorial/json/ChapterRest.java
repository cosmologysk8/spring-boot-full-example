package com.everis.d4i.tutorial.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterRest implements Serializable {

	private static final long serialVersionUID = 8725949484031409482L;

	private Long id;
	private short number;
	private String name;
	private short duration;

}
