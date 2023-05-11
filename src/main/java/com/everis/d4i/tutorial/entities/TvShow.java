package com.everis.d4i.tutorial.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.io.Serializable;
import java.time.Year;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TV_SHOWS")
public class TvShow implements Serializable {

	private static final long serialVersionUID = 4916713904971425156L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "SHORT_DESC", nullable = true)
	private String shortDescription;

	@Column(name = "LONG_DESC", nullable = true)
	private String longDescription;

	@Column(name = "YEAR")
	private Year year;

	@Column(name = "RECOMMENDED_AGE")
	private byte recommendedAge;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
		name = "TV_SHOWS_CATEGORIES",
		joinColumns = @JoinColumn(name = "ID_TV_SHOW"),
		inverseJoinColumns = @JoinColumn(name = "ID_CATEGORIES"))
	@JsonIgnore
	private List<Category> category;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "TV_SHOWS_CHARAPTERS",
			joinColumns = @JoinColumn(name = "ID_TV_SHOW"),
			inverseJoinColumns = @JoinColumn(name = "ID_CHARAPTERS"))
	@JsonIgnore
	private List<Charapter> charapters;

	@Column(name = "ADVERTISING", nullable = true)
	private String advertising;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tvShow")
	@JsonManagedReference
	private List<Season> seasons;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
			name = "TV_SHOWS_REWARDS",
			joinColumns = @JoinColumn(name = "ID_TV_SHOW"),
			inverseJoinColumns = @JoinColumn(name = "ID_REWARD"))
	@JsonManagedReference
	private List<Reward> rewards;


}
