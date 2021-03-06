package br.com.stoom.api.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.stoom.domain.model.Endereco;
import br.com.stoom.domain.service.EnderecoService;
import io.restassured.http.ContentType;;

@WebMvcTest
public class EnderecoControllerTest {
	
//	@Autowired
//	private MockMvc mockMvc;
	
	@Autowired
	private EnderecoController enderecoController;
	
	@MockBean
	private EnderecoService enderecoService;
			
	@BeforeEach
	public void setup() {
		standaloneSetup(this.enderecoController);
	}
		

	
	@Test
	public void returnSuccess_WhenEditAddress() {
		
	}
	
	@Test
	public void returnSuccess_WhenSearchAddress() {
		
		when(this.enderecoService.findAll())
			.thenReturn(addressFindAll());
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/endereco")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
		
	@Test
	public void returnBadRequest_WhenSaveAddressWithFieldNull() {
		
		when(this.enderecoService.createOrUpdate(addressWithNull()))
			.thenReturn(addressWithNull());
		
		given()
			.accept(ContentType.JSON)
		.when()
			.post("/endereco")
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	
	@Test
	public void returnSuccess_WhenDeleteAddress() {
			
		given()
			.accept(ContentType.JSON)
		.when()
			.delete("/endereco/{id}", 1)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	public List<Endereco> addressFindAll() {
		Endereco endereco1 = new Endereco();
		endereco1.setId(1L);
		endereco1.setStreetName("Av José Odorizzi");
		endereco1.setNumber("2261");
		endereco1.setComplement("BL:04 Apto:24");
		endereco1.setNeighbourhood("Assunção");
		endereco1.setCity("São Bernardo do Campo");
		endereco1.setState("SP");
		endereco1.setCountry("Brasil");
		endereco1.setZipcode("09861-001");
		endereco1.setLatitude("-23.7119865");
		endereco1.setLongitude("-46.58395429999999");
		
		Endereco endereco2 = new Endereco();
		endereco2.setId(1L);
		endereco2.setStreetName("Av. das Araras");
		endereco2.setNumber("946");
		endereco2.setComplement(null);
		endereco2.setNeighbourhood("Parque dos Passaros");
		endereco2.setCity("São Bernardo do Campo");
		endereco2.setState("SP");
		endereco2.setCountry("Brasil");
		endereco2.setZipcode("09861-090");
		endereco2.setLatitude(null);
		endereco2.setLongitude(null);
		
		List<Endereco> result = new ArrayList<Endereco>();
		result.add(endereco1);
		result.add(endereco2);
		
		return result;
		
	}
	
	public Endereco addressWithNull() {
		Endereco endereco = new Endereco();
		endereco.setId(1L);
		endereco.setStreetName("Av José Odorizzi");
		endereco.setNumber("2261");
		endereco.setComplement("BL:04 Apto:24");
		endereco.setNeighbourhood("Assunção");
		endereco.setCity("São Bernardo do Campo");
		endereco.setState("SP");
		endereco.setCountry("Brasil");
		endereco.setZipcode("09861-001");
		endereco.setLatitude("-23.7119865");
		endereco.setLongitude("-46.58395429999999");
		
		return endereco;
		
	}
	
//	@Test
//	public void returnSuccess_WhenSaveAddress() throws Exception {
//				
//		Endereco expectedAddress = new Endereco();
//		expectedAddress.setId(1L);
//		expectedAddress.setStreetName("Av José Odorizzi");
//		expectedAddress.setNumber("2261");
//		expectedAddress.setComplement("BL:04 Apto:24");
//		expectedAddress.setNeighbourhood("Assunção");
//		expectedAddress.setCity("São Bernardo do Campo");
//		expectedAddress.setState("SP");
//		expectedAddress.setCountry("Brasil");
//		expectedAddress.setZipcode("09861-001");
//		expectedAddress.setLatitude("-23.7119865");
//		expectedAddress.setLongitude("-46.58395429999999");
//		
//		when(this.enderecoService.createOrUpdate(any(Endereco.class))).thenReturn(expectedAddress);
//		
//		Endereco saveAddress = new Endereco();
//		expectedAddress.setId(1L);
//		expectedAddress.setStreetName("Av José Odorizzi");
//		expectedAddress.setNumber("2261");
//		expectedAddress.setComplement("BL:04 Apto:24");
//		expectedAddress.setNeighbourhood("Assunção");
//		expectedAddress.setCity("São Bernardo do Campo");
//		expectedAddress.setState("SP");
//		expectedAddress.setCountry("Brasil");
//		expectedAddress.setZipcode("09861-001");
//		expectedAddress.setLatitude("-23.7119865");
//		expectedAddress.setLongitude("-46.58395429999999");
//		
//		mockMvc.perform(MockMvcRequestBuilders
//				.post("/endereco")
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)
//				.content(saveAddress)
//				.andExpect(status().isOk)
//				.andExpect(content().json(expectedAddress)));		
//	}

}
