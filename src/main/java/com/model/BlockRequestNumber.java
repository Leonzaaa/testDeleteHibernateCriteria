package com.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "BLOCK_REQUEST_NUMBERS")
public class BlockRequestNumber implements Serializable{
    

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "LS2_ID")
    private Long id;

    @Column(name = "NUMBER_ADD")
    private String numbers;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LS_ID")
    private BlockRequest blockRequest;

    @OneToMany(mappedBy = "blockRequestNumber", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BlockRequestNumberNote> blockRequestNumberNotes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockRequestNumber that = (BlockRequestNumber) o;

        if (!id.equals(that.id)) return false;
        return numbers.equals(that.numbers);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + numbers.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BlockRequestNumber{" +
                "id=" + id +
                ", dn='" + numbers + '\'' +
                '}';
    }

    public void addNote(BlockRequestNumberNote note) {
        if(blockRequestNumberNotes == null) {
            blockRequestNumberNotes = new ArrayList<>();
        }
        blockRequestNumberNotes.add(note);
        note.setBlockRequestNumber(this);
    }
}