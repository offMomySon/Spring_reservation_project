package kr.or.connect.reservation.model;

import kr.or.connect.reservation.model.audite.BaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "file_info")
public class FileInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "save_file_name")
    private String saveFileName;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "delete_flag")
    private Boolean deleteFlag;
}
