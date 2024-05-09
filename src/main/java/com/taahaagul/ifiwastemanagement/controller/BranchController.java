package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.dto.BranchDTO;
import com.taahaagul.ifiwastemanagement.dto.RequestDTO;
import com.taahaagul.ifiwastemanagement.dto.ZoneDTO;
import com.taahaagul.ifiwastemanagement.service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/TG/branch")
@RequiredArgsConstructor
@Validated
public class BranchController {

    private final BranchService branchService;

    @PostMapping("/all")
    public ResponseEntity<Page<BranchDTO>> getAllBranches(
            @RequestBody RequestDTO requestDTO) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(branchService.getAllBranches(requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById(
            @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(branchService.getBranchById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<BranchDTO> createBranch(
            @Valid @RequestBody BranchDTO branchDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(branchService.createBranch(branchDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<BranchDTO> updateBranch(
            @Valid @RequestBody BranchDTO branchDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(branchService.updateBranch(branchDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBranch(
            @PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Branch deleted successfully");
    }

    @PutMapping("/{branchId}/district/{districtId}")
    public ResponseEntity<BranchDTO> assignBranchDistrict(
            @PathVariable Long branchId,
            @PathVariable Long districtId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(branchService.assignBranchDistrict(branchId, districtId));
    }

    @GetMapping("/{branchId}/zones")
    public ResponseEntity<Page<ZoneDTO>> getBranchZones(
            @PathVariable Long branchId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(branchService.getBranchZones(branchId, pageable));
    }
}