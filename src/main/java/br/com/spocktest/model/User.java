package br.com.spocktest.model;

import br.com.spocktest.model.enums.JobTitle;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {

    private Long id;
    private String name;
    private JobTitle jobTitle;

}
