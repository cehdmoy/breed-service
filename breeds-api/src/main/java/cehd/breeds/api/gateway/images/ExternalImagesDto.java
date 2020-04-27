
package cehd.breeds.api.gateway.images;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ExternalImagesDto {
    private List<String> message;
    private String status;

}
