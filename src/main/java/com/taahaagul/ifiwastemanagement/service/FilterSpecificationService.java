package com.taahaagul.ifiwastemanagement.service;

import com.taahaagul.ifiwastemanagement.dto.RequestDTO;
import com.taahaagul.ifiwastemanagement.dto.SearchRequestDTO;
import com.taahaagul.ifiwastemanagement.exception.IllegalOperationException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class FilterSpecificationService<T> {

    public Specification<T> getSearchSpecification(
            List<SearchRequestDTO> searchRequestDTOs,
            RequestDTO.GlobalOperator globalOperator) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            for (SearchRequestDTO requestDto : searchRequestDTOs) {

                switch (requestDto.getOperation()) {

                    case EQUAL:
                        Predicate equal = null;
                        if (requestDto.isFormatBoolean()) {
                            equal = criteriaBuilder.equal(root.get(requestDto.getColumn()), Boolean.parseBoolean(requestDto.getValue()));
                        } else {
                            equal = criteriaBuilder.equal(root.get(requestDto.getColumn()), requestDto.getValue());
                        }
                        predicates.add(equal);
                        break;

                    case NOT_EQUAL:
                        Predicate notEqual = null;
                        if (requestDto.isFormatBoolean()) {
                            notEqual = criteriaBuilder.notEqual(root.get(requestDto.getColumn()), Boolean.parseBoolean(requestDto.getValue()));
                        } else {
                            notEqual = criteriaBuilder.notEqual(root.get(requestDto.getColumn()), requestDto.getValue());
                        }
                        predicates.add(notEqual);
                        break;


                    case LIKE:
                        Predicate like = criteriaBuilder.like(root.get(requestDto.getColumn()), "%" + requestDto.getValue() + "%");
                        predicates.add(like);
                        break;

                    case IN:
                        String[] split = requestDto.getValue().split(",");
                        Predicate in = root.get(requestDto.getColumn()).in(Arrays.asList(split));
                        predicates.add(in);
                        break;

                    case GREATER_THAN:
                        Predicate greaterThan = null;
                        if (requestDto.isFormatDate()) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            LocalDateTime parsedDate = LocalDateTime.parse(requestDto.getValue(), formatter);
                            greaterThan = criteriaBuilder.greaterThan(root.get(requestDto.getColumn()), parsedDate);
                        } else {
                            greaterThan = criteriaBuilder.greaterThan(root.get(requestDto.getColumn()), requestDto.getValue());
                        }
                        predicates.add(greaterThan);
                        break;

                    case LESS_THAN:
                        Predicate lessThan = null;
                        if (requestDto.isFormatDate()) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            LocalDateTime parsedDate = LocalDateTime.parse(requestDto.getValue(), formatter);
                            lessThan = criteriaBuilder.lessThan(root.get(requestDto.getColumn()), parsedDate);
                        } else {
                            lessThan = criteriaBuilder.lessThan(root.get(requestDto.getColumn()), requestDto.getValue());
                        }
                        predicates.add(lessThan);
                        break;

                    case BETWEEN:
                        String[] split1 = requestDto.getValue().split(",");
                        Predicate between = criteriaBuilder.between(root.get(requestDto.getColumn()), split1[0], split1[1]);
                        predicates.add(between);
                        break;

                    case JOIN:
                        Predicate join = criteriaBuilder.equal(root.join(requestDto.getJoinTable()).get(requestDto.getColumn()), requestDto.getValue());
                        predicates.add(join);
                        break;

                    default:
                        throw new IllegalOperationException("Unexpected value: " + requestDto.getOperation());
                }
            }

            if(globalOperator.equals(RequestDTO.GlobalOperator.AND)) {
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }else {
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
