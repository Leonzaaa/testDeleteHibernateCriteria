package com.example;

import com.model.BlockRequest;
import com.repositories.BlockRequestNumberNoteRepository;
import com.repositories.BlockRequestRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@QuarkusTest
public class DataBaseTests {
    @Inject
    BlockRequestRepository blockRequestRepository;

    @Inject
    BlockRequestNumberNoteRepository blockRequestNumberNoteRepository;
    @Test
    @Transactional
    public void addNotes() {
        final String action = "NC";

        BlockRequest blockRequest = blockRequestRepository.findById(1L);
        assertDoesNotThrow(() ->
                blockRequestNumberNoteRepository.addNote(blockRequest, "3002457775", action, "test add notes3"));

    }

    // ================================ Criteria API  =======================================
    @Test
    @Transactional
    public void deleteNotesCriteria() {
        final String action = "NC";
        BlockRequest blockRequest = blockRequestRepository.findById(1L);

        assertDoesNotThrow(() ->
                blockRequestNumberNoteRepository.addNote(blockRequest, "3002457775", action, "test add notes3"));

        assertDoesNotThrow(() ->
                blockRequestNumberNoteRepository.cleanNotesCriteria(blockRequest, action));

    }

    @Test
    @Transactional
    public void deleteNotesCriteriaWhyDontWork() {
        final String action = "NC";
        BlockRequest blockRequest = blockRequestRepository.findById(1L);

        assertDoesNotThrow(() ->
                blockRequestNumberNoteRepository.addNote(blockRequest, "3002457775", action, "test add notes3"));

        assertDoesNotThrow(() ->
                blockRequestNumberNoteRepository.cleanNotesCriteriaWhyDontWork(blockRequest, action));

    }
    // ================================ Panache  ===========================================
    @Test
    @Transactional
    public void deleteNotesPanache() {
        final String action = "NC";
        BlockRequest blockRequest = blockRequestRepository.findById(1L);

        assertDoesNotThrow(() ->
                blockRequestNumberNoteRepository.addNote(blockRequest, "3002457775", action, "test add notes3"));

        assertDoesNotThrow(() ->
                blockRequestNumberNoteRepository.cleanNotesPanache(blockRequest, action));

    }

    @Test
    @Transactional
    public void deleteNotesPanache2() {
        final String action = "NC";
        BlockRequest blockRequest = blockRequestRepository.findById(1L);

        assertDoesNotThrow(() ->
                blockRequestNumberNoteRepository.addNote(blockRequest, "3002457775", action, "test add notes3"));

        assertDoesNotThrow(() ->
                blockRequestNumberNoteRepository.cleanNotesPanache2(blockRequest, action));

    }

    @Test
    @Transactional
    public void cleanNotesPanacheWhyDontWork() {
        final String action = "NC";
        BlockRequest blockRequest = blockRequestRepository.findById(1L);

        assertDoesNotThrow(() ->
                blockRequestNumberNoteRepository.addNote(blockRequest, "3002457775", action, "test add notes3"));

        assertDoesNotThrow(() ->
                blockRequestNumberNoteRepository.cleanNotesPanacheWhyDontWork(blockRequest, action));

    }
}
