package com.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "BLOCKREQUEST_BODY_NOTE")
public class BlockRequestNumberNote implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LS2_ID")
    private BlockRequestNumber blockRequestNumber;

    @Column(name = "BLOCK_REQUEST_ACTION")
    private String blockRequestAction;


    @Column(name = "MESSAGE")
    private String message;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockRequestNumberNote that = (BlockRequestNumberNote) o;

        if (!id.equals(that.id)) return false;
        if (!blockRequestNumber.equals(that.blockRequestNumber)) return false;
        if (!blockRequestAction.equals(that.blockRequestAction)) return false;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + blockRequestNumber.hashCode();
        result = 31 * result + blockRequestAction.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BlockRequestNumberNote{" +
                "id=" + id +
                ", blockRequestAction='" + blockRequestAction + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
