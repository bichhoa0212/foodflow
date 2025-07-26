package project.foodflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.foodflow.entity.Supplier;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface cho Supplier entity
 * Cung cấp các method để truy vấn dữ liệu nhà cung cấp
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    /**
     * Tìm nhà cung cấp theo tên
     * @param name tên nhà cung cấp
     * @return Optional<Supplier>
     */
    Optional<Supplier> findByName(String name);

    /**
     * Tìm nhà cung cấp theo email
     * @param email email nhà cung cấp
     * @return Optional<Supplier>
     */
    Optional<Supplier> findByEmail(String email);

    /**
     * Tìm nhà cung cấp theo số điện thoại
     * @param phone số điện thoại nhà cung cấp
     * @return Optional<Supplier>
     */
    Optional<Supplier> findByPhone(String phone);

    /**
     * Tìm tất cả nhà cung cấp theo trạng thái
     * @param status trạng thái (1-ACTIVE, 0-INACTIVE)
     * @return List<Supplier>
     */
    List<Supplier> findByStatus(Integer status);

    /**
     * Tìm nhà cung cấp theo tên (tìm kiếm mờ)
     * @param name tên nhà cung cấp (có thể là một phần)
     * @return List<Supplier>
     */
    @Query("SELECT s FROM Supplier s WHERE s.name LIKE %:name% AND s.status = 1")
    List<Supplier> findByNameContainingAndActive(@Param("name") String name);

    /**
     * Kiểm tra xem tên nhà cung cấp đã tồn tại chưa
     * @param name tên nhà cung cấp
     * @return boolean
     */
    boolean existsByName(String name);

    /**
     * Kiểm tra xem email nhà cung cấp đã tồn tại chưa
     * @param email email nhà cung cấp
     * @return boolean
     */
    boolean existsByEmail(String email);
} 