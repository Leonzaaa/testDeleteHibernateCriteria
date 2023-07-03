package com.model;


import jakarta.persistence.*;
import lombok.*;


import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "BLOCK_REQUEST")
public class BlockRequest implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LS_ID")
    private Long id;

    @Column(name = "DATE_CREATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    @Column(name = "USERNAME_CREATE")
    private String userNameCreate;

    @OneToMany(mappedBy = "blockRequest", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private List<BlockRequestNumber> blockRequestNumbers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockRequest that = (BlockRequest) o;

        if (!id.equals(that.id)) return false;
        return dateCreate.equals(that.dateCreate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + dateCreate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BlockRequest{" +
                "id=" + id +
                ", dateCreate=" + dateCreate +
                ", userNameCreate='" + userNameCreate + '\'' +
                '}';
    }
}
