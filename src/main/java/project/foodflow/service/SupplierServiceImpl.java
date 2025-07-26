package project.foodflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.foodflow.entity.Supplier;
import project.foodflow.repository.SupplierRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation cho Supplier
 * Triển khai các business logic liên quan đến nhà cung cấp
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Page<Supplier> getAllSuppliers(Pageable pageable) {
        return supplierRepository.findAll(pageable);
    }

    @Override
    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }

    @Override
    public Optional<Supplier> getSupplierByName(String name) {
        return supplierRepository.findByName(name);
    }

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        // Set default values
        if (supplier.getStatus() == null) {
            supplier.setStatus(1); // ACTIVE
        }
        if (supplier.getCreatedDate() == null) {
            supplier.setCreatedDate(LocalDateTime.now());
        }
        return supplierRepository.save(supplier);
    }

    @Override
    public Optional<Supplier> updateSupplier(Long id, Supplier supplier) {
        return supplierRepository.findById(id)
                .map(existingSupplier -> {
                    // Update fields
                    if (supplier.getName() != null) {
                        existingSupplier.setName(supplier.getName());
                    }
                    if (supplier.getContactInfo() != null) {
                        existingSupplier.setContactInfo(supplier.getContactInfo());
                    }
                    if (supplier.getAddress() != null) {
                        existingSupplier.setAddress(supplier.getAddress());
                    }
                    if (supplier.getPhone() != null) {
                        existingSupplier.setPhone(supplier.getPhone());
                    }
                    if (supplier.getEmail() != null) {
                        existingSupplier.setEmail(supplier.getEmail());
                    }
                    if (supplier.getStatus() != null) {
                        existingSupplier.setStatus(supplier.getStatus());
                    }
                    
                    // Update modified date
                    existingSupplier.setModifiedDate(LocalDateTime.now());
                    
                    return supplierRepository.save(existingSupplier);
                });
    }

    @Override
    public boolean deleteSupplier(Long id) {
        return supplierRepository.findById(id)
                .map(supplier -> {
                    supplier.setStatus(0); // INACTIVE
                    supplier.setModifiedDate(LocalDateTime.now());
                    supplierRepository.save(supplier);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public List<Supplier> searchSuppliersByName(String name) {
        return supplierRepository.findByNameContainingAndActive(name);
    }

    @Override
    public List<Supplier> getActiveSuppliers() {
        return supplierRepository.findByStatus(1);
    }
} 