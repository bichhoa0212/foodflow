package project.foodflow.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.foodflow.entity.Supplier;

import java.util.List;
import java.util.Optional;

/**
 * Service interface cho Supplier
 * Định nghĩa các business logic liên quan đến nhà cung cấp
 */
public interface SupplierService {

    /**
     * Lấy tất cả nhà cung cấp
     * @return List<Supplier>
     */
    List<Supplier> getAllSuppliers();

    /**
     * Lấy tất cả nhà cung cấp với phân trang
     * @param pageable thông tin phân trang
     * @return Page<Supplier>
     */
    Page<Supplier> getAllSuppliers(Pageable pageable);

    /**
     * Lấy nhà cung cấp theo ID
     * @param id ID nhà cung cấp
     * @return Optional<Supplier>
     */
    Optional<Supplier> getSupplierById(Long id);

    /**
     * Lấy nhà cung cấp theo tên
     * @param name tên nhà cung cấp
     * @return Optional<Supplier>
     */
    Optional<Supplier> getSupplierByName(String name);

    /**
     * Lưu nhà cung cấp mới
     * @param supplier thông tin nhà cung cấp
     * @return Supplier
     */
    Supplier saveSupplier(Supplier supplier);

    /**
     * Cập nhật thông tin nhà cung cấp
     * @param id ID nhà cung cấp
     * @param supplier thông tin cập nhật
     * @return Optional<Supplier>
     */
    Optional<Supplier> updateSupplier(Long id, Supplier supplier);

    /**
     * Xóa nhà cung cấp (soft delete)
     * @param id ID nhà cung cấp
     * @return boolean
     */
    boolean deleteSupplier(Long id);

    /**
     * Tìm kiếm nhà cung cấp theo tên
     * @param name tên nhà cung cấp (có thể là một phần)
     * @return List<Supplier>
     */
    List<Supplier> searchSuppliersByName(String name);

    /**
     * Lấy danh sách nhà cung cấp đang hoạt động
     * @return List<Supplier>
     */
    List<Supplier> getActiveSuppliers();
} 