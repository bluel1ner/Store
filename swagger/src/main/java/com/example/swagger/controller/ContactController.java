package com.example.swagger.controller;

import io.swagger.annotations.*;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Neevels
 * @version 1.0
 * @date 3/10/2023 2:22 PM
 */
@Api(description = "Endpoints for Creating, Retrieving, Updating and Deleting of Contacts.",
        tags = {"contact"})
@RestController
@RequestMapping("/api")
public class ContactController {
    @ApiOperation(value = "Find Contacts by name", notes = "Name search by %name% format", tags = {"contact"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = List.class)})
    @GetMapping(value = "/contacts")
    public ResponseEntity<List<Contact>> findAll(
            @ApiParam("Page number, default is 1") @RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @ApiParam("Name of the contact for search.") @RequestParam(required = false) String name) {
        return null;
    }

    @ApiOperation(value = "Find contact by ID", notes = "Returns a single contact", tags = {"contact"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Contact.class),
            @ApiResponse(code = 404, message = "Contact not found")})
    @GetMapping(value = "/contacts/{contactId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> findContactById(
            @ApiParam("Id of the contact to be obtained. Cannot be empty.")
            @PathVariable long contactId) {
        return null;
    }

    @ApiOperation(value = "Add a new contact", tags = {"contact"})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Contact created"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 409, message = "Contact already exists")})
    @PostMapping(value = "/contacts")
    public ResponseEntity<Contact> addContact(
            @ApiParam("Contact to add. Cannot null or empty.")
            @Valid @RequestBody Contact contact)
            throws URISyntaxException {
        return null;
    }

    @ApiOperation(value = "Update an existing contact", tags = {"contact"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Contact not found"),
            @ApiResponse(code = 405, message = "Validation exception")})
    @PutMapping(value = "/contacts/{contactId}")
    public ResponseEntity<Contact> updateContact(
            @ApiParam("Id of the contact to be update. Cannot be empty.")
            @PathVariable long contactId,
            @ApiParam("Contact to update. Cannot null or empty.")
            @Valid @RequestBody Contact contact) {
        return null;

    }

    @ApiOperation(value = "Update an existing contact's address", tags = {"contact"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 404, message = "Contact not found")})
    @PatchMapping("/contacts/{contactId}")
    public ResponseEntity<Void> updateAddress(
            @ApiParam("Id of the contact to be update. Cannot be empty.")
            @PathVariable long contactId,
            @ApiParam("Contact's address to update.")
            @RequestBody String address) {
        return null;

    }

    @ApiOperation(value = "Deletes a contact", tags = {"contact"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 404, message = "Contact not found")})
    @DeleteMapping(path = "/contacts/{contactId}")
    public ResponseEntity<Void> deleteContactById(
            @ApiParam("Id of the contact to be delete. Cannot be empty.")
            @PathVariable long contactId) {
        return null;
    }
}
