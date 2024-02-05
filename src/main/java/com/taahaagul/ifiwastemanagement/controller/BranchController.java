package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.request.BranchRequest;
import com.taahaagul.ifiwastemanagement.request.BranchUpdateRequest;
import com.taahaagul.ifiwastemanagement.response.BranchResponse;
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

    @GetMapping
    public ResponseEntity<Page<BranchResponse>> getAllBranch(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<BranchResponse> branchPage = branchService.getAllBranch(pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(branchPage);
    }

    @PostMapping("/create")
    public ResponseEntity<BranchResponse> createBranch(
            @Valid @RequestBody BranchRequest branchRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(branchService.createBranch(branchRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchResponse> updateBranch(
            @PathVariable Long id,
            @Valid @RequestBody BranchUpdateRequest branchUpdateRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(branchService.updateBranch(id, branchUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBranch(
            @PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Branch deleted successfully");
    }

    @PutMapping("/{branchId}/district/{districtId}")
    public ResponseEntity<BranchResponse> assignBranchZone(
            @PathVariable Long branchId,
            @PathVariable Long districtId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(branchService.assignBranchZone(branchId, districtId));
    }
}
