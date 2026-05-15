package com.albertsilva.dev.dscatalog.dto.category.request;

import com.albertsilva.dev.dscatalog.validation.category.annotation.CategoryUpdateValid;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO utilizado para requisições de atualização de categoria.
 *
 * <p>
 * Permite atualização parcial dos dados da categoria, onde apenas
 * os campos informados (não nulos) serão alterados.
 * </p>
 *
 * <p>
 * <b>Comportamento:</b>
 * </p>
 * <ul>
 * <li>Campos nulos são ignorados</li>
 * <li>Suporta atualização parcial (similar a PATCH)</li>
 * </ul>
 *
 * @param name        novo nome da categoria
 * @param description nova descrição da categoria
 * @param active      novo status da categoria
 */
@CategoryUpdateValid
public record CategoryUpdateRequest(

        @NotBlank(message = "O nome da categoria é obrigatório") 
        @Size(min = 3, max = 80, message = "O nome da categoria deve ter entre 3 e 80 caracteres")
        @Pattern(regexp = "^[A-Za-zÀ-ÿ0-9\\s]+$", message = "O nome da categoria possui caracteres inválidos")
        String name,

        @Pattern(regexp = "^$|^.{3,255}$", message = "A descrição deve ter entre 3 e 255 caracteres") 
        String description) {
}
