package com.booknest.booknest_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * Book Entity Model
 * Represents a book in the BookNest system with complete validation and entity configuration.
 */
@Entity
@Table(name = "books", indexes = {
    @Index(name = "idx_isbn", columnList = "isbn", unique = true),
    @Index(name = "idx_title", columnList = "title"),
    @Index(name = "idx_author", columnList = "author"),
    @Index(name = "idx_category", columnList = "category_id"),
    @Index(name = "idx_created_at", columnList = "created_at")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Book title is required")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @NotBlank(message = "Author name is required")
    @Size(min = 1, max = 255, message = "Author name must be between 1 and 255 characters")
    @Column(name = "author", nullable = false, length = 255)
    private String author;

    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^(?:ISBN(?:-1[03])?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)(?:97[89][- ]?)?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[X0-9]$", 
        message = "ISBN must be a valid ISBN-10 or ISBN-13 format")
    @Column(name = "isbn", nullable = false, unique = true, length = 20)
    private String isbn;

    @Size(max = 2000, message = "Description must not exceed 2000 characters")
    @Column(name = "description", length = 2000)
    private String description;

    @NotNull(message = "Publication year is required")
    @Min(value = 1000, message = "Publication year must be valid (minimum 1000)")
    @Max(value = 2100, message = "Publication year must be valid (maximum 2100)")
    @Column(name = "publication_year", nullable = false)
    private Integer publicationYear;

    @NotBlank(message = "Publisher is required")
    @Size(min = 1, max = 255, message = "Publisher name must be between 1 and 255 characters")
    @Column(name = "publisher", nullable = false, length = 255)
    private String publisher;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Price must not exceed 999999.99")
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock quantity cannot be negative")
    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Size(max = 500, message = "Genre must not exceed 500 characters")
    @Column(name = "genre", length = 500)
    private String genre;

    @Size(max = 500, message = "Category must not exceed 500 characters")
    @Column(name = "category", length = 500)
    private String category;

    @Column(name = "pages")
    @Min(value = 1, message = "Number of pages must be at least 1")
    private Integer pages;

    @Size(max = 255, message = "Language must not exceed 255 characters")
    @Column(name = "language", length = 255)
    private String language;

    @Size(max = 255, message = "Cover image URL must not exceed 255 characters")
    @Column(name = "cover_image_url", length = 255)
    private String coverImageUrl;

    @DecimalMin(value = "0.0", message = "Rating cannot be negative")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5.0")
    @Column(name = "rating")
    private BigDecimal rating;

    @Min(value = 0, message = "Review count cannot be negative")
    @Column(name = "review_count")
    @Builder.Default
    private Integer reviewCount = 0;

    @NotNull(message = "Availability status is required")
    @Column(name = "is_available", nullable = false)
    @Builder.Default
    private Boolean isAvailable = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private Boolean isDeleted = false;

    /**
     * Lifecycle callback to automatically update the updatedAt timestamp
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Lifecycle callback to set createdAt if not already set
     */
    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.updatedAt == null) {
            this.updatedAt = LocalDateTime.now();
        }
    }
}
