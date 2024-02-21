package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.dto.CountryDTO;
import com.taahaagul.ifiwastemanagement.entity.Country;
import com.taahaagul.ifiwastemanagement.mapper.CountryMapper;
import com.taahaagul.ifiwastemanagement.repository.CountryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class CountryServiceTest {

    // which service we want to test
    @InjectMocks
    private CountryService countryService;

    // declare the dependencies
    @Mock
    private CountryRepository countryRepository;
    @Mock
    private CountryMapper countryMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void ShouldSuccessfullyCreateCountry() {
        // given
        CountryDTO countryDTO = CountryDTO.builder()
                .countryName("Turkey")
                .countryCode("TR")
                .build();

        Country country = new Country();
        country.setCountryName("Turkey");
        country.setCountryCode("TR");

        Country savedCountry = new Country();
        savedCountry.setCountryName("Turkey");
        savedCountry.setCountryCode("TR");
        savedCountry.setId(1L);

        // mock the calls
        when(countryMapper.mapToCountry(countryDTO, new Country())).thenReturn(country);
        when(countryRepository.save(country)).thenReturn(savedCountry);
        when(countryMapper.mapToCountryDTO(savedCountry)).thenReturn(countryDTO);

        // when
        CountryDTO responseDTO = countryService.createCountry(countryDTO);

        // then
        Assertions.assertEquals(countryDTO.getCountryName(), responseDTO.getCountryName());
        Assertions.assertEquals(countryDTO.getCountryCode(), responseDTO.getCountryCode());
    }

    @Test
    public void ShouldReturnAllCountries() {
        // given
        List<Country> countries = new ArrayList<>();

        Country country = new Country();
        country.setId(1L);
        country.setCountryName("Turkey");
        country.setCountryCode("TR");

        countries.add(country);

        CountryDTO countryDTO = CountryDTO.builder()
                .id(1L)
                .countryName("Turkey")
                .countryCode("TR")
                .build();

        // mock the calls
        when(countryRepository.findAll()).thenReturn(countries);
        when(countryMapper.mapToCountryDTO(any(Country.class))).thenReturn(countryDTO);

        // when
        List<CountryDTO> responseDTOs = countryService.getAllCountry();

        // then
        Assertions.assertEquals(countries.size(), responseDTOs.size());
        verify(countryRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void ShouldReturnCountryById() {
        // given
        Long id = 1L;
        Country country = new Country();
        country.setCountryName("Turkey");
        country.setCountryCode("TR");

        CountryDTO countryDTO = CountryDTO.builder()
                .countryName("Turkey")
                .countryCode("TR")
                .build();


        // mock the calls
        when(countryRepository.findById(id)).thenReturn(Optional.of(country));
        when(countryMapper.mapToCountryDTO(country)).thenReturn(countryDTO);

        // when
        CountryDTO responseDTO = countryService.getCountryById(id);

        // then
        Assertions.assertEquals(countryDTO.getCountryName(), responseDTO.getCountryName());
        Assertions.assertEquals(countryDTO.getCountryCode(), responseDTO.getCountryCode());

        verify(countryRepository, Mockito.times(1)).findById(id);
    }
}