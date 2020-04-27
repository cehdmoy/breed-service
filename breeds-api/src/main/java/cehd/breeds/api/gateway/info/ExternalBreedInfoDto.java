package cehd.breeds.api.gateway.info;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class ExternalBreedInfoDto {
    private Map<String, List<String>> message;
    private String status;

}
