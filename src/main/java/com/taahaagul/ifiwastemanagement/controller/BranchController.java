package com.taahaagul.ifiwastemanagement.controller;

import com.taahaagul.ifiwastemanagement.request.BranchRequest;
import com.taahaagul.ifiwastemanagement.response.BranchResponse;
import com.taahaagul.ifiwastemanagement.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TG/branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @GetMapping
    public ResponseEntity<List<BranchResponse>> getAllBranch() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(branchService.getAllBranch());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBranch(@RequestBody BranchRequest branchRequest) {
        branchService.createBranch(branchRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Branch created successfully");
    }
}
