package br.edu.caelum.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	private String title;
	
	@DateTimeFormat
	private Calendar launchDate;
	
	@Lob
	@NotBlank
	private String description;
	
	@Min(30)
	private int numberOfPages;
	
	@ElementCollection
	private List<Price> prices = new ArrayList<>();
	
	private String summaryPath;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getNumberOfPages() {
		return numberOfPages;
	}
	
	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	
	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	public Calendar getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Calendar launchDate) {
		this.launchDate = launchDate;
	}

	public String getSummaryPath() {
		return summaryPath;
	}

	public void setSummaryPath(String summaryPath) {
		this.summaryPath = summaryPath;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", description=" + description + ", numberOfPages="
				+ numberOfPages + "]";
	}

	public BigDecimal priceFor(BookType bookType) {
		return prices
				.stream()
				.filter(price -> price.getBookType().equals(bookType))
				.findFirst().get().getValue();
	}
}
