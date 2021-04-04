package br.com.uol.pagseguro.model;

import br.com.uol.pagseguro.model.enums.JobTitle;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {

    private Long id;
    private String name;
    private JobTitle jobTitle;

}
