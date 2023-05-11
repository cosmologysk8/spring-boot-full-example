package com.everis.d4i.tutorial.json;

import com.everis.d4i.tutorial.entities.TvShow;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharapterRest implements Serializable {

    private static final long serialVersionUID = 162624623714155L;

    private Long id;
    private String first_name;
    private String last_name;
    private String nationality;
    private Date birth;
    private String byography;
    private List<TvShow> tvShow;

}
