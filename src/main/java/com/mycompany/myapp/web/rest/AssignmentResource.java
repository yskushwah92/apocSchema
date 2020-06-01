package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Assignment;
import com.mycompany.myapp.repository.AssignmentRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Assignment}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AssignmentResource {

    private final Logger log = LoggerFactory.getLogger(AssignmentResource.class);

    private static final String ENTITY_NAME = "assignment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AssignmentRepository assignmentRepository;

    public AssignmentResource(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    /**
     * {@code POST  /assignments} : Create a new assignment.
     *
     * @param assignment the assignment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new assignment, or with status {@code 400 (Bad Request)} if the assignment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/assignments")
    public ResponseEntity<Assignment> createAssignment(@RequestBody Assignment assignment) throws URISyntaxException {
        log.debug("REST request to save Assignment : {}", assignment);
        if (assignment.getId() != null) {
            throw new BadRequestAlertException("A new assignment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Assignment result = assignmentRepository.save(assignment);
        return ResponseEntity.created(new URI("/api/assignments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /assignments} : Updates an existing assignment.
     *
     * @param assignment the assignment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated assignment,
     * or with status {@code 400 (Bad Request)} if the assignment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the assignment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/assignments")
    public ResponseEntity<Assignment> updateAssignment(@RequestBody Assignment assignment) throws URISyntaxException {
        log.debug("REST request to update Assignment : {}", assignment);
        if (assignment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Assignment result = assignmentRepository.save(assignment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, assignment.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /assignments} : get all the assignments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of assignments in body.
     */
    @GetMapping("/assignments")
    public List<Assignment> getAllAssignments() {
        log.debug("REST request to get all Assignments");
        return assignmentRepository.findAll();
    }

    /**
     * {@code GET  /assignments/:id} : get the "id" assignment.
     *
     * @param id the id of the assignment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the assignment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/assignments/{id}")
    public ResponseEntity<Assignment> getAssignment(@PathVariable Long id) {
        log.debug("REST request to get Assignment : {}", id);
        Optional<Assignment> assignment = assignmentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(assignment);
    }

    /**
     * {@code DELETE  /assignments/:id} : delete the "id" assignment.
     *
     * @param id the id of the assignment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        log.debug("REST request to delete Assignment : {}", id);

        assignmentRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
