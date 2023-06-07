package jforce.hrms.entities.dtos.favoriteProject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteProjectSaveDto {
    private int userId;
    private int projectAnnounceId;
}
