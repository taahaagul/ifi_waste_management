package com.taahaagul.ifiwastemanagement.mapper;

import com.taahaagul.ifiwastemanagement.dto.CountryDTO;
import com.taahaagul.ifiwastemanagement.entity.Country;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;


class CountryMapperTest {

    private CountryMapper countryMapper;

    @BeforeEach
    void setUp() {
        countryMapper = new CountryMapper();
    }

    @Test
    public void shouldMapCountryDtoToCountry() {
        // given
        CountryDTO countryDTO = CountryDTO.builder()
                .countryName("Turkey")
                .countryCode("TR")
                .build();

        // when
        var country = countryMapper.mapToCountry(countryDTO, new Country());

        // then
        Assertions.assertEquals(countryDTO.getCountryName(), country.getCountryName());
        Assertions.assertEquals(countryDTO.getCountryCode(), country.getCountryCode());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenCountryDtoIsNull() {
        var exp = Assertions.assertThrows(NullPointerException.class, () -> {
            countryMapper.mapToCountry(null, new Country());
        });
        Assertions.assertEquals("CountryDTO is null", exp.getMessage());
    }

    @Test
    public void shouldMapCountryToCountryDto() {
        // given
        Country country = new Country();
        country.setId(1L);
        country.setCountryName("Turkey");
        country.setCountryCode("TR");
        country.setCreatedAt(LocalDateTime.now());
        country.setCreatedBy("TG");
        country.setUpdatedAt(LocalDateTime.now());
        country.setUpdatedBy("TG");

        // when
        var countryDTO = countryMapper.mapToCountryDTO(country);

        // then
        Assertions.assertEquals(country.getId(), countryDTO.getId());
        Assertions.assertEquals(country.getCountryName(), countryDTO.getCountryName());
        Assertions.assertEquals(country.getCountryCode(), countryDTO.getCountryCode());
        Assertions.assertEquals(country.getCreatedAt(), countryDTO.getCreatedAt());
        Assertions.assertEquals(country.getCreatedBy(), countryDTO.getCreatedBy());
        Assertions.assertEquals(country.getUpdatedAt(), countryDTO.getUpdatedAt());
        Assertions.assertEquals(country.getUpdatedBy(), countryDTO.getUpdatedBy());

    }
}