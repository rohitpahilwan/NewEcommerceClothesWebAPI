package com.app.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {


	private Long pid;
	
	private @NotNull String name;
	private @NotNull String imgURL;
	private @NotNull float price;
	private @NotNull String descrption;
	
	private Long categoryId;
}
