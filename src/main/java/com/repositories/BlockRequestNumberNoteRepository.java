package com.repositories;

import com.model.*;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.criteria.*;


@ApplicationScoped
public class BlockRequestNumberNoteRepository implements PanacheRepository<BlockRequestNumberNote> {
    public static final int MESSAGE_LENGTH = 150;
    public void addNote(BlockRequest request, String phone, String action, String message) {
        BlockRequestNumber blockRequestNumber = request.getBlockRequestNumbers().stream()
                .filter(dnObj -> dnObj.getNumbers().equals(phone))
                .findAny()
                .orElseThrow(() -> new RuntimeException("BlockRequestNumberNoteRepository.addNote: Cant found DN for phone [" +
                        phone + "]. BlockRequest: " + request));
        addNote(blockRequestNumber, action, message);
    }


    public void addNote(BlockRequestNumber blockRequestNumber, String action, String message) {
        BlockRequestNumberNote note = new BlockRequestNumberNote();
        note.setBlockRequestAction(action);
        if(message.length() > MESSAGE_LENGTH) {
            message = message.substring(0, MESSAGE_LENGTH);
        }
        note.setMessage(message);
        blockRequestNumber.addNote(note);
        persist(note);
    }

    // ======================== Panache ===========================

    public void cleanNotesPanache(BlockRequest request, String action) {
        find("blockRequestNumber.blockRequest = ?1 and blockRequestAction = ?2", request, action)
                .stream()
                .forEach(this::delete);

    }

    public void cleanNotesPanache2(BlockRequest request, String action) {
        delete("blockRequestNumber in (select brn from BlockRequestNumber brn where brn.blockRequest = ?1) and blockRequestAction = ?2", request, action);
    }

    public void cleanNotesPanacheWhyDontWork(BlockRequest request, String action) {
        delete("blockRequestNumber.blockRequest = ?1 and blockRequestAction = ?2", request, action);

    }

    // ==================== Criteria API ======================

    public void cleanNotesCriteriaWhyDontWork(BlockRequest request, String action) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaDelete<BlockRequestNumberNote> delete = cb.createCriteriaDelete(BlockRequestNumberNote.class);
        Root<BlockRequestNumberNote> root = delete.from(BlockRequestNumberNote.class);

        Path<Long> blockRequestPath = root.get(BlockRequestNumberNote_.blockRequestNumber)
                .get(BlockRequestNumber_.blockRequest).get(BlockRequest_.id);
        delete.where(cb.and(cb.equal(blockRequestPath, request.getId())),
                cb.equal(root.get(BlockRequestNumberNote_.blockRequestAction), action));
        getEntityManager().createQuery(delete).executeUpdate();
    }


    public void cleanNotesCriteria(BlockRequest request, String action) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaDelete<BlockRequestNumberNote> delete = cb.createCriteriaDelete(BlockRequestNumberNote.class);
        Root<BlockRequestNumberNote> root = delete.from(BlockRequestNumberNote.class);

        Subquery<BlockRequestNumber> blockRequestNumberSubquery = delete.subquery(BlockRequestNumber.class);
        Root<BlockRequestNumber> blockRequestNumberRoot = blockRequestNumberSubquery.from(BlockRequestNumber.class);
        blockRequestNumberSubquery.select(blockRequestNumberRoot);
        blockRequestNumberSubquery.where(cb.equal(blockRequestNumberRoot.get(BlockRequestNumber_.blockRequest), request));

        delete.where(cb.and(root.get(BlockRequestNumberNote_.blockRequestNumber).in(blockRequestNumberSubquery),
                cb.equal(root.get(BlockRequestNumberNote_.blockRequestAction), action)));

        getEntityManager().createQuery(delete).executeUpdate();
    }

}
