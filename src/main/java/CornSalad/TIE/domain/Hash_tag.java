package CornSalad.TIE.domain;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

//효정 수정 버전
@Entity
@Getter
@Setter
@Table(name = "HASH_TAG")
public class Hash_tag {
    @Id
    @GeneratedValue
    @Column(name = "TAG_ID")
    private Long ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post POST;
    private String TAG_CONTENTS;

    //private int TAG_COUNT;
    //private Type TAG_TYPE; // HASH, COMMENTS
}